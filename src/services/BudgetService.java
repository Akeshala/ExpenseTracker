package services;

import factory.BudgetFactory;
import models.*;
import utils.ConsoleReader;
import utils.DatabaseHandler;

import java.util.ArrayList;

public class BudgetService {

    public static void setBudget() {
        ConsoleReader reader = ConsoleReader.getInstance();
        System.out.println("Budget Categories:");
        ArrayList<Budget> budgets = DatabaseHandler.getBudgets();
        for (Budget budget : budgets) {
            int getCategoryID = budget.getCategoryID();
            Category category = DatabaseHandler.getCategoryByID(getCategoryID);
            if (category == null) {
                continue;
            }
            System.out.println(budget.getId() + ". " + category.getName() + ": " + budget.getDisplayAmount());
        }

        System.out.print("Enter the number of the category to set the budget: ");
        int categoryID = reader.readInteger();

        if (categoryID < 1) {
            System.out.println("Invalid category ID! Category number should be greater than zero.\n");
            return;
        }

        Category category = DatabaseHandler.getCategoryByID(categoryID);
        if (category == null) {
            System.out.println("Category not available for the ID.\n");
            return;
        }

        System.out.print("Enter the new budget amount for the selected category: Rs.");
        double newBudgetAmount = reader.readDouble();

        int budgetEntryID = category.getBudgetID();
        Budget budget = DatabaseHandler.getBudgetByID(budgetEntryID);
        if (budget == null) {
            Budget newBudget = BudgetFactory.getBudget(categoryID, newBudgetAmount);
            DatabaseHandler.addBudget(newBudget);
        } else {
            budget.setAmount(newBudgetAmount);
            DatabaseHandler.setBudgetEntry(budgetEntryID, budget);
        }

        System.out.println("Budget for " + category.getName() + " updated successfully.\n");
    }

    public static void trackProgress() {
        System.out.println("Budget Progress:");

        ArrayList<Budget> budgetEntries = DatabaseHandler.getBudgets();

        for (Budget budget : budgetEntries) {
            int categoryID = budget.getCategoryID();
            Category category = DatabaseHandler.getCategoryByID(categoryID);
            double totalSpent = calculateTotalSpent(categoryID);
            double budgetAmount = budget.getAmount();

            double progressPercentage = (totalSpent / budgetAmount) * 100;

            System.out.println(category.getName() + ": Spent " + Money.getFormattedAmount(totalSpent) + " out of " +
                    Money.getFormattedAmount(budgetAmount) + " - Progress: " + progressPercentage + "%");
        }
        System.out.println();
    }

    private static double calculateTotalSpent(int categoryID) {
        double totalSpent = 0;

        for (Transaction transaction : DatabaseHandler.getTransactions()) {
            if (transaction.getCategoryID().equals(categoryID) && (transaction instanceof Expense)) {
                totalSpent += transaction.getAmount().getValue();
            }
        }

        return totalSpent;
    }
}
