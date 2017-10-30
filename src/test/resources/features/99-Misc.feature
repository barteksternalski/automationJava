Feature: Verify miscellaneous functionalities

  Scenario: User can lookup for existing carrier
    Given At least 2 carrier organizations exists
    When User creates new eSlip with 'carrier' carrier
    Then Carrier is selected from list

  Scenario: User cannot enter non-existing carrier
    Given At least 2 carrier organizations exists
    When User creates new eSlip with non-existing 'carrier2' carrier
    Then Carrier field is empty

  Scenario: User can lookup for existing broker
    Given At least 2 broker organizations exists
    When User creates new eSlip with 'broker' broker
    Then Broker is selected from list

  Scenario: User can enter non-existing broker
    Given At least 2 broker organizations exists
    When User creates new eSlip with non-existing 'broker2' broker
    Then Broker field is filled

  Scenario: Create new eEslip as Carrier with assigned organization
    Given Carrier with 'carrier' organization assigned is logged to system
    When User carrier creates new eSlip
    Then Carrier field is prefilled with current carrier organization

  Scenario: Create new eEslip as Broker with assigned organization
    Given Broker with 'broker' organization assigned is logged to system
    When User carrier creates new eSlip
    Then Broker field is prefilled with current carrier organization

  Scenario Outline: User can see and dismiss notifications
    Given User is on login page
    When User enters '<login>' and '<password>'
    When User perform '<action>'
    Then Notification is displayed

    Examples:
      | login			                          | password		| action                              |
      |	admin.five@csiodev.onmicrosoft.com        | Si3ple9Ass      | sent eslip                          |
      |                                           |                 | delete eslip                        |
      |                                           |                 | delete different organization eslip |
      |                                           |                 | clone different organization eslip  |
      |                                           |                 | edit different organization eslip   |
      |                                           |                 | create carrier without organization |
      |                                           |                 | create broker without organization  |

  Scenario Outline: Two users from same organization can see notifications
    Given '<type>' organization admin is logged to system
    Given '<type>' organization user is logged to system
    Given '<type>' organization user created eSlip draft
    When '<type>' organization admin deletes created draft
    Then Notification is displayed for '<type>' admin
    Then Notification is displayed for '<type>' user

    Examples:
      | type      |
      | carrier   |
      | brokerage |

  Scenario Outline: Verify email preview logo
    Given User is on login page
    When User enters '<login>' and '<password>'
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
    When User clicks next
    When User clicks next
    When User clicks next
    Then Proper carrier logo is displayed
    Then Proper broker logo is displayed

    Examples:
      | login     | password		| name    | policyNo    | email                 | phoneNo   | lang    | province    | address1  | address2  | city  | code    | effDate     | expDate     | insurer     | broker      |
      |	carrier1  | Si3ple9Ass      | banan   | 123123123   | bartavanade@gmail.com | 123123123 | English | Manitoba    | temp1     | temp1     | krk   |  30300  | 12/12/2020  | 12/12/2022  | RSA Canada  | Some Broker |
      | carrier1  |                 |         |             |                       |           |         |             |           |           |       |         |             |             | RSA Canada  | Other Broker|
      | carrier2  |                 |         |             |                       |           |         |             |           |           |       |         |             |             | Carrier2    |             |
      | broker1   |                 |         |             |                       |           |         |             |           |           |       |         |             |             | RSA Canada  | Broker      |
      | broker1   |                 |         |             |                       |           |         |             |           |           |       |         |             |             | Carrier2    | Broker      |
      | broker2   |                 |         |             |                       |           |         |             |           |           |       |         |             |             | RSA Canada  | Broker2     |

