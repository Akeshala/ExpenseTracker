package org.asd.factory;

import org.asd.models.Transaction;

public abstract class TransactionFactory {

    private static TransactionFactory transactionFactory;

    public static TransactionFactory getFactory() {
        return transactionFactory;
    }

    public static void setFactory(TransactionFactory transactionFactory) {
        TransactionFactory.transactionFactory = transactionFactory;
    }

    public abstract Transaction getTransaction(
            double amount,
            int categoryID,
            String note,
            Boolean isRecurring
    );

}
