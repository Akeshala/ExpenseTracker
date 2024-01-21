package services;

import models.BudgetCategory;
import resources.Database;
import utils.ConsoleReader;

import java.util.ArrayList;

public class CategoryService {
    private Database database;

    public CategoryService() {
        this.database = Database.getInstance();
    }

    public void viewCategories() {
        System.out.println("Budget Categories:");
        ArrayList<BudgetCategory> categories = database.getBudgetCategories();
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i).getName() + ": Rs." + categories.get(i).getAmount());
        }

        System.out.print("Enter the number of the category to reset the budget (Enter 0 to skip the Menu): ");
        int categoryNumber = ConsoleReader.getInstance().readInteger();

        if (categoryNumber > 0 && categoryNumber <= categories.size()) {
            BudgetCategory selectedCategory = categories.get(categoryNumber - 1);
            selectedCategory.setAmount(0);

            System.out.println("Budget for " + selectedCategory.getName() + " reset to Rs.0.");
        } else if (categoryNumber != 0) {
            System.out.println("Invalid category number. Please enter a valid number.");
        }
    }

    public void addNewCategory() {
        ConsoleReader reader = ConsoleReader.getInstance();
        System.out.print("Enter the name of the new category: ");
        String categoryName = reader.readString();
        System.out.print("Enter the budget amount for the new category: Rs.");
        double budgetAmount = reader.readDouble();

        BudgetCategory newCategory = new BudgetCategory(categoryName, budgetAmount);
        database.addBudgetCategory(newCategory);

        System.out.println("New category added successfully.");
    }
}

