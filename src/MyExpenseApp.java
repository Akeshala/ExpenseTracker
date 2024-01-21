import services.BudgetService;
import services.CategoryService;
import services.TransactionService;
import utils.ConsoleReader;

public class MyExpenseApp {

    private static MyExpenseApp instance;
    private TransactionService transactionService;
    private CategoryService categoryService;
    private BudgetService budgetService;

    private MyExpenseApp() {
        transactionService = new TransactionService();
        categoryService = new CategoryService();
        budgetService = new BudgetService();
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
                transactionService.viewRecentTransactions();
                return false;
            case Menu.ADD_NEW_TRANSACTION:
                transactionService.addTransaction();
                return false;
            case Menu.EDIT_TRANSACTION:
                transactionService.editOrDeleteTransaction();
                return false;
            case Menu.VIEW_CATEGORIES:
                categoryService.viewCategories();
                return false;
            case Menu.ADD_NEW_CATEGORY:
                categoryService.addNewCategory();
                return false;
            case Menu.SET_BUDGET:
                budgetService.setBudget();
                return false;
            case Menu.TRACK_PROGRESS:
                budgetService.trackProgress();
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
