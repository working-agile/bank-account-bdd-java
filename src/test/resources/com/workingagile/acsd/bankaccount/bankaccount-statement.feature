Feature: Bank statements

  All transactions of the customer need to be stored in a transaction history.
  Customers have access to their transaction history, by requesting a bank statement.

  Scenario: Bank statements printout contain the current balance and the transaction history

    Given the customer Nathan with a bank account with balance of 1140 USD
    And the following transaction history:
      | transaction | amount |
      | DEPOSIT     | 50     |
      | WITHDRAWAL  | -30    |
      | DEPOSIT     | 100    |
      | DEPOSIT     | 20     |
      | WITHDRAWAL  | -10    |
    When Nathan requests a bank statement
    Then he should receive the following bank statement printout:
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


