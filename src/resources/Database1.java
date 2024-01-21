package resources;

import models.Transaction;
import models1.Category;

import java.util.ArrayList;

public class Database1 {

    private static Database1 instance;
    private ArrayList<Transaction> transactions;
    private ArrayList<Category> categories;

    private Database1() {
        transactions = new ArrayList<>();
        categories = new ArrayList<>();
        addPresetCategories();
    }

    public static synchronized Database1 getInstance() {
        if (instance == null)
            instance = new Database1();
        return instance;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void deleteTransaction(Transaction transaction) {
        transactions.remove(transaction);
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    private void addPresetCategories() {
        categories.add(new Category(1,"Salary"));
        categories.add(new Category(2,"Clothes"));
        categories.add(new Category(3, "Fuel"));
        categories.add(new Category(4,"Gifts"));
    }
}
