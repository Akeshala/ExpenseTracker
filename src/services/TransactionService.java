package services;

import factory.ExpenseTransactionFactory;
import factory.IncomeTransactionFactory;
import factory.TransactionFactory;
import models.Category;
import models.Transaction;
import utils.ConsoleReader;
import utils.DatabaseHandler;

import java.util.ArrayList;
import static java.lang.Math.min;

public class TransactionService {

    public static void viewRecentTransactions() {
        System.out.println("Recent Transactions:");
        ArrayList<Transaction> transactions = DatabaseHandler.getTransactions();
        viewTransactions(transactions, 5);
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

        System.out.print("Enter new amount (current: " + transaction.getAmount().getRupee() + "): Rs.");
        double newAmount = reader.readDouble();
        transaction.setAmount(newAmount);

        System.out.print("Is it recurring? (true/false) (current: " + transaction.getIsRecurring() + "): ");
        boolean newIsRecurring = reader.readBoolean();
        transaction.setIsRecurring(newIsRecurring);

        System.out.print("Enter new note (current: " + transaction.getNote() + "): ");
        String newNote = reader.readString();
        transaction.setNote(newNote);

        int categoryID = transaction.getCategoryID();
        Category category = DatabaseHandler.getCategoryByID(categoryID);
        System.out.print("Enter new category ID (current: " + category.getName() + "): ");
        int newCategoryID = reader.readInteger();
        Category newCategory = DatabaseHandler.getCategoryByID(newCategoryID);
        if(newCategory == null) {
            System.out.println("Invalid category ID!");
            System.out.println("Editing transaction:" + transactionID + " failed.\n");
        } else {
            transaction.setCategoryID(newCategoryID);
            DatabaseHandler.setTransaction(transactionID, transaction);
            System.out.println("Transaction:" + transactionID + " edited successfully.\n");
        }
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

    public static void viewTransactions(ArrayList<Transaction> transactions, int limit) {
        for (int i = 0; i < min(transactions.size(), limit); i++) {
            Transaction transaction = transactions.get(i);
            Category category = DatabaseHandler.getCategoryByID(transaction.getCategoryID());
            System.out.println(transaction.getId() + ". " + category.getName() + ": " + transaction.getDetails());
        }
        System.out.println();
    }
}
