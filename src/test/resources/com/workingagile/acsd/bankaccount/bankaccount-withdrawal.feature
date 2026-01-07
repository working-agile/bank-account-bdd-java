Feature: Withdraw funds from a bank account

  In order to access my money
  As a bank customer
  I want to withdraw funds from my account

  Background:
    Given a savings account with a balance of 1000.00 USD

  Rule: Cash withdrawal reduces the available balance

    Scenario: Successful withdrawal
      When the customer withdraws 200.00 USD from the account
      Then the balance of the account is 800.00 USD

    Scenario: Withdrawal above available balance is declined
      When the customer withdraws 1100.00 USD from the account
      Then the withdrawal is declined due to insufficient funds
      And the balance of the account remains 1000.00 USD