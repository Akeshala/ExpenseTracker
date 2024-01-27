package org.asd.services;

import org.asd.factory.CategoryFactory;
import org.asd.models.Budget;
import org.asd.models.Category;
import org.asd.utils.Money;
import org.asd.utils.ConsoleReader;
import org.asd.utils.DatabaseHandler;

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
                budgetDisplayAmount = Money.getFormattedAmount(budget.getAmount());
            }
            System.out.println(category.getId() + ". " + category.getName() + ": " + budgetDisplayAmount);
        }
        System.out.println();
    }

    public static void addNewCategory() {
        ConsoleReader reader = ConsoleReader.getInstance();
        System.out.print("Enter the name of the new category you want to add: ");
        String categoryName = reader.readString();
        DatabaseHandler.addCategory(CategoryFactory.getCategory(categoryName));
        System.out.println("New category added successfully.\n");
    }

    public static void deleteCategory() {
        ConsoleReader reader = ConsoleReader.getInstance();
        System.out.print("Enter the ID of the category to delete: ");
        int categoryID = reader.readInteger();
        Category category = DatabaseHandler.getCategoryByID(categoryID);
        if (category == null) {
            System.out.println("Invalid category ID!.\n");
            return;
        }

        int budgetID = category.getBudgetID();
        Budget budget = DatabaseHandler.getBudgetByID(budgetID);
        if (budget != null) {
            DatabaseHandler.deleteBudget(budgetID);
            System.out.println("Budget attached to category deleted successfully.");
        }

        DatabaseHandler.deleteCategory(categoryID);
        System.out.println("category deleted successfully.\n");
    }
}

