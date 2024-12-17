package com.workingagile.acsd.bankaccount;

public class BankAccount {

    private int balance;

    public BankAccount(Integer initialBalance) {
        balance = initialBalance;
    }

    public void deposit(Integer amount) {
        balance += amount;
    }

    public Integer getBalance() {
        return balance;
    }

    public void withdraw(Integer amountToWithdraw) throws InsufficientBalanceException {
        if (amountToWithdraw > balance) {
            throw new InsufficientBalanceException();
        }

        balance = balance - amountToWithdraw;
    }

    public static class InsufficientBalanceException extends Exception {}

}
