package com.workingagile.acsd.bankaccount;

import java.math.BigDecimal;

/**
 * Domain event emitted when a withdrawal would overdraw the account.
 */
public class OverdraftAttempted {
    private final BigDecimal requested;
    private final BigDecimal available;

    public OverdraftAttempted(BigDecimal requested, BigDecimal available) {
        this.requested = requested;
        this.available = available;
    }

    public BigDecimal getRequested() {
        return requested;
    }

    public BigDecimal getAvailable() {
        return available;
    }
}
