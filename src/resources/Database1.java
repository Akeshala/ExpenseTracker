package resources;

import models1.Transaction;
import models1.BudgetEntry;
import models1.Category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Database1 {

    private static Database1 instance;
    private final Map<Integer, Transaction> transactions;
    private final Map<Integer, Category> categories;
    private final Map<Integer, BudgetEntry> budgetEntries;
    private int transactionCounter = 0;
    private int categoryCounter = 0;
    private int budgetEntryCounter = 0;

    private Database1() {
        transactions = new HashMap<>();
        categories = new HashMap<>();
        budgetEntries = new HashMap<>();
        addPresetCategories();
    }

    public static synchronized Database1 getInstance() {
        if (instance == null) instance = new Database1();
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
}
