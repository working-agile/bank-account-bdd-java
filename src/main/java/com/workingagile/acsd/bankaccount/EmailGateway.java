package com.workingagile.acsd.bankaccount;

import java.math.BigDecimal;

/**
 * Abstraction for sending email alerts.
 */
public interface EmailGateway {
    void sendOverdraftAlert(BigDecimal requested, BigDecimal available);
}
