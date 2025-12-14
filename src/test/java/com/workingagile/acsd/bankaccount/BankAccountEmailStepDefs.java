package com.workingagile.acsd.bankaccount;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;


public class BankAccountEmailStepDefs {


    EmailSender mockEmailSender;

    @When("a client overdrafts his account")
    public void a_client_overdrafts_his_account() {

        mockEmailSender = mock(EmailSender.class);

        try {

            BankAccount bankAccount = new BankAccount(1000, mockEmailSender);
            bankAccount.withdraw(2000);
            fail("Not expected to get here");

        } catch (Exception ignored) {
        }

    }

    @Then("an email alert is sent to the bank")
    public void an_email_alert_is_sent_to_the_bank() {

        verify(mockEmailSender, times(1)).sendEmailToBank();

    }

}
