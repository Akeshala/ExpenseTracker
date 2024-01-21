package factory;

import models.Transaction;

public class IncomeTransactionFactory extends TransactionFactory {

    public static void init() {
        setFactory(new IncomeTransactionFactory());
    };

    @Override
    public Transaction getTransaction() {
        return null;
    }

}
