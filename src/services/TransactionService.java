package services;

import factory.ExpenseTransactionFactory;
import factory.IncomeTransactionFactory;
import factory.TransactionFactory;
import models1.Category;
import models1.Income;
import models1.Money;
import models1.Transaction;
import resources.Database;
import utils.ConsoleReader;

import java.util.ArrayList;
import java.util.Random;

public class TransactionService {
    private Database database;
    public TransactionService() {
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

        if (isIncome) {
            IncomeTransactionFactory.init();
        } else {
            ExpenseTransactionFactory.init();
        }
        TransactionFactory transactionFactory =  TransactionFactory.getFactory();
        Transaction newTransaction = transactionFactory.getTransaction(
                (int) amount, // id
                new Money(amount),
                new Category(34, category),
                note,
                isRecurring
        );
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
        return transaction.getCategory() + ": " + transaction.getDisplayAmount() +
                " (Income: " + (transaction instanceof Income) + ", Recurring: " + transaction.getIsRecurring() + ")";
    }

    private void editTransaction(Transaction transaction) {
        ConsoleReader reader = ConsoleReader.getInstance();

        System.out.println("Editing models.Transaction:");
        System.out.print("Enter new category (current: " + transaction.getCategory() + "): ");
        String newCategory = reader.readString();
        System.out.print("Enter new amount (current: " + transaction.getDisplayAmount() + "): Rs.");
        double newAmount = reader.readDouble();
        System.out.print("Is it recurring? (true/false) (current: " + transaction.getIsRecurring() + "): ");
        boolean newIsRecurring = reader.readBoolean();
        System.out.print("Enter new note (current: " + transaction.getNote() + "): ");
        String newNote = reader.readString();

        Random random = new Random();
        int randomNumber = random.nextInt(1000 - 5 + 1) + 5;
        transaction.setCategory(new Category(randomNumber, newCategory)); // set by ID

        Money money = new Money(newAmount);
        transaction.setAmount(money);
        transaction.setIsRecurring(newIsRecurring);
        transaction.setNote(newNote);

        System.out.println("models.Transaction edited successfully.");
    }

    private void deleteTransaction(Transaction transaction) {
        database.deleteTransaction(transaction);
        System.out.println("models.Transaction deleted successfully.");
    }
}
