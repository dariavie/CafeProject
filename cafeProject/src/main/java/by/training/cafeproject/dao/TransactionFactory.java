package by.training.cafeproject.dao;

import by.training.cafeproject.exception.PersistentException;

public interface TransactionFactory {
    Transaction createTransaction() throws PersistentException;

    void close();
}
