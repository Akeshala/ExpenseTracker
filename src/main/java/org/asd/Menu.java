package org.asd;

import org.asd.services.BudgetService;
import org.asd.services.CategoryService;
import org.asd.services.TransactionService;
import org.asd.utils.ConsoleReader;

public class Menu {

    public static final int VIEW_TRANSACTIONS = 1;
    public static final int ADD_NEW_TRANSACTION = 2;
    public static final int EDIT_TRANSACTION = 3;
    public static final int DELETE_TRANSACTION = 4;
    public static final int VIEW_CATEGORIES = 5;
    public static final int ADD_NEW_CATEGORY = 6;
    public static final int DELETE_CATEGORY = 7;
    public static final int SET_BUDGET = 8;
    public static final int TRACK_PROGRESS = 9;
    public static final int TOTAL_INCOME = 10;
    public static final int EXIT_CHOICE = 0;

    public static void display() {
        System.out.println("Expense Tracker Menu:");
        System.out.println(VIEW_TRANSACTIONS + ". View recent transactions");
        System.out.println(ADD_NEW_TRANSACTION + ". Add a new transaction");
        System.out.println(EDIT_TRANSACTION + ". Edit a transaction");
        System.out.println(DELETE_TRANSACTION + ". Delete a transaction");
        System.out.println(VIEW_CATEGORIES + ". View categories");
        System.out.println(ADD_NEW_CATEGORY + ". Add new category");
        System.out.println(DELETE_CATEGORY + ". Delete category");
        System.out.println(SET_BUDGET + ". Set budget");
        System.out.println(TRACK_PROGRESS + ". Track progress");
        System.out.println(TOTAL_INCOME + ". Total income");
        System.out.println(EXIT_CHOICE + ". Exit");
        System.out.print("Enter your choice: ");
    }

    public static boolean processUserChoice() {
        int choice = ConsoleReader.getInstance().readInteger();
        switch (choice) {
            case Menu.VIEW_TRANSACTIONS:
                TransactionService.viewRecentTransactions();
                return false;
            case Menu.ADD_NEW_TRANSACTION:
                CategoryService.viewCategories();
                TransactionService.addTransaction();
                return false;
            case Menu.EDIT_TRANSACTION:
                TransactionService.viewAllTransactions();
                TransactionService.editTransaction();
                return false;
            case Menu.DELETE_TRANSACTION:
                TransactionService.viewAllTransactions();
                TransactionService.deleteTransaction();
                return false;
            case Menu.VIEW_CATEGORIES:
                CategoryService.viewCategories();
                return false;
            case Menu.ADD_NEW_CATEGORY:
                CategoryService.viewCategories();
                CategoryService.addNewCategory();
                return false;
            case Menu.DELETE_CATEGORY:
                CategoryService.viewCategories();
                CategoryService.deleteCategory();
                return false;
            case Menu.SET_BUDGET:
                BudgetService.setBudget();
                return false;
            case Menu.TRACK_PROGRESS:
                BudgetService.trackProgress();
                return false;
            case Menu.TOTAL_INCOME:
                TransactionService.viewTotalIncome();
                return true;
            case Menu.EXIT_CHOICE:
                System.out.println("Exiting MyExpenseApp Application. Goodbye!");
                return true;
            default:
                System.out.println("Invalid choice. Please enter a number from 0 to 9.");
                return false;
        }
    }

}
