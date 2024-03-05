package com.financialtracker.vault.model;

import java.math.BigDecimal;

public class Account {
    private  int accountId;
    private int transactionId;

    private int userId;
    private String name;
    private boolean debtAccount;
    private BigDecimal balance;
    private String notes;

    public Account() {}

    public Account( int accountId, int transactionId, int userId, String name, boolean debtAccount, BigDecimal balance, String notes) {
        this.accountId = accountId;
        this.transactionId = transactionId;
        this.userId = userId;
        this.name = name;
        this.debtAccount = debtAccount;
        this.balance = balance;
        this.notes = notes;
    }

    public int getAccountId() {
        return accountId;
    }


    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDebtAccount() {
        return debtAccount;
    }
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setAccountId(int accountId) {
    }
}
