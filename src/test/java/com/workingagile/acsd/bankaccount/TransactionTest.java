package com.workingagile.acsd.bankaccount;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class TransactionTest {

    @Test
    public void should_return_value_of_transaction() {

        Transaction depositTransaction = new Transaction("deposit", "100");

        assertThat(depositTransaction.getValue(), is(equalTo("100")));

    }

    @Test
    public void should_return_type_of_transaction() {

        Transaction depositTransaction = new Transaction("deposit", "100");

        assertThat(depositTransaction.getType(), is(equalTo("deposit")));

    }

    @Test
    public void should_refuse_unknown_transaction_type() {

        try {
            new Transaction("unknown", "100");
            fail("should not accept unknown transaction types");
        } catch(IllegalArgumentException e) {}

    }





}
