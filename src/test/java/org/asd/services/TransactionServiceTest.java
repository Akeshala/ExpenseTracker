package org.asd.services;

import org.asd.models.Expense;
import org.asd.models.Income;
import org.asd.models.Transaction;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TransactionServiceTest {

    @Test
    public void calculateTotalIncome_emptyTransactions() {
        ArrayList<Transaction> transactions = new ArrayList<>();
        double totalSpent = TransactionService.calculateTotalIncome(transactions);
        assertEquals(0, totalSpent, 0);
    }

    @Test
    public void calculateTotalIncome_onlyIncomes() {
        ArrayList<Transaction> transactions = new ArrayList<>();
        Transaction transaction1 = new Income(100.00, 4, "salary89", false);
        transactions.add(transaction1);
        double totalSpent = TransactionService.calculateTotalIncome(transactions);
        assertEquals(100, totalSpent, 0);
    }

    @Test
    public void calculateTotalIncome_incomesAndExpenses() {
        ArrayList<Transaction> transactions = new ArrayList<>();
        Transaction transaction1 = new Income(100.00, 4, "salary89", false);
        Transaction transaction2 = new Expense(50.00, 3, "salary1", true);
        Transaction transaction3 = new Income(60.00, 2, "salary5", false);
        transactions.add(transaction1);
        transactions.add(transaction2);
        transactions.add(transaction3);
        double totalSpent = TransactionService.calculateTotalIncome(transactions);
        assertEquals(160, totalSpent, 0);
    }
}
