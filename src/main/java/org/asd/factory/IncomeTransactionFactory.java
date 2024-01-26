package org.asd.factory;

import org.asd.models.Income;
import org.asd.models.Transaction;

public class IncomeTransactionFactory extends TransactionFactory {

    public static void init() {
        setFactory(new IncomeTransactionFactory());
    }

    @Override
    public Transaction getTransaction(double amount, int categoryID, String note, Boolean isRecurring) {
        return new Income(amount, categoryID, note, isRecurring);
    }
}
