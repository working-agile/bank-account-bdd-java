Feature: Money transfers between customer accounts

  Scenario Template: Transfer is approved or declined based on available funds

  For transfers between accounts to be approved there has to be sufficient funds in the
  senders account.

    Given the bank applies a transfer fee of <fee> USD
    And the following customer accounts exist:
      | Customer | Starting balance (USD)      |
      | Nathan   | <nathan-balance>            |
      | Sabrina  | <sabrina-balance>           |
    When Nathan asks the bank to transfer <amount> USD to Sabrina
    Then the transfer should be "<outcome>"
    And the resulting balances should be:
      | Customer | Resulting balance (USD)     |
      | Nathan   | <nathan-resulting-balance>  |
      | Sabrina  | <sabrina-resulting-balance> |

    Examples: Approved transfers
      | nathan-balance | sabrina-balance | fee | amount | outcome    | nathan-resulting-balance | sabrina-resulting-balance |
      | 1000           | 50              | 0   | 100    | successful | 900                      | 150                       |
      | 1000           | 60              | 0   | 1000   | successful | 0                        | 1060                      |
      | 1000           | 0               | 10  | 400    | successful | 590                      | 400                       |

    Examples: Declined transfers
      | nathan-balance | sabrina-balance | fee | amount | outcome    | nathan-resulting-balance | sabrina-resulting-balance |
      | 1000           | 20              | 0   | 1001   | declined   | 1000                     | 20                        |
      | 1000           | 0               | 10  | 1000   | declined   | 1000                     | 0                         |