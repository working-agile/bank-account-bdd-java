Feature: Manage bank account balance

  The Bank account feature allows clients to keep track of their current financial balance.
  Clients can deposit, withdraw and query their account balance.

  Scenario: Successful deposit updates the balance

    Given a savings account with a balance of 1000.00 USD
    When the customer deposits 200.00 USD into the account
    Then the account the account balance should be 1200.00 USD
