package com.workingagile.acsd.bankaccount;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BankAccountStatementTest {

    @Test
    public void empty_statement_initial_balance_1000() {

        // Arrange
        BankAccount myBankAccount = new BankAccount(1000);

        // Act
        String actualStatement = myBankAccount.getStatement();

        // Assert
        String expectedStatement;
        expectedStatement =
                "  balance=1000\n" +
                "  number of transactions=0\n" +
                "  number of deposits=0\n" +
                "  number of withdrawals=0\n" +
                "---\n";
                assertThat(actualStatement, is(equalTo(expectedStatement)));
    }


    @Test
    public void empty_statement_initial_balance_2000() {

        // Arrange
        BankAccount myBankAccount = new BankAccount(2000);

        // Act
        String actualStatement = myBankAccount.getStatement();

        // Assert
        String expectedStatement;
        expectedStatement =
                "  balance=2000\n" +
                        "  number of transactions=0\n" +
                        "  number of deposits=0\n" +
                        "  number of withdrawals=0\n" +
                        "---\n";
        assertThat(actualStatement, is(equalTo(expectedStatement)));
    }
}
