package com.workingagile.acsd.bankaccount;

import io.cucumber.java.ParameterType;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountStepdefs {

    private BankAccount account;
    private Throwable lastWithdrawalError;
    private InMemoryEmailGateway emailGateway;

    @ParameterType("[-+]?\\d+(?:\\.\\d{1,2})?")
    public BigDecimal money(String amount) {
        return new BigDecimal(amount).setScale(2, RoundingMode.HALF_EVEN);
    }

    @Before
    public void before_scenario_setup() {
        // Reset scenario state and register email handler for overdraft events
        this.emailGateway = new InMemoryEmailGateway();
        DomainEvents.clearAllSubscribers();
        DomainEvents.register(OverdraftAttempted.class, evt -> new OverdraftEmailHandler(emailGateway).on(evt));
        this.lastWithdrawalError = null;
    }

    @Given("a savings account with a balance of {money} USD")
    public void a_savings_account_with_a_balance_of_usd(BigDecimal initialBalance) {
        this.account = new BankAccount(initialBalance);
    }

    @Given("a customer account has a balance of {money} USD")
    public void a_customer_account_has_a_balance_of_usd(BigDecimal initialBalance) {
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

    @When("the customer tries to withdraw {money} USD")
    public void the_customer_tries_to_withdraw_usd(BigDecimal amount) {
        // Reuse the same behavior as the standard withdraw step
        the_customer_withdraws_usd_from_the_account(amount);
    }

    @Then("the withdrawal should be declined due to insufficient funds")
    public void the_withdrawal_should_be_declined_due_to_insufficient_funds() {
        assertNotNull(lastWithdrawalError, "Expected withdrawal to be declined with a business exception");
        assertTrue(lastWithdrawalError instanceof InsufficientFundsException,
                "Expected InsufficientFundsException to signal insufficient funds");
    }

    @Then("the withdrawal should be rejected")
    public void the_withdrawal_should_be_rejected() {
        the_withdrawal_should_be_declined_due_to_insufficient_funds();
    }

    @Then("an email alert should be sent to the bank administration")
    public void an_email_alert_should_be_sent_to_the_bank_administration() {
        assertNotNull(emailGateway, "Email gateway should be initialized for the scenario");
        assertTrue(emailGateway.isOverdraftAlertSent(), "Expected an overdraft email alert to be sent");
    }

    private static void assertMoneyEquals(BigDecimal expected, BigDecimal actual) {
        assertEquals(0, expected.compareTo(actual), "Unexpected account balance");
    }
}
