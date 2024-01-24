package services;

import models.*;
import utils.ConsoleReader;
import utils.DatabaseHandler;

import java.util.ArrayList;

public class BudgetService {

    public static void setBudget() {
        ConsoleReader reader = ConsoleReader.getInstance();
        System.out.println("Budget Categories:");
        ArrayList<Budget> budgetEntries = DatabaseHandler.getBudgets();
        for (int i = 0; i < budgetEntries.size(); i++) {
            Budget budget = budgetEntries.get(i);
            int getCategoryID = budgetEntries.get(i).getCategoryID();
            Category category = DatabaseHandler.getCategoryByID(getCategoryID);
            if (category == null) {
                continue;
            }
            System.out.println((i + 1) + ". " + category.getName() + ": " + budget.getDisplayAmount());
        }

        System.out.print("Enter the number of the category to set the budget: ");
        int categoryID = reader.readInteger();

        if (categoryID >= 1 && categoryID <= budgetEntries.size()) { //  check this logic
            System.out.print("Enter the new budget amount for the selected category: Rs.");
            double newBudgetAmount = reader.readDouble();

            Category category = DatabaseHandler.getCategoryByID(categoryID);
            int budgetEntryID = category.getBudgetID();
            Budget budget = DatabaseHandler.getBudgetByID(budgetEntryID);
            budget.setAmount(newBudgetAmount);
            DatabaseHandler.setBudgetEntry(budgetEntryID, budget);

            System.out.println("Budget for " + category.getName() + " updated successfully.\n");
        } else {
            System.out.println("Invalid category number. Please enter a valid number.\n");
        }
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

            System.out.println(category.getName() + ": Spent Rs." + totalSpent + " out of Rs." +
                    budgetAmount + " - Progress: " + progressPercentage + "%");
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
