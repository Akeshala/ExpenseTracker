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
        System.out.println(VIEW_TRANSACTIONS + ". View recent transactions");
        System.out.println(ADD_NEW_TRANSACTION + ". Add a new transaction");
        System.out.println(EDIT_TRANSACTION + ". Edit/Delete a transaction");
        System.out.println(VIEW_CATEGORIES + ". View categories");
        System.out.println(ADD_NEW_CATEGORY + ". Add new category");
        System.out.println(SET_BUDGET + ". Set budget");
        System.out.println(TRACK_PROGRESS + ". Track progress");
        System.out.println(EXIT_CHOICE + ". Exit");
        System.out.print("Enter your choice: ");
    }

}
