package org.asd.services;

import org.asd.factory.BudgetFactory;
import org.asd.models.*;
import org.asd.utils.ConsoleReader;
import org.asd.utils.DatabaseHandler;
import org.asd.utils.Money;

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
            System.out.println(budget.getId() + ". " + category.getName() + ": " +
                    Money.getFormattedAmount(budget.getAmount()));
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

        int budgetID = category.getBudgetID();
        Budget budget = DatabaseHandler.getBudgetByID(budgetID);
        if (budget == null) {
            Budget newBudget = BudgetFactory.getBudget(categoryID, newBudgetAmount);
            int newBudgetID = DatabaseHandler.addBudget(newBudget);
            category.setBudgetID(newBudgetID);
            DatabaseHandler.editCategory(categoryID, category);
        } else {
            budget.setAmount(newBudgetAmount);
            DatabaseHandler.setBudget(budgetID, budget);
        }

        System.out.println("Budget for " + category.getName() + " updated successfully.\n");
    }

    public static void trackProgress() {
        System.out.println("Budget Progress:");

        ArrayList<Budget> budgets = DatabaseHandler.getBudgets();
        ArrayList<Transaction> transactions = DatabaseHandler.getTransactions();

        for (Budget budget : budgets) {
            int categoryID = budget.getCategoryID();
            Category category = DatabaseHandler.getCategoryByID(categoryID);
            double totalSpent = calculateTotalSpentByCategory(categoryID, transactions);
            double budgetAmount = budget.getAmount();

            double progressPercentage = (totalSpent / budgetAmount) * 100;

            System.out.println(category.getName() + ": Spent " + Money.getFormattedAmount(totalSpent) + " out of " +
                    Money.getFormattedAmount(budgetAmount) + " - Progress: " + progressPercentage + "%");
        }
        System.out.println();
    }

    static double calculateTotalSpentByCategory(int categoryID, ArrayList<Transaction> transactions) {
        return transactions.stream()
                .filter(transaction -> transaction.getCategoryID().equals(categoryID))
                .filter(transaction -> transaction instanceof Expense)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }
}
