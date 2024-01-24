package factory;

import models1.Transaction;
import models1.Category;
import models1.Expense;
import models1.Money;

public class ExpenseTransactionFactory extends TransactionFactory {

    public static void init() {
        setFactory(new ExpenseTransactionFactory());
    }

    @Override
    public Transaction getTransaction(Money amount, int categoryID, String note, Boolean isRecurring) {
        return new Expense(amount, categoryID, note, isRecurring);
    }
}
