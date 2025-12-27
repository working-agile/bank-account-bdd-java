package com.workingagile.acsd.bankaccount;

import com.icegreen.greenmail.junit5.GreenMailExtension;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetupTest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class EmailIntegrationTest {

    @RegisterExtension
    static GreenMailExtension greenMail = new GreenMailExtension(ServerSetupTest.SMTP);

    @Test
    @DisplayName("Simple email sending test")
    void sendEmailWithGreenMailAndSimpleMailSender() throws Exception {

        Email email = EmailBuilder.startingBlank()
                .from("sender@example.com")
                .to("recipient@example.com")
                .withSubject("Email with Plain Text!")
                .withPlainText("This is a test email sent using SJM.")
                .buildEmail();

        try (Mailer mailer = MailerBuilder
                .withSMTPServer("localhost", 3025)
                .buildMailer()) {

            mailer.sendMail(email);
        }

        final MimeMessage[] receivedMessages = greenMail.getReceivedMessages();
        final MimeMessage receivedMessage = receivedMessages[0];
        assertEquals("This is a test email sent using SJM.", receivedMessage.getContent());
    }

    @DisplayName("Overdrafts trigger email")
    @Test
    void sendEmailWhenOverdraft() throws MessagingException {

        // Arrange (Given)
        EmailSender emailSender = new SimpleEmailSender();
        BankAccount bankAccount = new BankAccount(1000, emailSender);

        // Act (When)
        try {
            bankAccount.withdraw(2000);
            fail("should not come here after overdraft");
        } catch (BankAccount.InsufficientBalanceException ignored) {
            // ignore
        }

        // Assert
        boolean received = greenMail.waitForIncomingEmail(Duration.ofSeconds(1).toMillis(), 1);
        assertThat(received, is(true));

        // Assert: inspect the received message(s)
        MimeMessage[] messages = greenMail.getReceivedMessages();
        assertThat(messages.length, is(1));
        assertThat(messages[0].getSubject(), is("Alarm!"));
        String receivedBody = GreenMailUtil.getBody(messages[0]).trim();
        assertThat(receivedBody, containsString("There has been an overdraft!!"));
    }

}