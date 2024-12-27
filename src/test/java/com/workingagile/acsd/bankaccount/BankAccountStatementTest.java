package com.workingagile.acsd.bankaccount;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BankAccountStatementTest {

    @ParameterizedTest
    @ValueSource(ints = {1000, 2000})
    public void empty_statement_initial_balance(int initialBalance) {

        // Arrange
        BankAccount myBankAccount = new BankAccount(initialBalance);

        // Act
        String actualStatement = myBankAccount.getStatement();

        // Assert
        String expectedStatement;
        expectedStatement =
                "  balance=" + initialBalance + "\n" +
                "  number of transactions=0\n" +
                "  number of deposits=0\n" +
                "  number of withdrawals=0\n" +
                "---\n";
                assertThat(actualStatement, is(equalTo(expectedStatement)));
    }


}
