package com.workingagile.acsd.bankaccount;

/**
 * Handles overdraft domain events by sending an email alert via the gateway.
 */
public class OverdraftEmailHandler {
    private final EmailGateway emailGateway;

    public OverdraftEmailHandler(EmailGateway emailGateway) {
        this.emailGateway = emailGateway;
    }

    public void on(OverdraftAttempted event) {
        emailGateway.sendOverdraftAlert(event.getRequested(), event.getAvailable());
    }
}
