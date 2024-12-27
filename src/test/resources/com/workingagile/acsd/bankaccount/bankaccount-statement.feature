Feature: Bank statements

* All transactions of the client needs to be stored in a transaction history.
Clients have access to their transaction history, by requesting a
bank statement.

  Scenario: Bank statements contain the current balance and the transaction history

    Given Nathan with a bank account and balance of 1140
    And the following transaction history
      | transaction | amount |
      | DEPOSIT     | 50     |
      | WITHDRAWAL  | -30    |
      | DEPOSIT     | 100    |
      | DEPOSIT     | 20     |
    When Nathan requests a bank statement
    Then he should receive a bank statement containing:
    """
      balance=1140
      number of transactions=4
      number of deposits=3
      number of withdrawals=1
      ---
      transaction 1=deposit=50
      transaction 2=withdrawal=30
      transaction 3=deposit=100
      transaction 4=deposit=20
    """


