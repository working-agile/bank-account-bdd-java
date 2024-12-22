package com.workingagile.acsd.bankaccount;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TransactionTest {

    @Test
    public void should_return_value_of_transaction() {

        Transaction depositTransaction = new Transaction("deposit", "100");

        assertThat(depositTransaction.getValue(), is(equalTo("100")));

    }







    // assertThat(depositTransaction.getType(), is(equalTo("deposit")));








}
