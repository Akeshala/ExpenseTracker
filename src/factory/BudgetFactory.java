package factory;

import models.Budget;

public class BudgetFactory {

    public static Budget getBudget(int categoryID, double budgetAmount){
        return new Budget(categoryID, budgetAmount);
    }
}
