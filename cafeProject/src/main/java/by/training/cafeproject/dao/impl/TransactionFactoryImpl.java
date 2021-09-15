package by.training.cafeproject.dao.impl;

import by.training.cafeproject.dao.Transaction;
import by.training.cafeproject.dao.TransactionFactory;

public class TransactionFactoryImpl implements TransactionFactory {
    public static final TransactionFactory instance = new TransactionFactoryImpl();

    private final Transaction transaction = new TransactionImpl();

    private TransactionFactoryImpl() {}

    public static TransactionFactory getInstance() {
        return instance;
    }

    @Override
    public Transaction getTransaction() {
        return transaction;
    }
}
