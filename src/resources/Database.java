package resources;

import models.Transaction;
import models.Budget;
import models.Category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Database {

    private static Database instance;
    private final Map<Integer, Transaction> transactions;
    private final Map<Integer, Category> categories;
    private final Map<Integer, Budget> budgets;
    private int transactionCounter = 0;
    private int categoryCounter = 0;
    private int budgetCounter = 0;

    private Database() {
        transactions = new HashMap<>();
        categories = new HashMap<>();
        budgets = new HashMap<>();
        addPresetCategories();
    }

    public static synchronized Database getInstance() {
        if (instance == null) instance = new Database();
        return instance;
    }

    public ArrayList<Transaction> getTransactions() {
        return new ArrayList<>(transactions.values());
    }

    public Transaction getTransactionByID(int id) {
        return transactions.get(id);
    }

    public void addTransaction(Transaction transaction) {
        int id = transactionCounter++;
        transaction.setId(id);
        transactions.put(id, transaction);
    }

    public void setTransaction(int id, Transaction transaction) {
        transactions.put(id, transaction);
    }

    public void deleteTransaction(int id) {
        transactions.remove(id);
    }

    public ArrayList<Category> getCategories() {
        return new ArrayList<>(categories.values());
    }

    public Category getCategoryByID(int id) {
        return categories.get(id);
    }

    public void addCategory(Category category) {
        int id = categoryCounter++;
        category.setId(id);
        categories.put(id, category);
    }

    public void deleteCategory(int id) {
        categories.remove(id);
    }

    private void addPresetCategories() {
        addCategory(new Category("Salary"));
        addCategory(new Category("Clothes"));
        addCategory(new Category("Fuel"));
        addCategory(new Category("Gifts"));
    }

    public ArrayList<Budget> getBudgets() {
        return new ArrayList<>(budgets.values());
    }

    public void addBudget(Budget budget) {
        int id = budgetCounter++;
        budget.setId(id);
        budgets.put(id, budget);
    }

    public Budget getBudgetByID(int id) {
        return budgets.get(id);
    }

    public void setBudget(int id, Budget budget) {
        budgets.put(id, budget);
    }
}
