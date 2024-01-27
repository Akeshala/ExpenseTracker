package org.asd.services;

import org.asd.factory.ExpenseTransactionFactory;
import org.asd.factory.IncomeTransactionFactory;
import org.asd.factory.TransactionFactory;
import org.asd.models.Category;
import org.asd.models.Income;
import org.asd.utils.Money;
import org.asd.models.Transaction;
import org.asd.utils.ConsoleReader;
import org.asd.utils.DatabaseHandler;

import java.util.ArrayList;

public class TransactionService {

    public static void viewRecentTransactions() {
        System.out.println("Recent Transactions:");
        ArrayList<Transaction> transactions = DatabaseHandler.getTransactions();
        viewTransactions(transactions, 3);
    }

    public static void viewAllTransactions() {
        System.out.println("All Transactions:");
        ArrayList<Transaction> transactions = DatabaseHandler.getTransactions();
        viewTransactions(transactions, transactions.size());
    }

    public static void addTransaction() {
        ConsoleReader reader = ConsoleReader.getInstance();
        System.out.print("Enter category ID: ");
        int categoryID = reader.readInteger();
        System.out.print("Enter amount: Rs.");
        double amount = reader.readDouble();
        System.out.print("Is it an income? (true/false): ");
        boolean isIncome = reader.readBoolean();
        System.out.print("Is it recurring? (true/false): ");
        boolean isRecurring = reader.readBoolean();
        System.out.print("Enter note (optional): ");
        String note = reader.readString();

        executeAddTransaction(isIncome, amount, categoryID, note, isRecurring);
    }

    public static void editTransaction() {
        System.out.print("Enter the ID of the transaction to edit: ");
        ConsoleReader reader = ConsoleReader.getInstance();
        int transactionID = reader.readInteger();

        Transaction transaction = DatabaseHandler.getTransactionByID(transactionID);
        if (transaction == null) {
            System.out.println("Invalid transaction ID. Please enter a valid ID.\n");
            return;
        }
        System.out.println("Editing Transaction:" + transactionID);

        System.out.print("Enter new amount (current: " + Money.getFormattedAmount(transaction.getAmount()) + "): Rs.");
        double newAmount = reader.readDouble();

        System.out.print("Is it recurring? (true/false) (current: " + transaction.getIsRecurring() + "): ");
        boolean newIsRecurring = reader.readBoolean();

        System.out.print("Enter new note (current: " + transaction.getNote() + "): ");
        String newNote = reader.readString();

        int categoryID = transaction.getCategoryID();
        Category category = DatabaseHandler.getCategoryByID(categoryID);
        System.out.print("Enter new category ID (current: " + category.getName() + "): ");
        int newCategoryID = reader.readInteger();

        executeEditTransaction(newAmount, newIsRecurring, newNote, newCategoryID, transaction);
    }

    public static void deleteTransaction() {
        System.out.print("Enter the ID of the transaction to delete: ");
        int transactionID = ConsoleReader.getInstance().readInteger();

        Transaction transaction = DatabaseHandler.getTransactionByID(transactionID);
        if (transaction == null) {
            System.out.println("Invalid transaction ID. Please enter a valid ID.\n");
            return;
        }
        System.out.println("Editing Transaction:" + transactionID);

        DatabaseHandler.deleteTransaction(transactionID);
        System.out.println("Transaction:" + transactionID + " deleted successfully.\n");
    }

    private static void viewTransactions(ArrayList<Transaction> transactions, int limit) {
        int startIndex = Math.max(0, transactions.size() - limit);
        for (int i = transactions.size() - 1; i >= startIndex; i--) {
            Transaction transaction = transactions.get(i);
            Category category = DatabaseHandler.getCategoryByID(transaction.getCategoryID());
            System.out.println(transaction.getId() + ". " + category.getName() + ": " + transaction.getDetails());
        }
        System.out.println();
    }

    private static void executeEditTransaction(
            double amount,
            boolean recurring,
            String note,
            int categoryID,
            Transaction transaction
    ){
        transaction.setAmount(amount);
        transaction.setIsRecurring(recurring);
        transaction.setNote(note);
        Category newCategory = DatabaseHandler.getCategoryByID(categoryID);
        int transactionID = transaction.getId();
        if(newCategory == null) {
            System.out.println("Invalid category ID!");
            System.out.println("Editing transaction:" + transactionID + " failed.\n");
        } else {
            transaction.setCategoryID(categoryID);
            DatabaseHandler.setTransaction(transactionID, transaction);
            System.out.println("Transaction:" + transactionID + " edited successfully.\n");
        }
    }

    private static void executeAddTransaction(
            boolean isIncome,
            double amount,
            int categoryID,
            String note,
            boolean isRecurring
    ){
        if (isIncome) {
            IncomeTransactionFactory.init();
        } else {
            ExpenseTransactionFactory.init();
        }
        TransactionFactory transactionFactory =  TransactionFactory.getFactory();
        Transaction newTransaction = transactionFactory.getTransaction(
                amount,
                categoryID,
                note,
                isRecurring
        );

        DatabaseHandler.addTransaction(newTransaction);
        System.out.println("Transaction was added successfully.\n");
    }

    public static void viewTotalIncome(){
        ArrayList<Transaction> transactions = DatabaseHandler.getTransactions();
        double totalIncome = calculateTotalIncome(transactions);

        System.out.println("Total income " + Money.getFormattedAmount(totalIncome));
    }

    static double calculateTotalIncome(ArrayList<Transaction> transactions) {
        return transactions.stream()
                .filter(transaction -> transaction instanceof Income)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }
}
