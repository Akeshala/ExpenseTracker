package factory;

import models1.Category;
import models1.Expense;
import models1.Money;
import models1.Transaction;

public class IncomeTransactionFactory extends TransactionFactory {

    public static void init() {
        setFactory(new IncomeTransactionFactory());
    }

    @Override
    public Transaction getTransaction(Integer id, Money amount, Category category, String note, Boolean isRecurring) {
        return new Expense(id, amount, category, note, isRecurring);
    }
}
