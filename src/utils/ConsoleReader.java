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
            try {
                return scanner.nextInt();
            } catch (java.util.InputMismatchException e) {
                scanner.nextLine();
                System.out.print("Invalid input!. Please enter a valid integer: ");
            }
        }
    }

    public String readString() {
        while (true) {
            try {
                return scanner.next();
            } catch (java.util.InputMismatchException e) {
                scanner.nextLine();
                System.out.print("Invalid input!. Please enter a valid text: ");
            }
        }
    }

    public double readDouble() {
        while (true) {
            try {
                return scanner.nextDouble();
            } catch (java.util.InputMismatchException e) {
                scanner.nextLine();
                System.out.print("Invalid input!. Please enter a valid number: ");
            }
        }
    }

    public boolean readBoolean() {
        while (true) {
            try {
                return scanner.nextBoolean();
            } catch (java.util.InputMismatchException e) {
                scanner.nextLine();
                System.out.print("Invalid input!. Please enter a valid boolean value: ");
            }
        }
    }
}
