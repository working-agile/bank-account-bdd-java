package com.workingagile.acsd.bankaccount;

import java.math.BigDecimal;

public class InMemoryEmailGateway implements EmailGateway {
    private boolean overdraftAlertSent;

    @Override
    public void sendOverdraftAlert(BigDecimal requested, BigDecimal available) {
        this.overdraftAlertSent = true;
    }

    public boolean isOverdraftAlertSent() {
        return overdraftAlertSent;
    }

    public void reset() {
        this.overdraftAlertSent = false;
    }
}
