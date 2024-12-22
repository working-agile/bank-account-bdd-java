package com.workingagile.acsd.bankaccount;

public class Transaction {

    String value;
    String type;

    public Transaction(String transactionType, String transactionValue) {

        if (transactionType == null || transactionType != "deposit") {
            throw new IllegalArgumentException("unknown transaction type");
        }

        value = transactionValue;
        type = transactionType;
    }

    public String getValue() {
        return value;
    }

    public String getType() {
        return type;
    }
}
