package factory;

import models1.Category;
import models1.Money;
import models1.Transaction;

public abstract class TransactionFactory {

    private static TransactionFactory transactionFactory;

    public static TransactionFactory getFactory() {
        return transactionFactory;
    }

    public static void setFactory(TransactionFactory transactionFactory) {
        TransactionFactory.transactionFactory = transactionFactory;
    }

    public abstract Transaction getTransaction(
            Money amount,
            int categoryID,
            String note,
            Boolean isRecurring
    );

}
