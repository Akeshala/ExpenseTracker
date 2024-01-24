package factory;

import models1.*;

public class IncomeTransactionFactory extends TransactionFactory {

    public static void init() {
        setFactory(new IncomeTransactionFactory());
    }

    @Override
    public Transaction getTransaction(Money amount, int categoryID, String note, Boolean isRecurring) {
        return new Income(amount, categoryID, note, isRecurring);
    }
}
