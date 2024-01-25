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
        for (int i = 0; i < min(transactions.size(), 5); i++) {
            Transaction transaction = transactions.get(i);
            Category category = DatabaseHandler.getCategoryByID(transaction.getCategoryID());
            System.out.println(transaction.getId() + ". " + category.getName() + ": " +
                    transaction.getConcatenatedString());
        }
        System.out.println();
    }

    public static void addTransaction() {
        ConsoleReader reader = ConsoleReader.getInstance();
        System.out.print("Enter category: ");
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
    public static void editOrDeleteTransaction() {
        ArrayList<Transaction> transactions = DatabaseHandler.getTransactions();
        System.out.print("Enter the ID of the transaction to edit or delete: ");
        int transactionID = ConsoleReader.getInstance().readInteger();

        if (transactionID >= 1 && transactionID <= transactions.size()) { // check logic
            Transaction selectedTransaction = DatabaseHandler.getTransactionByID(transactionID);

            System.out.println("Selected Transaction: " + selectedTransaction.toString());
            System.out.println("1. Edit transaction");
            System.out.println("2. Delete transaction");
            System.out.print("Enter your choice: ");
            int choice = ConsoleReader.getInstance().readInteger();

            switch (choice) {
                case 1:
                    editTransaction(transactionID);
                    break;
                case 2:
                    deleteTransaction(transactionID);
                    break;
                default:
                    System.out.println("Invalid choice. No changes made to the transaction.\n");
            }
        } else {
            System.out.println("Invalid transaction number. Please enter a valid number.\n");
        }
    }

    private static void editTransaction(int id) {
        ConsoleReader reader = ConsoleReader.getInstance();
        Transaction transaction = DatabaseHandler.getTransactionByID(id);

        System.out.println("Editing Transaction:" + id);

        System.out.print("Enter new amount (current: " + transaction.getDisplayAmount() + "): Rs.");
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
            System.out.println("Editing transaction:" + id + " failed.\n");
        } else {
            DatabaseHandler.setTransaction(id, transaction);
            System.out.println("Transaction:" + id + " edited successfully.\n");
        }
    }

    private static void deleteTransaction(int id) {
        DatabaseHandler.deleteTransaction(id);
        System.out.println("Transaction:" + id + " deleted successfully.\n");
    }
}
