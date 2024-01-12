import java.util.ArrayList;
import java.util.Scanner;

public class TransactionManager {
    private Database database;
    public TransactionManager() {
        this.database = new Database();
    }

    public void viewRecentTransactions() {
        System.out.println("Recent Transactions:");
        ArrayList<Transaction> transactions = database.getTransactions();
        for (int i = 0; i < transactions.size(); i++) {
            System.out.println((i + 1) + ". " + getTransactionDetails(transactions.get(i)));
        }
    }

    public void addTransaction() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        System.out.print("Enter amount: Rs.");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Is it an income? (true/false): ");
        boolean isIncome = scanner.nextBoolean();
        scanner.nextLine();
        System.out.print("Is it recurring? (true/false): ");
        boolean isRecurring = scanner.nextBoolean();
        scanner.nextLine();
        System.out.print("Enter note (optional): ");
        String note = scanner.nextLine();

        Transaction newTransaction = new Transaction(category, amount, isIncome, isRecurring, note);
        database.addTransaction(newTransaction);

        System.out.println("Transaction added successfully.");
    }
    public void editOrDeleteTransaction() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Existing Transactions:");
        ArrayList<Transaction> transactions = database.getTransactions();
        for (int i = 0; i < transactions.size(); i++) {
            System.out.println((i + 1) + ". " + getTransactionDetails(transactions.get(i)));
        }

        System.out.print("Enter the number of the transaction to edit or delete: ");
        int transactionNumber = scanner.nextInt();

        if (transactionNumber >= 1 && transactionNumber <= transactions.size()) {
            Transaction selectedTransaction = transactions.get(transactionNumber - 1);

            System.out.println("Selected Transaction: " + getTransactionDetails(selectedTransaction));
            System.out.println("1. Edit Transaction");
            System.out.println("2. Delete Transaction");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

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
        Scanner scanner = new Scanner(System.in);

        System.out.println("Editing Transaction:");
        System.out.print("Enter new category (current: " + transaction.getCategory() + "): ");
        String newCategory = scanner.nextLine();
        System.out.print("Enter new amount (current: Rs." + transaction.getAmount() + "): Rs.");
        double newAmount = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Is it an income? (true/false) (current: " + transaction.isIncome() + "): ");
        boolean newIsIncome = scanner.nextBoolean();
        scanner.nextLine();
        System.out.print("Is it recurring? (true/false) (current: " + transaction.isRecurring() + "): ");
        boolean newIsRecurring = scanner.nextBoolean();
        scanner.nextLine();
        System.out.print("Enter new note (current: " + transaction.getNote() + "): ");
        String newNote = scanner.nextLine();

        transaction.setCategory(newCategory);
        transaction.setAmount(newAmount);
        transaction.setIsIncome(newIsIncome);
        transaction.setIsRecurring(newIsRecurring);
        transaction.setNote(newNote);

        System.out.println("Transaction edited successfully.");
    }

    private void deleteTransaction(Transaction transaction) {
        database.deleteTransaction(transaction);
        System.out.println("Transaction deleted successfully.");
    }
}
