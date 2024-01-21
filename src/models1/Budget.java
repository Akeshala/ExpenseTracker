package models1;

import java.util.ArrayList;
public class Budget{
    private String name;
    private ArrayList<BudgetEntry> entries;

    public Budget(String name) {
        this.name = name;
        this.entries = new ArrayList<BudgetEntry>();
    }

    public String getName(){
        return name;
    }
    public void addEntry(Integer id, Category category, Money value){
        entries.add(new BudgetEntry(id, category, value));
    }

    public float getCategoryBudget(Category category) {
        float total = 0;
        for (BudgetEntry i: entries){
            if (i.getCategoryType() == category) {
                total = total + i.getTotal();
            }
        }
        return total;
    }
    public Boolean checkCategory(Category category) {
        Boolean isCategoryExits = false;
        for (BudgetEntry i: entries) {
            if (category == i.getCategoryType()) {
                isCategoryExits = true;
                break;
            }
        }
        return isCategoryExits;
    }
}