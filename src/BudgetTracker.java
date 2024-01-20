import utils.ConsoleReader;

import java.util.ArrayList;
import java.util.Scanner;

public class BudgetTracker {
    private Database database;

    public BudgetTracker() {

        this.database = Database.getInstance();
    }
    public void setBudget() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Budget Categories:");
        ArrayList<BudgetCategory> budgetCategories = database.getBudgetCategories();
        for (int i = 0; i < budgetCategories.size(); i++) {
            System.out.println((i + 1) + ". " + budgetCategories.get(i).getName() + ": Rs." + budgetCategories.get(i).getAmount());
        }

        System.out.print("Enter the number of the category to set the budget: ");
        int categoryNumber = scanner.nextInt();

        if (categoryNumber >= 1 && categoryNumber <= budgetCategories.size()) {
            System.out.print("Enter the new budget amount for the selected category: Rs.");
            double newBudgetAmount = scanner.nextDouble();

            BudgetCategory selectedCategory = budgetCategories.get(categoryNumber - 1);
            selectedCategory.setAmount(newBudgetAmount);

            System.out.println("Budget for " + selectedCategory.getName() + " updated successfully.");
        } else {
            System.out.println("Invalid category number. Please enter a valid number.");
        }
    }

    public void trackProgress() {
        System.out.println("Budget Progress:");

        ArrayList<BudgetCategory> budgetCategories = database.getBudgetCategories();

        for (BudgetCategory category : budgetCategories) {
            double totalSpent = calculateTotalSpent(category.getName());
            double budgetAmount = category.getAmount();

            double progressPercentage = (totalSpent / budgetAmount) * 100;

            System.out.println(category.getName() + ": Spent Rs." + totalSpent + " out of Rs." + budgetAmount + " - Progress: " + progressPercentage + "%");
        }
    }

    private double calculateTotalSpent(String categoryName) {
        double totalSpent = 0;

        for (Transaction transaction : database.getTransactions()) {
            if (transaction.getCategory().equals(categoryName) && transaction.isExpense()) {
                totalSpent += transaction.getAmount();
            }
        }

        return totalSpent;
    }
}
