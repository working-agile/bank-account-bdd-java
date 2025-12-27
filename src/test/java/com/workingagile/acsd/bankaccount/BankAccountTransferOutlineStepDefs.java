package com.workingagile.acsd.bankaccount;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class BankAccountTransferOutlineStepDefs {

    Integer transferFee;
    BankAccount bankAccountNathan;
    BankAccount bankAccountSabrina;

    @Given("the bank applies a transfer fee of {int} USD")
    public void the_bank_applies_a_transfer_fee_of_usd(Integer transferFee) {

        this.transferFee = transferFee;

    }

    @Given("the following customer accounts exist:")
    public void the_following_customer_accounts_exist(DataTable givenBalances) {

        Integer balanceNathan = Integer.valueOf(givenBalances.asMap().get("Nathan"));
        bankAccountNathan = new BankAccount(balanceNathan);

        Integer balanceSabrina = Integer.valueOf(givenBalances.asMap().get("Sabrina"));
        bankAccountSabrina = new BankAccount(balanceSabrina);

    }

    boolean transferDeclined;

    @When("Nathan asks the bank to transfer {int} USD to Sabrina")
    public void nathan_asks_the_bank_to_transfer_usd_to_sabrina(Integer moneyToTransfer) {

        try {
            bankAccountNathan.transferWithFee(moneyToTransfer, bankAccountSabrina, transferFee);
            transferDeclined = false;
        } catch (BankAccount.InsufficientBalanceException e) {
            transferDeclined = true;
        }

    }

    @Then("the transfer should be {string}")
    public void the_transfer_should_be(String transferOutcome) {

        if (transferDeclined) {
            assertThat(transferOutcome, is(equalTo("declined")));
        } else {
            assertThat(transferOutcome, is(equalTo("successful")));
        }

    }

    @Then("the resulting balances should be:")
    public void the_resulting_balances_should_be(DataTable expectedBalances) {


        Integer expectedBalanceNathan = Integer.valueOf(expectedBalances.asMap().get("Nathan"));
        assertThat(expectedBalanceNathan, is(equalTo(bankAccountNathan.getBalance())));

        Integer expectedBalanceSabrina = Integer.valueOf(expectedBalances.asMap().get("Sabrina"));
        assertThat(expectedBalanceSabrina, is(equalTo(bankAccountSabrina.getBalance())));

    }

}
