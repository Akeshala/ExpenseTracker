public class Main {
    public static void main(String[] args) {
        boolean exitRequested = false;
        while (!exitRequested) {
            try {
                Menu.display();
                exitRequested = Menu.processUserChoice();
            } catch (Exception e) {
                System.err.println("An error occurred: " + e.getMessage());
            }
        }
    }
}