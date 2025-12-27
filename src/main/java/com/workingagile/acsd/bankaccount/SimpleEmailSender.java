package com.workingagile.acsd.bankaccount;

import jakarta.mail.*;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

public class SimpleEmailSender implements EmailSender {

    @Override
    public void sendEmailToBank() {

        Email email = EmailBuilder.startingBlank()
                .from("robot@test.com")
                .to("admin@test.com")
                .withSubject("Alarm!")
                .withPlainText("There has been an overdraft!!")
                .buildEmail();

        try (Mailer mailer = MailerBuilder
                .withSMTPServer("localhost", 3025)
                .buildMailer()) {
            mailer.sendMail(email);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
