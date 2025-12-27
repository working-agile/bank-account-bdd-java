package com.workingagile.acsd.bankaccount;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class BankAccountTransferStepDefs {

    BankAccount bankAccountNathan;
    BankAccount bankAccountSabrina;
    int initialBalanceNathan;
    int initialBalanceSabrina;

    @Given("Nathan has a bank account with a balance of {int} USD")
    public void nathan_has_a_bank_account_with_a_balance_of_usd(Integer initialBalance) {

        bankAccountNathan = new BankAccount(initialBalance);
        initialBalanceNathan = initialBalance;

    }

    @Given("Sabrina has a bank account with a balance of {int} USD")
    public void sabrina_has_a_bank_account_with_a_balance_of_usd(Integer initialBalance) {

        bankAccountSabrina = new BankAccount(initialBalance);
        initialBalanceSabrina = initialBalance;

    }

    Exception exceptionWhenTransferring;

    @When("Nathan tries to transfers {int} to Sabrina")
    @When("Nathan transfers {int} USD to Sabrina")
    public void nathan_transfers_to_sabrina(Integer transferAmount) {

        try {
            bankAccountNathan.transferWithFee(transferAmount, bankAccountSabrina, transferFee);
        } catch (Exception e) {
            exceptionWhenTransferring = e;
        }

    }

    @Then("the transfer should be rejected")
    public void the_transfer_should_be_rejected() {

        assertThat(exceptionWhenTransferring, is(not(nullValue())));
        assertThat(exceptionWhenTransferring, is(instanceOf(BankAccount.InsufficientBalanceException.class)));

    }

    @Then("Sabrina should have a balance of {int} USD")
    public void sabrina_should_have_a_balance_of_usd(Integer expectedBalance) {

        assertThat(bankAccountSabrina.getBalance(), is(equalTo(expectedBalance)));

    }

    @Then("Nathan should have a balance of {int} USD")
    public void nathan_should_have_a_balance_of_usd(Integer expectedBalance) {

        assertThat(bankAccountNathan.getBalance(), is(equalTo(expectedBalance)));

    }

    @Then("no money should have been moved")
    public void no_money_should_have_been_moved() {

        assertThat(bankAccountNathan.getBalance(), is(equalTo(initialBalanceNathan)));
        assertThat(bankAccountSabrina.getBalance(), is(equalTo(initialBalanceSabrina)));

    }

    int transferFee;

    @Given("the bank is charging a transfer fee of {int} USD")
    public void the_bank_is_charging_a_transfer_fee_of_usd(Integer transferFee) {

        this.transferFee = transferFee;

    }




}
