package services;

import factory.CategoryFactory;
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
        for (Category category : categories) {
            int budgetID = category.getBudgetID();
            Budget budget = DatabaseHandler.getBudgetByID(budgetID);
            String budgetDisplayAmount = Money.ZERO;
            if (budget != null) {
                budgetDisplayAmount = budget.getDisplayAmount();
            }
            System.out.println(category.getId() + ". " + category.getName() + ": " + budgetDisplayAmount);
        }
        System.out.println();
    }

    public static void addNewCategory() {
        ConsoleReader reader = ConsoleReader.getInstance();
        System.out.print("Enter the name of the new category: ");
        String categoryName = reader.readString();
        DatabaseHandler.addCategory(CategoryFactory.getCategory(categoryName));
        System.out.println("New category added successfully.\n");
    }
}

