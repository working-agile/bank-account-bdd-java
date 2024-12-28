Feature: Bank statements

  All transactions of the client needs to be stored in a transaction history.
  Clients have access to their transaction history, by requesting a bank statement.

  Scenario: Bank statements contain the current balance and the transaction history

    Given Nathan with a bank account with balance of 1140
    And the following transaction history:
      | transaction | amount |
      | DEPOSIT     | 50     |
      | WITHDRAWAL  | -30    |
      | DEPOSIT     | 100    |
      | DEPOSIT     | 20     |
      | WITHDRAWAL  | -10    |
    When Nathan requests a bank statement
    Then he should receive the bank statement containing:
    """
      balance=1140
      number of transactions=5
      number of deposits=3
      number of withdrawals=2
      ---
      transaction 1: D=50
      transaction 2: W=30
      transaction 3: D=100
      transaction 4: D=20
      transaction 5: W=10

    """


