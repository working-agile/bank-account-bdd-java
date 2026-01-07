package com.workingagile.acsd.bankaccount;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BankAccount {

    private BigDecimal balance;
    private final DomainEvents domainEvents;

    public BankAccount(BigDecimal initialBalance) {
        this(initialBalance, new SimpleDomainEvents());
    }

    public BankAccount(BigDecimal initialBalance, DomainEvents domainEvents) {
        this.balance = toMoney(initialBalance);
        this.domainEvents = domainEvents;
    }

    public void deposit(BigDecimal amount) {
        this.balance = this.balance.add(toMoney(amount));
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void withdraw(BigDecimal amount) throws InsufficientFundsException {
        BigDecimal money = toMoney(amount);
        BigDecimal newBalance = this.balance.subtract(money);
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            // business rule: do not allow overdraft
            // publish a domain event before signaling the error
            if (domainEvents != null) {
                domainEvents.publish(new OverdraftAttempted(money, this.balance));
            }
            throw new InsufficientFundsException();
        }
        this.balance = newBalance;
    }

    private static BigDecimal toMoney(BigDecimal value) {
        return value.setScale(2, RoundingMode.HALF_EVEN);
    }
}
