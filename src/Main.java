public class Main {
    public static void main(String[] args) {
        MyExpenseApp app = MyExpenseApp.getInstance();
        while (true) {
            try {
                app.displayMenu();
            } catch (Exception e) {
                System.err.println("An error occurred: " + e.getMessage());
            }
        }
    }
}