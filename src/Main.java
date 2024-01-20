import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MyExpenseApp app = MyExpenseApp.getInstance();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                app.displayMenu();

                Integer choice = scanner.nextInt();

                if (choice.equals(8)) {
                    System.out.println("Exiting MyExpenseApp Application. Goodbye!");
                    break;
                }

                app.processUserChoice(choice);
            } catch (Exception e) {
                System.err.println("An error occurred: " + e.getMessage());
            }
        }

        scanner.close();
    }
}