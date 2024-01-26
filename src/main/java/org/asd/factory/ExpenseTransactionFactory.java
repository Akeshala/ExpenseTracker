package org.asd.factory;

import org.asd.models.Expense;
import org.asd.models.Transaction;

public class ExpenseTransactionFactory extends TransactionFactory {

    public static void init() {
        setFactory(new ExpenseTransactionFactory());
    }

    @Override
    public Transaction getTransaction(double amount, int categoryID, String note, Boolean isRecurring) {
        return new Expense(amount, categoryID, note, isRecurring);
    }
}
