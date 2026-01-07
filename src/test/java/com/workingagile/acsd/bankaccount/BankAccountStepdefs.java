package com.workingagile.acsd.bankaccount;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountStepdefs {

    private BankAccount account;
    private Throwable lastWithdrawalError;

    @Given("a savings account with a balance of {double} USD")
    public void aSavingsAccountWithABalanceOfUSD(Double initialBalance) {
        this.account = new BankAccount(toMoney(initialBalance));
    }

    @When("the customer deposits {double} USD into the account")
    public void theCustomerDepositsUSDIntoTheAccount(Double amount) {
        this.account.deposit(toMoney(amount));
    }

    @Then("the balance of the account should remain {double} USD")
    @Then("the balance of the account should be {double} USD")
    public void theBalanceOfTheAccountShouldBeUSD(Double expected) {
        assertMoneyEquals(expected, account.getBalance());
    }

    @When("the customer withdraws {double} USD from the account")
    public void the_customer_withdraws_usd_from_the_account(Double amount) {
        // TDD: propose a new domain method to be implemented in production code
        // public void withdraw(BigDecimal amount) throws InsufficientFundsException
        //  - on success: reduce balance by amount
        //  - on insufficient funds: do not change balance, throw InsufficientFundsException
        try {
            this.account.withdraw(toMoney(amount));
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







    private static BigDecimal toMoney(Double value) {
        return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_EVEN);
    }

    private static void assertMoneyEquals(Double expected, BigDecimal actual) {
        BigDecimal exp = toMoney(expected);
        assertEquals(0, exp.compareTo(actual), "Unexpected account balance");
    }
}
