package com.workingagile.acsd.bankaccount;

import java.util.List;

public class BankAccount {

    private int balance;
    EmailSender emailSender;
    TransactionHistory transactionHistory;

    public BankAccount(Integer initialBalance) {
        balance = initialBalance;
    }

    public BankAccount(Integer initialBalance, EmailSender emailSender) {
        this(initialBalance);
        this.emailSender = emailSender;
    }

    public void setTransactionHistory(TransactionHistory transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    public void deposit(Integer amount) {
        balance += amount;
    }

    public Integer getBalance() {
        return balance;
    }

    public void withdraw(Integer amountToWithdraw) throws InsufficientBalanceException {
        if (amountToWithdraw > balance) {
            if (emailSender != null) {
                emailSender.sendEmailToBank();
            }
            throw new InsufficientBalanceException();
        }

        balance = balance - amountToWithdraw;
    }

    public void transfer(Integer transferAmount, BankAccount bankAccountReceiver) throws InsufficientBalanceException {
        transferWithFee(transferAmount, bankAccountReceiver, 0);
    }

    public void transferWithFee(Integer transferAmount, BankAccount bankAccountReceiver, Integer transferFee) throws InsufficientBalanceException {
        withdraw(transferAmount + transferFee);
        bankAccountReceiver.deposit(transferAmount);
    }

    public String getStatement() {

        List<Transaction> transactionList = transactionHistory.getTransactionHistory();
        int numberOfTransactions = transactionList.size();
        int numberOfDeposits = numberOfTransactions;

        String statement =
        "  balance=" + balance + "\n" +
                "  number of transactions=" + numberOfTransactions + "\n" +
                "  number of deposits=" + numberOfDeposits + "\n" +
                "  number of withdrawals=0\n" +
                "---\n";
        if (numberOfTransactions > 0) {
            Transaction depositTransaction = transactionList.get(0);
            statement = statement +
                    "transaction 1=deposit=" + depositTransaction.getValue() +
                    "\n";
        }
        return statement;

    }

    public static class InsufficientBalanceException extends Exception {}

}
