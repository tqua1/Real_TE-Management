package com.financialtracker.vault.service;

import com.financialtracker.vault.model.Transaction;

import java.security.Principal;
import java.util.List;

public interface TransactionService {
    Transaction updateTransaction(Principal principal, Transaction transaction);

    List<Transaction> viewAllTransactions(Principal principal);

    List<Transaction> viewTransactionsByType(Principal principal, String type);

    Transaction createTransaction(Principal principal, Transaction newTransaction);

    void deleteTransaction(Principal principal, int transactionId);
}
