package factory;

import models.Transaction;

public class ExpenseTransactionFactory extends TransactionFactory {

    public static void init() {
        setFactory(new ExpenseTransactionFactory());
    };

    @Override
    public Transaction getTransaction() {
        return null;
    }

}
