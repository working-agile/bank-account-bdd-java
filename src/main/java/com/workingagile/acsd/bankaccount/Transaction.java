package com.workingagile.acsd.bankaccount;

public class Transaction {

    public Integer getIntValue() {
        return Integer.valueOf(value);
    }

    public enum Type {
        DEPOSIT, WITHDRAWAL
    }

    Type type;
    String value;

    public Transaction(Type type, String transactionValue) {

        if (type == null) {
            throw new IllegalArgumentException("unknown transaction type");
        }

        value = transactionValue;
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public Type getType() {
        return type;
    }
}
