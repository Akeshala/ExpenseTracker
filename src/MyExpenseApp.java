import utils.ConsoleReader;

public class MyExpenseApp {

    private static MyExpenseApp instance;
    private TransactionManager transactionManager;
    private CategoryManager categoryManager;
    private BudgetTracker budgetTracker;

    private MyExpenseApp() {
        transactionManager = new TransactionManager();
        categoryManager = new CategoryManager();
        budgetTracker = new BudgetTracker();
    }

    public static synchronized MyExpenseApp getInstance() {
        if (instance == null)
            instance = new MyExpenseApp();
        return instance;
    }

    public void displayMenu() {
        Menu.display();
    }

    public boolean processUserChoice() {
        int choice = ConsoleReader.getInstance().readInteger();
        switch (choice) {
            case Menu.VIEW_TRANSACTIONS:
                transactionManager.viewRecentTransactions();
                return false;
            case Menu.ADD_NEW_TRANSACTION:
                transactionManager.addTransaction();
                return false;
            case Menu.EDIT_TRANSACTION:
                transactionManager.editOrDeleteTransaction();
                return false;
            case Menu.VIEW_CATEGORIES:
                categoryManager.viewCategories();
                return false;
            case Menu.ADD_NEW_CATEGORY:
                categoryManager.addNewCategory();
                return false;
            case Menu.SET_BUDGET:
                budgetTracker.setBudget();
                return false;
            case Menu.TRACK_PROGRESS:
                budgetTracker.trackProgress();
                return false;
            case Menu.EXIT_CHOICE:
                System.out.println("Exiting MyExpenseApp Application. Goodbye!");
                return true;
            default:
                System.out.println("Invalid choice. Please enter a number from 1 to 8.");
                return false;
        }
    }
}
