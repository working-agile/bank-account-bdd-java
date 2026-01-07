package com.workingagile.acsd.bankaccount;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Minimal domain object representing a bank account balance and deposit behavior.
 */
public class BankAccount {

    private BigDecimal balance;

    public BankAccount(BigDecimal initialBalance) {
        this.balance = toMoney(initialBalance);
    }

    public void deposit(BigDecimal amount) {
        this.balance = this.balance.add(toMoney(amount));
    }

    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * Placeholder API for withdrawals. Intentionally left without behavior to satisfy compilation only.
     * Functional implementation will be provided by TDD in a later step.
     */
    public void withdraw(BigDecimal amount) throws InsufficientFundsException {
        // no-op: production behavior intentionally not implemented yet
    }

    private static BigDecimal toMoney(BigDecimal value) {
        return value.setScale(2, RoundingMode.HALF_EVEN);
    }
}
