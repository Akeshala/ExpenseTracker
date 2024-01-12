import java.util.ArrayList;

public class Database {
    private ArrayList<Transaction> transactions;
    private ArrayList<BudgetCategory> budgetCategories;

    public Database() {
        transactions = new ArrayList<>();
        budgetCategories = new ArrayList<>();
        addPresetTransactions();
        addPresetCategories();
    }

    public ArrayList<Transaction> getTransactions() {

        return new ArrayList<>(transactions);
    }

    public ArrayList<BudgetCategory> getBudgetCategories() {

        return new ArrayList<>(budgetCategories);
    }

    public void addTransaction(Transaction transaction) {

        transactions.add(transaction);
    }

    public void addBudgetCategory(BudgetCategory category) {

        budgetCategories.add(category);
    }

    public void deleteTransaction(Transaction transaction) {

        transactions.remove(transaction);
    }

    private void addPresetCategories() {
        budgetCategories.add(new BudgetCategory("Salary", 500000));
        budgetCategories.add(new BudgetCategory("Clothes", 10000));
        budgetCategories.add(new BudgetCategory("Fuel", 50000));
        budgetCategories.add(new BudgetCategory("Gifts", 10000));
        budgetCategories.add(new BudgetCategory("Shopping", 50000));
        budgetCategories.add(new BudgetCategory("Kids", 15000));
        budgetCategories.add(new BudgetCategory("Sports", 10000));
        budgetCategories.add(new BudgetCategory("Travel", 12000));
        budgetCategories.add(new BudgetCategory("Entertainment", 10000));
    }

    private void addPresetTransactions() {
        transactions.add(new Transaction("Salary", 500000.0, true, false, "Monthly salary"));
        transactions.add(new Transaction("Clothes", 5000.0, false, false, "New shirt"));
        transactions.add(new Transaction("Fuel", 5000.0, false, true, "Weekly fuel"));
        transactions.add(new Transaction("Gifts", 3000.0, false, false, "Birthday gift"));
        transactions.add(new Transaction("Shopping", 1500.0, false, false, "Groceries"));
        transactions.add(new Transaction("Kids", 2000.0, false, false, "Toys for kids"));
        transactions.add(new Transaction("Sports", 4000.0, false, false, "Gym membership"));
        transactions.add(new Transaction("Travel", 2000.0, false, false, "Weekend getaway"));
        transactions.add(new Transaction("Entertainment", 2000.0, false, true, "Movie night"));
    }
}
