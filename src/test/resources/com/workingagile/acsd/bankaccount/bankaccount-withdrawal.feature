Feature: Bank Account withdrawal

  The Bank account feature allows clients to withdraw money from their bank account.
  Overdrafting is not allowed.

  Scenario: Withdrawing from a bank account

    Given a bank account with the initial balance of 1000
    When a client withdraws 200
    Then the account should have a balance of 800

  Scenario: Overdraft from a bank account is not allowed

    Given a bank account with the initial balance of 1000
    When a client withdraws 1100
    Then the withdrawal should be cancelled
    And the account should have a balance of 1000