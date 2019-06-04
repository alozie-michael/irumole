package com.irumole.ng.dto;

import lombok.Data;

@Data
public class Transaction {
    private String transactionDate;
    private String valueDate;
    private String narration;
    private String transactionReference;
    private String debit;
    private String credit;
}
