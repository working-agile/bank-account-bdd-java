package com.workingagile.acsd.bankaccount;

public class BankAccount {

    private int balance;
    EmailSender emailSender;

    public BankAccount(Integer initialBalance) {
        balance = initialBalance;
    }

    public BankAccount(Integer initialBalance, EmailSender emailSender) {
        this(initialBalance);
        this.emailSender = emailSender;
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
        return null;
    }

    public static class InsufficientBalanceException extends Exception {}

}
