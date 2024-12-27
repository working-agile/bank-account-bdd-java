package com.workingagile.acsd.bankaccount;

import io.cucumber.datatable.DataTable;
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
    TransactionHistory transactionHistoryMock;

    @Given("Nathan with a bank account and balance of {int}")
    public void nathan_with_a_bank_account_and_initial_balance_of(Integer initialBalance) {

        bankAccountNathan = new BankAccount(initialBalance);

    }

    @Given("the following transaction history")
    public void the_following_transaction_history(DataTable transactionsDatatable) {

        List<Map<String, String>> transactionEntries =  transactionsDatatable.asMaps();

        List<Transaction> transactions = new ArrayList<Transaction>();
        for (Map<String, String> transactionEntry: transactionEntries) {

            String transactionValue = transactionEntry.get("amount");
            String transactionTypeString = transactionEntry.get("transaction");
            Transaction.Type type = Transaction.Type.valueOf(transactionTypeString);
            transactions.add(new Transaction(type,  transactionValue));
        }

        transactionHistoryMock = mock(TransactionHistory.class);
        Mockito.when(transactionHistoryMock.getTransactionHistory()).thenReturn(transactions);

        // Inject the dependency
        bankAccountNathan.setTransactionHistory(transactionHistoryMock);

    }

    String bankStatement;

    @When("Nathan requests a bank statement")
    public void nathan_requests_a_bank_statement() {

        bankStatement = bankAccountNathan.getStatement();

    }

    @Then("he should receive a bank statement containing:")
    public void he_should_receive_a_bank_statement_containing(String expectedStatement) {

        assertThat(bankStatement, is(expectedStatement));
    }

}
