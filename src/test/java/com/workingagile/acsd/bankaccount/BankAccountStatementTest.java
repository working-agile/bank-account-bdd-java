package com.workingagile.acsd.bankaccount;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class BankAccountStatementTest {

    @ParameterizedTest
    @ValueSource(ints = {1000, 2000})
    public void empty_statement_initial_balance(int initialBalance) {

        // Arrange
        BankAccount myBankAccount = new BankAccount(initialBalance);
        List<Transaction> transactionList = new ArrayList<>();
        TransactionHistory transactionHistoryMock = mock(TransactionHistory.class);
        Mockito.when(transactionHistoryMock.getTransactionHistory()).thenReturn(transactionList);
        myBankAccount.setTransactionHistory(transactionHistoryMock);

        // Act
        String actualStatement = myBankAccount.getStatement();

        // Assert
        String expectedStatement;
        expectedStatement =
                "  balance=" + initialBalance + "\n" +
                "  number of transactions=0\n" +
                "  number of deposits=0\n" +
                "  number of withdrawals=0\n" +
                "  ---\n";
                assertThat(actualStatement, is(equalTo(expectedStatement)));
    }

    @Test
    public void one_deposit_with_initial_balance_of_1000() {

        // Arrange
        BankAccount myBankAccount = new BankAccount(1000);

        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction(Transaction.Type.DEPOSIT, "100"));
        TransactionHistory transactionHistoryMock = mock(TransactionHistory.class);
        Mockito.when(transactionHistoryMock.getTransactionHistory()).thenReturn(transactionList);
        myBankAccount.setTransactionHistory(transactionHistoryMock);

        // Act
        String actualStatement = myBankAccount.getStatement();

        // Assert
        String expectedStatement;
        expectedStatement =
                        "  balance=1000\n" +
                        "  number of transactions=1\n" +
                        "  number of deposits=1\n" +
                        "  number of withdrawals=0\n" +
                        "  ---\n" +
                        "  transaction 1=deposit=100\n";
        assertThat(actualStatement, is(equalTo(expectedStatement)));
    }

    @Test
    public void two_deposits_with_initial_balance_of_1000() {

        // Arrange
        BankAccount myBankAccount = new BankAccount(1000);

        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction(Transaction.Type.DEPOSIT, "100"));
        transactionList.add(new Transaction(Transaction.Type.DEPOSIT, "50"));
        TransactionHistory transactionHistoryMock = mock(TransactionHistory.class);
        Mockito.when(transactionHistoryMock.getTransactionHistory()).thenReturn(transactionList);
        myBankAccount.setTransactionHistory(transactionHistoryMock);

        // Act
        String actualStatement = myBankAccount.getStatement();

        // Assert
        String expectedStatement;
        expectedStatement =
                        "  balance=1000\n" +
                        "  number of transactions=2\n" +
                        "  number of deposits=2\n" +
                        "  number of withdrawals=0\n" +
                        "  ---\n" +
                        "  transaction 1=deposit=100\n" +
                        "  transaction 2=deposit=50\n";
        assertThat(actualStatement, is(equalTo(expectedStatement)));
    }

    @Test
    public void two_deposits_and_1_withdrawal_with_initial_balance_of_1000() {

        // Arrange
        BankAccount myBankAccount = new BankAccount(1000);

        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction(Transaction.Type.DEPOSIT, "100"));
        transactionList.add(new Transaction(Transaction.Type.DEPOSIT, "50"));
        transactionList.add(new Transaction(Transaction.Type.WITHDRAWAL, "-30"));

        TransactionHistory transactionHistoryMock = mock(TransactionHistory.class);
        Mockito.when(transactionHistoryMock.getTransactionHistory()).thenReturn(transactionList);
        myBankAccount.setTransactionHistory(transactionHistoryMock);

        // Act
        String actualStatement = myBankAccount.getStatement();

        // Assert
        String expectedStatement =
                "  balance=1000\n" +
                        "  number of transactions=3\n" +
                        "  number of deposits=2\n" +
                        "  number of withdrawals=1\n" +
                        "  ---\n" +
                        "  transaction 1=deposit=100\n" +
                        "  transaction 2=deposit=50\n" +
                        "  transaction 3=withdrawal=30\n";
        assertThat(actualStatement, is(equalTo(expectedStatement)));
    }

}
