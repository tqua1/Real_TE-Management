package com.financialtracker.vault.model;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {
    private int transactionId;
    private int accountId;
    private int userId;
    private int accountToId;
    private String category;
    private BigDecimal amount;
    private Date date;
    private String type;
    private boolean is_fixed;
    private String notes;

    public Transaction(int transactionId, int accountId, int userId, int accountToId, String category, BigDecimal amount, Date date, String type, boolean is_fixed, String notes) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.userId = userId;
        this.accountToId = accountToId;
        this.category = category;
        this.amount = amount;
        this.date = date;
        this.type = type;
        this.is_fixed = is_fixed;
        this.notes = notes;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAccountToId() {
        return accountToId;
    }

    public void setAccountToId(int accountToId) {
        this.accountToId = accountToId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isIs_fixed() {
        return is_fixed;
    }

    public void setIs_fixed(boolean is_fixed) {
        this.is_fixed = is_fixed;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
