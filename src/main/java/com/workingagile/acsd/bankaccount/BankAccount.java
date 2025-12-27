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

    public BankAccount(Integer initialBalance, EmailSender emailSender, TransactionHistory transactionHistory) {
        this(initialBalance, emailSender);
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
        long numberOfDeposits = transactionList.stream()
                .filter(transaction -> transaction.getType() == Transaction.Type.DEPOSIT)
                .count();
        long numberOfWithdrawals = numberOfTransactions - numberOfDeposits;

        StringBuilder statement = new StringBuilder();
        statement.append(
        "  balance=" + balance + "\n" +
                "  number of transactions=" + numberOfTransactions + "\n" +
                "  number of deposits=" + numberOfDeposits + "\n" +
                "  number of withdrawals=" + numberOfWithdrawals + "\n" +
                "  ---\n"
        );
        if (numberOfTransactions > 0) {

            for (int i=0; i<numberOfTransactions; i++) {

                Transaction transaction = transactionList.get(i);
                statement.append("  transaction ")
                        .append(i+1)
                        .append(": ");
                if (transaction.getType() == Transaction.Type.DEPOSIT) {
                    statement.append("D");
                } else {
                    statement.append("W");
                }
                Integer value = transaction.getIntValue();
                if (value < 0) {
                    value = -value;
                }
                statement.append("=").append(value).append("\n");
            }

        }
        return statement.toString();

    }

    public static class InsufficientBalanceException extends Exception {}

}
