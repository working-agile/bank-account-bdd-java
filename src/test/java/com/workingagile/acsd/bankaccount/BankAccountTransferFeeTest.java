package com.workingagile.acsd.bankaccount;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BankAccountTransferFeeTest {

    @Test
    public void should_deduct_fee_from_sender_when_transferring_money() throws Exception {

        // Arrange
        BankAccount bankAccountSender = new BankAccount(1000);
        BankAccount bankAccountReceiver = new BankAccount(500);

        // Act
        bankAccountSender.transferWithFee(300, bankAccountReceiver, 10);

        // Assert
        assertThat(bankAccountReceiver.getBalance(), is(equalTo(500+300)));
        assertThat(bankAccountSender.getBalance(), is(equalTo(1000-300-10)));
    }


}
