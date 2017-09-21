@Smoke
Feature: misc test
  I want to verify things

  Scenario: Setup browser
    Given Setup browser

  Scenario Outline: User can login
    Given User is on login page
    When User enters '<login>' and '<password>'
    Then Main page is displayed

    Examples:
      | login			          | password		|
      |	b.sternalski@avanade.com  | kokos           |

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

  Scenario Outline: User can add new vehicle to created draft
    Given User opens drafted '<name>' eSlip
    When User clicks next
    When User adds new vehicle with given data
      | Year    | <year>    |
      | Make    | <make>    |
      | Model   | <model>   |
      | VIN     | <vin>     |
    Then Vehicle with '<vin>' is added to eSlip
    When User saves eSlip draft

    Examples:
      | name  | year    | make    | model   | vin         |
      | kokos | 2000    | temp    | temp    | temp123     |
      | kokos | 2000    | temp    | temp    | temp234     |
      | kokos | 2000    | temp    | temp    | temp345     |
      | kokos | 2000    | temp    | temp    | temp456     |

  Scenario Outline: User can reorder vehicle info
    Given User opens drafted '<name>' eSlip
    When User clicks next
    When User moves up vehicle with '<vin>' vin number
    Then Vehicle with '<vin>' is reordered
    When User moves down vehicle with '<vin>' vin number
    Then Vehicle with '<vin>' is reordered
    When User saves eSlip draft

    Examples:
      | name  | vin     |
      | kokos | temp345 |

  Scenario: User can remove added vehicle
    Given User opens drafted 'kokos' eSlip
    When User clicks next
    When User removes vehicle with 'temp345' vin number
    Then Vehicle with 'temp345' vin number is removed from eSlip

  Scenario Outline: User can add back text to created eSlip
    Given User opens drafted '<name>' eSlip
    When User clicks next
    When User clicks next
    When User adds new back text section with given data
      | Title   | <title> |
      | Text    | <text>  |
    Then Back text with '<title>' is added to eSlip
    When User saves eSlip draft

    Examples:
      | title   | text      |
      | temp1   | some text |
      | temp2   | some text |
      | temp3   | some text |

  Scenario: User can reorder back text sections
    Given User opens drafted 'kokos' eSlip
    When User clicks next
    When User clicks next
    When User moves up back text with 'temp2' title
    Then Back text with 'temp2' title is reordered
    When User moves down back text with 'temp2' title
    Then Back text with 'temp2' title is reordered

  Scenario: User can remove added back text
    Given User opens drafted 'kokos' eSlip
    When User clicks next
    When User clicks next
    When User removes back text with 'temp2' title
    Then Back text with 'temp2' is removed from eSlip


  Scenario: Tear down browser
    Given Close browser