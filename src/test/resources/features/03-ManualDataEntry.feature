Feature: Manual data entry
  As u user I want to have possibility to create eSlip manually

  Scenario: User is able to create eSlip manually
    Given User is on dashboard screen
    When User navigates to Create Single eSlip
    Then Create eSlip form is displayed

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

  Scenario Outline: Error handling on Customer and Policy Information form
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
    Then Proper error message '<message>' is displayed

    Examples:
      | name    | policyNo    | email     | phoneNo   | lang    | province    | address1  | address2  | city    | code    | effDate     | expDate     | insurer   | broker    | message                             |
      | {null}  |             |           |           |         |             |           |           |         |         |             |             |           |           | Please enter Customer Name          |
      |         | {null}      |           |           |         |             |           |           |         |         |             |             |           |           | Please enter Policy Number          |
      |         |             | kokos.w.w |           |         |             |           |           |         |         |             |             |           |           | Please enter a valid email address  |
      |         |             |           | {null}    |         |             |           |           |         |         |             |             |           |           | Please enter a phone number         |
      |         |             |           |           |         |             |           |           |         |         | 30-02-2020  |             |           |           | Please enter valid date             |
      |         |             |           |           |         |             |           |           |         |         |             | 30-02-2020  |           |           | Please enter valid date             |
      |         |             |           |           |         |             |           |           |         |         |             |             | {null}    |           | Please enter an insurer             |
      |         |             |           |           |         |             |           |           |         |         |             |             |           | {null}    | Please select a b                   |
      |         |             |           |           |         |             | {null}    |           |         |         |             |             |           |           | Please enter Address 1 value        |
      |         |             |           |           |         |             |           |           | {null}  |         |             |             |           |           | Please enter City                   |
      |         |             |           |           |         |             |           |           |         | {null}  |             |             |           |           | Please enter postal code            |

  Scenario: User can displayed eSlip details
    Given User in on eslips drafts page
    When User opens created eSlip
    Then Eslip details are displayed

  Scenario Outline: User can add new vehicle to created draft
    Given User opens drafted eSlip
    When User adds new vehicle with given data
      | Year    | <year>    |
      | Make    | <make>    |
      | Model   | <model>   |
      | VIN     | <vin>     |
    Then Vehicle is added to eSlip

    Examples:
      | year    | make    | model   | vin   |
      |         |         |         |       |

  Scenario Outline: Error handling on new vehicle form
    Given User opens drafted eSlip
    When User adds new vehicle with given data
      | Year    | <year>    |
      | Make    | <make>    |
      | Model   | <model>   |
      | VIN     | <vin>     |
    Then Error message <message> is displayed

    Examples:
      | year    | make    | model   | vin     | message                         |
      | {null}  |         |         |         | Please enter the vehicle year.  |
      |         | {null}  |         |         | Please enter the vehicle make.  |
      |         |         | {null}  |         | Please enter the vehicle model. |
      |         |         |         | {null}  | Please enter the VIN value.     |

  Scenario Outline: User can edit vehicle info
    Given User opens drafted eSlip with added vehicle
    When User edits vehicle with given data
      | Year    | <year>    |
      | Make    | <make>    |
      | Model   | <model>   |
      | VIN     | <vin>     |
    Then Vehicle info is updated

    Examples:
      | year    | make    | model   | vin   |
      |         |         |         |       |

  Scenario: User can reorder vehicle info
    Given User opens drafted eSlip with added several vehicles
    When User moves up one of vehicles
    Then Vehicle order is changed
    When User moves down one of vehicles
    Then Vehicle order is changed

  Scenario: User can remove added vehicle
    Given User opens drafted eSlip with added vehicle
    When User removes added vehicle
    Then Vehicle is removed from eSlip

  Scenario Outline: User can add back text to created eSlip
    Given User opens drafted eSlip
    When User adds new back text section with given data
      | Title   | <title> |
      | Text    | <text>  |
    Then Back text entry is added to eSlip

    Examples:
      | title   | text    |
      |         |         |

  Scenario Outline: Error handling on new back text
    Given User opens drafted eSlip
    When User adds new back text section with given data
      | Title   | <title> |
      | Text    | <text>  |
    Then Error message <message> is displayed

    Examples:
      | title   | text    | message             |
      |         |         | Please enter Title  |
      |         |         | Please enter Text   |

  Scenario Outline: User can edit back text
    Given User opens drafter eSlip with added back text
    When User edits back text with given data
      | Title   | <title> |
      | Text    | <text>  |
    Then Back text is updated

    Examples:
      | title   | text    |
      |         |         |
      |         |         |

  Scenario: User can reorder back text sections
    Given User opens drafted eSlip with added several back text sections
    When User moves up one of sections
    Then Back text sections order is changed
    When User moves down one of sections
    Then Back text sections order is changed

  Scenario: User can remove added back text
    Given User opens drafted eSlip with added back text
    When User removes added back text
    Then Back text is removed from eSlip

  Scenario Outline: User can preview and fill email form
    Given User opens drafted eSlip
    When User fill email form with
      | Salutation          | <salutation>  |
      | Customized Message  | <message>     |
    Then Email form is updated

    Examples:
      | salutation    | message   |