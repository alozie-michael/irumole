package com.irumole.ng.dao;

import lombok.Data;

@Data
public class TransactionDate {
    private String day;
    private String month;
    private String year;

    public TransactionDate(String day, String month, String year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }
}
