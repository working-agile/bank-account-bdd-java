package com.workingagile.acsd.bankaccount;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionTest {

    @ParameterizedTest
    @EnumSource(value = Transaction.Type.class, names = {"DEPOSIT", "WITHDRAWAL"})
    void define_a_deposit_transaction(Transaction.Type type) {

        Transaction depositTransaction = new Transaction(type, "100");

        assertEquals(depositTransaction.getType(), type);
        assertThat(depositTransaction.getValue(), is(equalTo("100")));

    }

    @Test
    public void convert_string_to_type() {
        Transaction.Type type = Transaction.Type.valueOf("DEPOSIT");
        assertThat(type, is(equalTo(Transaction.Type.DEPOSIT)));
    }

    @Test
    public void convert_value_to_int() {

        Transaction depositTransaction = new Transaction(Transaction.Type.DEPOSIT, "100");

        Integer intValue = depositTransaction.getIntValue();

        assertThat(intValue, is(equalTo(100)));

    }

}
