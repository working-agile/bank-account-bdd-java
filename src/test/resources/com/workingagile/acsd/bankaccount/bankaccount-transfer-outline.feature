Feature: Transfer between bank accounts

  Scenario Template: Transfer between bank accounts

  For transfers between accounts to success there has to be sufficient funds in the
  senders account.

    Given the bank is charging a transfer fee of <fee>
    And Nathan has a bank account with <balance-nathan>
    And Sabrina has a bank account with <balance-sabrina>
    When Nathan tries to transfers <amount> to Sabrina
    Then the transfer is "<status>"
    And Nathan should have <resulting-balance-nathan>
    And Sabrina should have <resulting-balance-sabrina>

    Examples: Successful transfers
      | balance-nathan | balance-sabrina | fee | amount | status     | resulting-balance-nathan | resulting-balance-sabrina |
      | 1000           | 50              | 0   | 100    | successful | 900                      | 150                       |
      | 1000           | 60              | 0   | 1000   | successful | 0                        | 1060                      |
      | 1000           | 0               | 10  | 400    | successful | 590                      | 400                       |

    Examples: Cancelled transfers
      | balance-nathan | balance-sabrina | fee | amount | status    | resulting-balance-nathan | resulting-balance-sabrina |
      | 1000           | 20              | 0   | 1001   | cancelled | 1000                     | 20                        |
      | 1000           | 0               | 10  | 1000   | cancelled | 1000                     | 0                         |