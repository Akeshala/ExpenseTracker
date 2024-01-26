package org.asd.utils;

import org.asd.models.Budget;
import org.asd.models.Category;
import org.asd.models.Transaction;
import org.asd.resources.Database;

import java.util.ArrayList;

public class DatabaseHandler {

    public static ArrayList<Transaction> getTransactions() {
        Database database = Database.getInstance();
        return database.getTransactions();
    }

    public static Transaction getTransactionByID(int id) {
        Database database = Database.getInstance();
        return database.getTransactionByID(id);
    }
    public static void addTransaction(Transaction transaction) {
        Database database = Database.getInstance();
        database.addTransaction(transaction);
    }
    public static void setTransaction(int id, Transaction transaction) {
        Database database = Database.getInstance();
        database.setTransaction(id, transaction);
    }

    public static void deleteTransaction(int id) {
        Database database = Database.getInstance();
        database.deleteTransaction(id);
    }

    public static ArrayList<Category> getCategories() {
        Database database = Database.getInstance();
        return database.getCategories();
    }

    public static Category getCategoryByID(int id) {
        Database database = Database.getInstance();
        return database.getCategoryByID(id);
    }

    public static void addCategory(Category category) {
        Database database = Database.getInstance();
        database.addCategory(category);
    }

    public static void deleteCategory(int id) {
        Database database = Database.getInstance();
        database.deleteCategory(id);
    }

    public static void editCategory(int id, Category category) {
        Database database = Database.getInstance();
        database.setCategory(id, category);
    }

    public static ArrayList<Budget> getBudgets() {
        Database database = Database.getInstance();
        return database.getBudgets();
    }

    public static int addBudget(Budget budget) {
        Database database = Database.getInstance();
        return database.addBudget(budget);
    }

    public static Budget getBudgetByID(int id) {
        Database database = Database.getInstance();
        return database.getBudgetByID(id);
    }

    public static void setBudget(int id, Budget budget) {
        Database database = Database.getInstance();
        database.setBudget(id, budget);
    }

    public static void deleteBudget(int id) {
        Database database = Database.getInstance();
        database.deleteBudget(id);
    }
}
