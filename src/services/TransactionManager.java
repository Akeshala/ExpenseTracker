package services;

import models.Transaction;
import resources.Database;
import utils.ConsoleReader;

import java.util.ArrayList;

public class TransactionManager {
    private Database database;
    public TransactionManager() {
        this.database = Database.getInstance();;
    }

    public void viewRecentTransactions() {
        System.out.println("Recent Transactions:");
        ArrayList<Transaction> transactions = database.getTransactions();
        for (int i = 0; i < transactions.size(); i++) {
            System.out.println((i + 1) + ". " + getTransactionDetails(transactions.get(i)));
        }
    }

    public void addTransaction() {
        ConsoleReader reader = ConsoleReader.getInstance();
        System.out.print("Enter category: ");
        String category = reader.readString();
        System.out.print("Enter amount: Rs.");
        double amount = reader.readDouble();
        System.out.print("Is it an income? (true/false): ");
        boolean isIncome = reader.readBoolean();
        System.out.print("Is it recurring? (true/false): ");
        boolean isRecurring = reader.readBoolean();
        System.out.print("Enter note (optional): ");
        String note = reader.readString();

        Transaction newTransaction = new Transaction(category, amount, isIncome, isRecurring, note);
        database.addTransaction(newTransaction);

        System.out.println("models.Transaction added successfully.");
    }
    public void editOrDeleteTransaction() {
        System.out.println("Existing Transactions:");
        ArrayList<Transaction> transactions = database.getTransactions();
        for (int i = 0; i < transactions.size(); i++) {
            System.out.println((i + 1) + ". " + getTransactionDetails(transactions.get(i)));
        }

        System.out.print("Enter the number of the transaction to edit or delete: ");
        int transactionNumber = ConsoleReader.getInstance().readInteger();

        if (transactionNumber >= 1 && transactionNumber <= transactions.size()) {
            Transaction selectedTransaction = transactions.get(transactionNumber - 1);

            System.out.println("Selected models.Transaction: " + getTransactionDetails(selectedTransaction));
            System.out.println("1. Edit models.Transaction");
            System.out.println("2. Delete models.Transaction");
            System.out.print("Enter your choice: ");
            int choice = ConsoleReader.getInstance().readInteger();

            switch (choice) {
                case 1:
                    editTransaction(selectedTransaction);
                    break;
                case 2:
                    deleteTransaction(selectedTransaction);
                    break;
                default:
                    System.out.println("Invalid choice. No changes made to the transaction.");
            }
        } else {
            System.out.println("Invalid transaction number. Please enter a valid number.");
        }
    }

    private String getTransactionDetails(Transaction transaction) {
        return transaction.getCategory() + ": Rs." + transaction.getAmount() +
                " (Income: " + transaction.isIncome() + ", Recurring: " + transaction.isRecurring() + ")";
    }

    private void editTransaction(Transaction transaction) {
        ConsoleReader reader = ConsoleReader.getInstance();

        System.out.println("Editing models.Transaction:");
        System.out.print("Enter new category (current: " + transaction.getCategory() + "): ");
        String newCategory = reader.readString();
        System.out.print("Enter new amount (current: Rs." + transaction.getAmount() + "): Rs.");
        double newAmount = reader.readDouble();
        System.out.print("Is it an income? (true/false) (current: " + transaction.isIncome() + "): ");
        boolean newIsIncome = reader.readBoolean();
        System.out.print("Is it recurring? (true/false) (current: " + transaction.isRecurring() + "): ");
        boolean newIsRecurring = reader.readBoolean();
        System.out.print("Enter new note (current: " + transaction.getNote() + "): ");
        String newNote = reader.readString();

        transaction.setCategory(newCategory);
        transaction.setAmount(newAmount);
        transaction.setIsIncome(newIsIncome);
        transaction.setIsRecurring(newIsRecurring);
        transaction.setNote(newNote);

        System.out.println("models.Transaction edited successfully.");
    }

    private void deleteTransaction(Transaction transaction) {
        database.deleteTransaction(transaction);
        System.out.println("models.Transaction deleted successfully.");
    }
}
