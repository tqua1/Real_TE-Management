package com.financialtracker.vault.model;

import java.util.Date;

public class Transfer {
    private int transferId;
    private Date date;
    private int accountFrom;
    private int accountTo;
    private boolean is_repeating;
    private String notes;

    public Transfer(int transferId, Date date, int accountFrom, int accountTo, boolean is_repeating, String notes) {
        this.transferId = transferId;
        this.date = date;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.is_repeating = is_repeating;
        this.notes = notes;
    }

    public int getTransferId() {
        return transferId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(int accountFrom) {
        this.accountFrom = accountFrom;
    }

    public int getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(int accountTo) {
        this.accountTo = accountTo;
    }

    public boolean isIs_repeating() {
        return is_repeating;
    }

    public void setIs_repeating(boolean is_repeating) {
        this.is_repeating = is_repeating;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
