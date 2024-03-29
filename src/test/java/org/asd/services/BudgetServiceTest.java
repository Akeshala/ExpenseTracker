package org.asd.services;

import org.asd.models.Expense;
import org.asd.models.Income;
import org.asd.models.Transaction;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BudgetServiceTest {

    @Test
    public void testCalculateTotalSpent_emptyTransactions() {
        int categoryID = 1;
        ArrayList<Transaction> transactions = new ArrayList<>();
        double totalSpent = BudgetService.calculateTotalSpentByCategory(categoryID, transactions);
        assertEquals(0, totalSpent, 0);
    }

    @Test
    public void testCalculateTotalSpent_oneExpenseTransaction() {
        int categoryID = 1;
        ArrayList<Transaction> transactions = new ArrayList<>();
        Transaction transaction = new Expense(100.00, 1, "art1", true);
        transactions.add(transaction);
        double totalSpent = BudgetService.calculateTotalSpentByCategory(categoryID, transactions);
        assertEquals(100, totalSpent, 0);
    }

    @Test
    public void testCalculateTotalSpent_multipleExpenseTransactions() {
        int categoryID = 1;
        ArrayList<Transaction> transactions = new ArrayList<>();
        Transaction transaction1 = new Expense(200.00, 2, "art2", true);
        Transaction transaction2 = new Expense(300.00, 3, "art3", false);
        transactions.add(transaction1);
        transactions.add(transaction2);
        double totalSpent = BudgetService.calculateTotalSpentByCategory(categoryID, transactions);
        assertEquals(0, totalSpent, 0);
    }

    @Test
    public void testCalculateTotalSpent_oneIncomeTransaction() {
        int categoryID = 1;
        ArrayList<Transaction> transactions = new ArrayList<>();
        Transaction transaction1 = new Income(100.00, 4, "salary12", false);
        transactions.add(transaction1);
        double totalSpent = BudgetService.calculateTotalSpentByCategory(categoryID, transactions);
        assertEquals(0, totalSpent, 0);
    }
}
