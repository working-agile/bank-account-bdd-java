Feature: Transfer between bank accounts

  Scenario: A client transfers an amount to another account

    Given Nathan has a bank account with 1000
    And Sabrina has a bank account with 500
    When Nathan transfers 150 to Sabrina
    Then Sabrina should have 650
    And Nathan should have 850

  Scenario: A client cannot transfers more money than is in the account

    Given Nathan has a bank account with 1000
    And Sabrina has a bank account with 500
    When Nathan transfers 1001 to Sabrina
    Then the transfer should be cancelled
    And both should have the same amount in their accounts

  Scenario: Transfer fee is charged when transferring money between accounts

  The bank can charge a transfer fee.

    Given the bank is charging a transfer fee of 10
    And Nathan has a bank account with 1000
    And Sabrina has a bank account with 500
    When Nathan transfers 150 to Sabrina
    Then Sabrina should have 650
    And Nathan should have 840