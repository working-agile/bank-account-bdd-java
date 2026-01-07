package com.workingagile.acsd.bankaccount;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountStepdefs {

    private BankAccount account;
    private Throwable lastWithdrawalError;

    @ParameterType("[-+]?\\d+(?:\\.\\d{1,2})?")
    public BigDecimal money(String amount) {
        return new BigDecimal(amount).setScale(2, RoundingMode.HALF_EVEN);
    }

    @Given("a savings account with a balance of {money} USD")
    public void a_savings_account_with_a_balance_of_usd(BigDecimal initialBalance) {
        this.account = new BankAccount(initialBalance);
    }

    @When("the customer deposits {money} USD into the account")
    public void the_customer_deposits_usd_into_the_account(BigDecimal amount) {
        this.account.deposit(amount);
    }

    @Then("the balance of the account should remain/be {money} USD")
    public void the_balance_of_the_account_should_be_usd(BigDecimal expected) {
        assertMoneyEquals(expected, account.getBalance());
    }

    @When("the customer withdraws {money} USD from the account")
    public void the_customer_withdraws_usd_from_the_account(BigDecimal amount) {
        try {
            this.account.withdraw(amount);
            this.lastWithdrawalError = null;
        } catch (InsufficientFundsException e) {
            this.lastWithdrawalError = e;
        }
    }

    @Then("the withdrawal should be declined due to insufficient funds")
    public void the_withdrawal_should_be_declined_due_to_insufficient_funds() {
        assertNotNull(lastWithdrawalError, "Expected withdrawal to be declined with a business exception");
        assertTrue(lastWithdrawalError instanceof InsufficientFundsException,
                "Expected InsufficientFundsException to signal insufficient funds");
    }

    private static void assertMoneyEquals(BigDecimal expected, BigDecimal actual) {
        assertEquals(0, expected.compareTo(actual), "Unexpected account balance");
    }
}
