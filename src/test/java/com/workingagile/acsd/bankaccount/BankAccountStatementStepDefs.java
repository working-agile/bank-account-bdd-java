package com.workingagile.acsd.bankaccount;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

public class BankAccountStatementStepDefs {

    BankAccount bankAccountNathan;
    TransactionHistory transactionHistoryStub;

    @Given("the customer Nathan with a bank account with balance of {int} USD")
    public void theCustomerNathanWithABankAccountWithBalanceOfUSD(int balance) {
        bankAccountNathan = new BankAccount(balance);
    }

    @Given("the following transaction history:")
    public void the_following_transaction_history(DataTable transactionsDatatable) {

        List<Map<String, String>> transactionEntries =  transactionsDatatable.asMaps();

        List<Transaction> transactions = new ArrayList<>();
        for (Map<String, String> transactionEntry: transactionEntries) {

            String transactionValue = transactionEntry.get("amount");
            String transactionTypeString = transactionEntry.get("transaction");
            Transaction.Type type = Transaction.Type.valueOf(transactionTypeString);
            transactions.add(new Transaction(type,  transactionValue));
        }

        transactionHistoryStub = mock(TransactionHistory.class);
        Mockito.when(transactionHistoryStub.getTransactionHistory()).thenReturn(transactions);

        // Inject the dependency
        bankAccountNathan.setTransactionHistory(transactionHistoryStub);

    }

    String bankStatement;

    @When("Nathan requests a bank statement")
    public void nathan_requests_a_bank_statement() {

        bankStatement = bankAccountNathan.getStatement();

    }

    @Then("he should receive the following bank statement printout:")
    public void heShouldReceiveTheFollowingBankStatementPrintout(String expectedStatement) {

        assertThat(bankStatement, is(expectedStatement));

    }

}
