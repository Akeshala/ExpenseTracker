public class Menu {

    public static final int VIEW_TRANSACTIONS = 1;
    public static final int ADD_NEW_TRANSACTION = 2;
    public static final int EDIT_TRANSACTION = 3;
    public static final int VIEW_CATEGORIES = 4;
    public static final int ADD_NEW_CATEGORY = 5;
    public static final int SET_BUDGET = 6;
    public static final int TRACK_PROGRESS = 7;
    public static final int EXIT_CHOICE = 8;

    public static void display() {
        System.out.println("Expense Tracker Menu:");
        System.out.println(VIEW_TRANSACTIONS + ". View Recent Transactions");
        System.out.println(ADD_NEW_TRANSACTION + ". Add New models.Transaction");
        System.out.println(EDIT_TRANSACTION + ". Edit/Delete models.Transaction");
        System.out.println(VIEW_CATEGORIES + ". View Categories");
        System.out.println(ADD_NEW_CATEGORY + ". Add New Category");
        System.out.println(SET_BUDGET + ". Set Budget");
        System.out.println(TRACK_PROGRESS + ". Track Progress");
        System.out.println(EXIT_CHOICE + ". Exit");
        System.out.print("Enter your choice: ");
    }

}
