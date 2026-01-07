Feature: Email alerts

  Scenario: Overdrafting triggers email alert

  Overdrafting can happen in multiple ways. When it happens,
  an email alert is sent to the bank administration.

    Given a customer account has a balance of 100 USD
    When the customer tries to withdraw 150 USD
    Then the withdrawal should be rejected
    And an email alert should be sent to the bank administration