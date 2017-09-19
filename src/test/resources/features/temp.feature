@Smoke
Feature: misc test
  I want to verify things

  Scenario: Setup browser
    Given Setup browser

  Scenario Outline: Successfully create new user
    Given User is on main page
    When User creates new user with given data
      | User Type               | <userType>  |
      | User Id                 | <userId>    |
      | Name                    | <name>      |
      | Email                   | <email>     |
      | Organization User Id    | <orgUserId> |
      | Organization Type       | <orgType>   |
      | CSIO Net ID             | <csioId>    |
      | Carrier Organization    | <carrier>   |
      | Brokerage Organization  | <brokerage> |
      | File                    | <file>      |
      | Modules                 | <modules>   |
    Then User '<name>' is created

    Examples:
      | userType      | userId    | name    | email       | orgUserId   | orgType   | csioId    | carrier   | brokerage         | file    | modules         |
      | Organization  | baton     | baton   | baton@op.pl | {null}      | Brokerage | baton     | {null}    | Sample Brokerage  | {null}  | Users,Templates |

  Scenario Outline: User is able to fill Customer and Policy Information
    Given User is creating new eEslip
    When User creates new eSlip with given customer and policy information with given data
      | Name                    | <name>        |
      | Policy Number           | <policyNo>    |
      | Email                   | <email>       |
      | Phone Number            | <phoneNo>     |
      | Preferred Language      | <lang>        |
      | Province                | <province>    |
      | Address 1               | <address1>    |
      | Address 2               | <address2>    |
      | City                    | <city>        |
      | Postal Code             | <code>        |
      | Policy Effective Date   | <effDate>     |
      | Policy Expiration Date  | <expDate>     |
      | Insurer                 | <insurer>     |
      | Brokerage               | <broker>      |
    Then 'Vehicle information' page is displayed

    Examples:
      | name    | policyNo    | email       | phoneNo   | lang    | province    | address1  | address2  | city  | code    | effDate     | expDate     | insurer     | broker    |
      | kokos   | 123123123   | banan@wp.pl | 123123123 | English | Manitoba    | kokos     | baton     | krk   |  30300  | 12/12/2020  | 12/12/2022  | RSA Canada  | Banan     |

  Scenario Outline: User can add new vehicle to created draft
    When User adds new vehicle with given data
      | Year    | <year>    |
      | Make    | <make>    |
      | Model   | <model>   |
      | VIN     | <vin>     |
    Then 'E-Slip Back Text' page is displayed

    Examples:
      | year    | make    | model   | vin       |
      | 2010    | kokos   | Fiat    | 123123123 |

  Scenario Outline: User can add back text to created eSlip
    When User adds new back text section with given data
      | Title   | <title> |
      | Text    | <text>  |
    Then 'Send E-Slip: Preview' page is displayed

    Examples:
      | title   | text        |
      | test    | lorem ipsum |

  Scenario: Tear down browser
    Given Close browser