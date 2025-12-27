Feature: Transfer between bank accounts

  Background:
    Given Nathan has a bank account with a balance of 1000 USD
    And Sabrina has a bank account with a balance of 500 USD

  Scenario: Successful transfer between two accounts

    When Nathan transfers 150 USD to Sabrina
    Then Sabrina should have a balance of 650 USD
    And Nathan should have a balance of 850 USD

  Scenario: Transfer is rejected when the sender has insufficient funds

    When Nathan transfers 1001 USD to Sabrina
    Then the transfer should be rejected
    And no money should have been moved

  Scenario: Transfer fee is deducted from the sender account

  The bank can charge a transfer fee.

    Given the bank is charging a transfer fee of 10 USD
    When Nathan transfers 150 USD to Sabrina
    Then Sabrina should have a balance of 650 USD
    And Nathan should have a balance of 840 USD