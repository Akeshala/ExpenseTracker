package org.asd.models;

public class Category {
    private Integer id;
    private String name;
    private Integer budgetID;
    private static final Integer DEFAULT_BUDGET_ID = -1;

    public Category(String name) {
        setName(name);
        setBudgetID(DEFAULT_BUDGET_ID);
    }
    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getId(){
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getBudgetID() {
        return this.budgetID;
    }
    public void setBudgetID(Integer budgetID) {
        this.budgetID = budgetID;
    }
}