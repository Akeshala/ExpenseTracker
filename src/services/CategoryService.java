package services;

import models.Budget;
import models.Category;
import models.Money;
import utils.ConsoleReader;
import utils.DatabaseHandler;

import java.util.ArrayList;

public class CategoryService {

    public static void viewCategories() {
        System.out.println("Budget Categories:");
        ArrayList<Category> categories = DatabaseHandler.getCategories();
        for (int i = 0; i < categories.size(); i++) {
            int BudgetID = categories.get(i).getBudgetID();
            Budget Budget = DatabaseHandler.getBudgetByID(BudgetID);
            String budget = Money.ZERO;
            if (Budget != null) {
                budget = Budget.getDisplayAmount();
            }
            System.out.println((i + 1) + ". " + categories.get(i).getName() + ": " + budget);
        }
        System.out.println();
    }

    public static void addNewCategory() {
        ConsoleReader reader = ConsoleReader.getInstance();
        System.out.print("Enter the name of the new category: ");
        String categoryName = reader.readString();
        DatabaseHandler.addCategory(categoryName);
        System.out.println("New category added successfully.\n");
    }
}

