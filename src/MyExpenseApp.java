import java.util.Scanner;

public class MyExpenseApp {
    private TransactionManager transactionManager;
    private CategoryManager categoryManager;
    private BudgetTracker budgetTracker;

    public MyExpenseApp() {
        this.transactionManager = new TransactionManager();
        this.categoryManager = new CategoryManager();
        this.budgetTracker = new BudgetTracker();
    }
    public void displayMenu() {
        System.out.println("Expense Tracker Menu:");
        System.out.println("1. View Recent Transactions");
        System.out.println("2. Add New Transaction");
        System.out.println("3. Edit/Delete Transaction");
        System.out.println("4. View Categories");
        System.out.println("5. Add New Category");
        System.out.println("6. Set Budget");
        System.out.println("7. Track Progress");
        System.out.println("8. Exit");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                transactionManager.viewRecentTransactions();
                break;
            case 2:
                transactionManager.addTransaction();
                break;
            case 3:
                transactionManager.editOrDeleteTransaction();
                break;
            case 4:
                categoryManager.viewCategories();
                break;
            case 5:
                categoryManager.addNewCategory();
                break;
            case 6:
                budgetTracker.setBudget();
                break;
            case 7:
                budgetTracker.trackProgress();
                break;
            case 8:
                System.out.println("Exiting MyExpenseApp Application. Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please enter a number from 1 to 8.");
        }
    }
}
