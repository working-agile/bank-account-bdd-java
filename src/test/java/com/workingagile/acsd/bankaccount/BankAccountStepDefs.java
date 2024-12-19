package com.workingagile.acsd.bankaccount;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class BankAccountStepDefs {

    BankAccount bankAccount;

    @Given("a bank account with the initial balance of {int}")
    public void a_bank_account_with_the_initial_balance_of(Integer initialBalance) {

        bankAccount = new BankAccount(initialBalance);

    }
    @When("a client deposits {int}")
    public void a_client_deposits(Integer amount) {

        bankAccount.deposit(amount);

    }

    @Then("the account should have a balance of {int}")
    public void the_account_should_have_a_balance_of(Integer expectedBalance) {

        int actualBalance = bankAccount.getBalance();
        assertThat(actualBalance, is(equalTo(expectedBalance)));

    }



    @When("a client withdraws {int}")
    public void a_client_withdraws(Integer amount)  {

        try {
            bankAccount.withdraw(amount);
        } catch (BankAccount.InsufficientBalanceException e) {
            exception = e;
        }

    }

    Exception exception;

    @Then("the withdrawal should be cancelled")
    public void the_withdrawal_should_be_cancelled() {

        assertThat(exception, is(not(nullValue())));
        assertThat(exception, is(instanceOf(BankAccount.InsufficientBalanceException.class)));

    }


}
