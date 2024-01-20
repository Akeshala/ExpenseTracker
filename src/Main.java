public class Main {
    public static void main(String[] args) {
        MyExpenseApp app = MyExpenseApp.getInstance();
        boolean exitRequested = false;
        while (!exitRequested) {
            try {
                app.displayMenu();
                exitRequested = app.processUserChoice();
            } catch (Exception e) {
                System.err.println("An error occurred: " + e.getMessage());
            }
        }
    }
}