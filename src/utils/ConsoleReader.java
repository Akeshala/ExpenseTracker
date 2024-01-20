package utils;

import java.util.Scanner;

public class ConsoleReader {

    private static ConsoleReader instance;
    private static Scanner scanner;

    private ConsoleReader() {
        scanner = new Scanner(System.in);
    }

    public static synchronized ConsoleReader getInstance() {
        if (instance == null)
            instance = new ConsoleReader();
        return instance;
    }

    public int readInteger() {
        while (true) {
            System.out.print("Enter your choice: ");
            try {
                return scanner.nextInt();
            } catch (java.util.InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Invalid input!. Please enter a valid integer.");
            }
        }
    }
}
