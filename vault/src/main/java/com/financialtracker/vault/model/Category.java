package com.financialtracker.vault.model;

public class Category {
    private int categoryId;
    private int transactionId;
    private int userId;
    private String name;

    public Category(int categoryId, int transactionId, int userId, String name) {
        this.categoryId = categoryId;
        this.transactionId = transactionId;
        this.userId = userId;
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
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

    public void setCategoryId(int categoryId) {
    }
}
