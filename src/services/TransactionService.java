package services;

import factory.ExpenseTransactionFactory;
import factory.IncomeTransactionFactory;
import factory.TransactionFactory;
import models1.Category;
import models1.Money;
import models1.Transaction;
import resources.Database1;
import utils.ConsoleReader;

import java.util.ArrayList;
import static java.lang.Math.min;

public class TransactionService {

    public static void viewRecentTransactions() {
        Database1 database1 = Database1.getInstance();
        System.out.println("Recent Transactions:");
        ArrayList<Transaction> transactions = database1.getTransactions();
        for (int i = 0; i < min(transactions.size(), 5); i++) {
            System.out.println((i + 1) + ". " + transactions.get(i).toString());
        }
    }

    public static void addTransaction() {
        ConsoleReader reader = ConsoleReader.getInstance();
        Database1 database1 = Database1.getInstance();
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
                new Money(amount),
                categoryID,
                note,
                isRecurring
        );
        database1.addTransaction(newTransaction);

        System.out.println("models.Transaction added successfully.");
    }
    public static void editOrDeleteTransaction() {
        System.out.println("Existing Transactions:");
        Database1 database1 = Database1.getInstance();
        ArrayList<Transaction> transactions = database1.getTransactions();
        for (int i = 0; i < transactions.size(); i++) {
            System.out.println((i + 1) + ". " + transactions.get(i).toString());
        }

        System.out.print("Enter the number of the transaction to edit or delete: ");
        int transactionNumber = ConsoleReader.getInstance().readInteger();

        if (transactionNumber >= 1 && transactionNumber <= transactions.size()) {
            Transaction selectedTransaction = database1.getTransactionByID(transactionNumber);

            System.out.println("Selected models.Transaction: " + selectedTransaction.toString());
            System.out.println("1. Edit models.Transaction");
            System.out.println("2. Delete models.Transaction");
            System.out.print("Enter your choice: ");
            int choice = ConsoleReader.getInstance().readInteger();

            switch (choice) {
                case 1:
                    editTransaction(transactionNumber);
                    break;
                case 2:
                    deleteTransaction(transactionNumber);
                    break;
                default:
                    System.out.println("Invalid choice. No changes made to the transaction.");
            }
        } else {
            System.out.println("Invalid transaction number. Please enter a valid number.");
        }
    }

    private static void editTransaction(int id) {
        ConsoleReader reader = ConsoleReader.getInstance();
        Database1 database1 = Database1.getInstance();
        Transaction transaction = database1.getTransactionByID(id);

        System.out.println("Editing models.Transaction:");
        System.out.print("Enter new category (current: " + transaction.getCategory() + "): ");
        int categoryID = reader.readInteger();
        System.out.print("Enter new amount (current: " + transaction.getDisplayAmount() + "): Rs.");
        double newAmount = reader.readDouble();
        System.out.print("Is it recurring? (true/false) (current: " + transaction.getIsRecurring() + "): ");
        boolean newIsRecurring = reader.readBoolean();
        System.out.print("Enter new note (current: " + transaction.getNote() + "): ");
        String newNote = reader.readString();

        Category newCategory = database1.getCategoryByID(categoryID);
        if (newCategory != null) {
            transaction.setCategory(categoryID);
        } else {
            System.out.println("Invalid category ID!");
        }

        Money money = new Money(newAmount);
        transaction.setAmount(money);
        transaction.setIsRecurring(newIsRecurring);
        transaction.setNote(newNote);
        database1.setTransaction(id, transaction);

        System.out.println("models.Transaction edited successfully.");
    }

    private static void deleteTransaction(int id) {
        Database1 database1 = Database1.getInstance();
        database1.deleteTransaction(id);
        System.out.println("models.Transaction deleted successfully.");
    }
}
