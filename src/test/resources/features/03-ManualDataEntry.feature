Feature: Manual data entry
  As u user I want to have possibility to create eSlip manually

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
    When User clicks next
    Then Proper error message '<message>' is displayed

    Examples:
      | name    | policyNo    | email     | phoneNo   | lang    | province    | address1  | address2  | city    | code    | effDate     | expDate     | insurer     | broker    | message                             |
      | {null}  | a           | a         | 1         | English | Manitoba    | a         |           | a       | 1       | 5/5/2010    | 5/5/2010    | RSA Canada  | a         | Please enter the customer name.     |
      | a       | {null}      | a         | 1         | English | Manitoba    | a         |           | a       | 1       | 5/5/2010    | 5/5/2010    | RSA Canada  | a         | Please enter the policy number.     |
      | a       | a           | {null}    | 1         | English | Manitoba    | a         |           | a       | 1       | 5/5/2010    | 5/5/2010    | RSA Canada  | a         | Please enter an e-mail address.     |
      | a       | a           | a         | {null}    | English | Manitoba    | a         |           | a       | 1       | 5/5/2010    | 5/5/2010    | RSA Canada  | a         | Please enter a phone number.        |
      | a       | a           | a         | 1         | English | Manitoba    | a         |           | a       | 1       | {null}      | 5/5/2010    | RSA Canada  | a         | Please enter the effective date.    |
      | a       | a           | a         | 1         | English | Manitoba    | a         |           | a       | 1       | 5/5/2010    | {null}      | RSA Canada  | a         | Please enter the expiration date.   |
      | a       | a           | a         | 1         | English | Manitoba    | a         |           | a       | 1       | 5/5/2010    | 5/5/2010    | {null}      | a         | Please select an insurer.           |
      | a       | a           | a         | 1         | English | Manitoba    | a         |           | a       | 1       | 5/5/2010    | 5/5/2010    | RSA Canada  | {null}    | Please enter the brokerage name.    |
      | a       | a           | a         | 1         | English | Manitoba    | {null}    |           | a       | 1       | 5/5/2010    | 5/5/2010    | RSA Canada  | a         | Please enter the address.           |
      | a       | a           | a         | 1         | English | Manitoba    | a         |           | {null}  | 1       | 5/5/2010    | 5/5/2010    | RSA Canada  | a         | Please enter the city.              |
      | a       | a           | a         | 1         | English | Manitoba    | a         |           | a       | {null}  | 5/5/2010    | 5/5/2010    | RSA Canada  | a         | Please enter the postal code.       |


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
    When User saves eSlip draft
    Then ESlip '<name>' is displayed on Drafts list

    Examples:
      | name    | policyNo    | email       | phoneNo   | lang    | province    | address1  | address2  | city  | code    | effDate     | expDate     | insurer     | broker    |
      | kokos   | 123123123   | banan@wp.pl | 123123123 | English | Manitoba    | kokos     | baton     | krk   |  30300  | 12/12/2020  | 12/12/2022  | RSA Canada  | Banan     |

  Scenario Outline: Error handling on new vehicle form
    Given User opens drafted '<name>' eSlip
    When User clicks next
    When User adds new vehicle with given data
      | Year    | <year>    |
      | Make    | <make>    |
      | Model   | <model>   |
      | VIN     | <vin>     |
    Then Proper error message '<message>' is displayed

    Examples:
      | name  | year    | make    | model   | vin     | message                         |
      | kokos | {null}  | a       | a       | a       | Please enter the vehicle year.  |
      | kokos | 2000    | {null}  | a       | a       | Please enter the vehicle make.  |
      | kokos | 2000    | a       | {null}  | a       | Please enter the vehicle model. |
      | kokos | 2000    | a       | a       | {null}  | Please enter the VIN value.     |

  Scenario Outline: User can add new vehicle to created draft
    Given User opens drafted '<name>' eSlip
    When User clicks next
    When User adds new vehicle with given data
      | Year    | <year>    |
      | Make    | <make>    |
      | Model   | <model>   |
      | VIN     | <vin>     |
    Then Vehicle with '<vin>' is added to eSlip

    Examples:
      | name  | year    | make    | model   | vin   |
      | kokos |         |         |         |       |

  Scenario Outline: User can edit vehicle info
    Given User opens drafted '<name>' eSlip
    When User clicks next
    When User edits '<vehicleNo>' vehicle with given data
      | Year    | <year>    |
      | Make    | <make>    |
      | Model   | <model>   |
      | VIN     | <vin>     |
    Then Vehicle info is updated

    Examples:
      | name  | vehicleNo | year    | make    | model   | vin   |
      | kokos | 1         |         |         |         |       |

  Scenario Outline: User can reorder vehicle info
    Given User opens drafted '<name>' eSlip
    When User clicks next
    When User moves up vehicle with '<vin>' vin number
    Then Vehicle with '<vin>' is reordered
    When User moves down vehicle with '<vin>' vin number
    Then Vehicle with '<vin>' is reordered

    Examples:
      | name  | vin     |
      | kokos | temp345 |

  Scenario: User can remove added vehicle
    Given User opens drafted 'kokos' eSlip
    When User clicks next
    When User removes vehicle with 'temp345' vin number
    Then Vehicle with 'temp345' vin number is removed from eSlip

  Scenario Outline: Error handling on new back text
    Given User opens drafted '<name>' eSlip
    When User clicks next
    When User clicks next
    When User adds new back text section with given data
      | Title   | <title> |
      | Text    | <text>  |
    Then Proper error message '<message>' is displayed

    Examples:
      | title   | text    | message             |
      |         |         | Please enter Title  |
      |         |         | Please enter Text   |

  Scenario Outline: User can add back text to created eSlip
    Given User opens drafted '<name>' eSlip
    When User clicks next
    When User clicks next
    When User adds new back text section with given data
      | Title   | <title> |
      | Text    | <text>  |
    Then Back text with '<title>' is added to eSlip

    Examples:
      | title   | text    |
      |         |         |

  Scenario Outline: User can edit back text
    Given User opens drafter '<name>' eSlip with added back text
    When User clicks next
    When User clicks next
    When User edits back text with given data
      | Title   | <title> |
      | Text    | <text>  |
    Then Back text is updated

    Examples:
      | title   | text    |
      |         |         |
      |         |         |

  Scenario: User can reorder back text sections
    Given User opens drafted '<name>' eSlip with added several back text sections
    When User clicks next
    When User clicks next
    When User moves up one of sections
    Then Back text sections order is changed
    When User moves down one of sections
    Then Back text sections order is changed

  Scenario: User can remove added back text
    Given User opens drafted '<name>' eSlip with added back text
    When User clicks next
    When User clicks next
    When User removes added back text
    Then Back text is removed from eSlip

  Scenario Outline: User can preview and fill email form
    Given User opens drafted '<name>' eSlip
    When User clicks next
    When User clicks next
    When User clicks next
    When User fill email form with
      | Salutation          | <salutation>  |
      | Customized Message  | <message>     |
    Then Email form is updated

    Examples:
      | salutation    | message   |

  Scenario: User can displayed eSlip details
    Given User in on eslips drafts page
    When User opens created eSlip
    Then Eslip details are displayed


