package com.workingagile.acsd.bankaccount;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class BankAccountEmailStepDefs {

    EmailSender mockEmailSender;
    BankAccount bankAccount;
    Integer initialBalance;

    @Given("a customer account has a balance of {int} USD")
    public void a_customer_account_has_a_balance_of_usd(Integer balance) {

        initialBalance = balance;
        mockEmailSender = mock(EmailSender.class);
        bankAccount = new BankAccount(balance, mockEmailSender);

    }

    @When("the customer tries to withdraw {int} USD")
    public void the_customer_tries_to_withdraw_usd(Integer amountToWithdraw) {
        try {

            bankAccount.withdraw(amountToWithdraw);
            fail("Not expected to get here");

        } catch (Exception ignored) {
        }
    }

    @Then("the withdrawal should be rejected")
    public void the_withdrawal_should_be_rejected() {

        assertThat(bankAccount.getBalance(), is(initialBalance));

    }

    @Then("an email alert is sent to the bank administration")
    public void an_email_alert_is_sent_to_the_bank_administration() {

        verify(mockEmailSender, times(1)).sendEmailToBank();

    }

}