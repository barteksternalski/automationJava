@Smoke
Feature: misc test
  I want to verify things

  Scenario: Setup browser
    Given Clear email account
    Given Generate unique name
    Given Setup 'Chrome' browser

  Scenario Outline: User can login
    Given User is on login page
    When User enters '<login>' and '<password>'
    Then Main page is displayed

    Examples:
      | login			                          | password		|
      |	admin.five@csiodev.onmicrosoft.com        | Si3ple9Ass      |

  Scenario Outline: Successfully create new user
    Given User is on dashboard page
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
    When User logs out

    Examples:
      | userType      | userId    | name    | email                 | orgUserId   | orgType   | csioId    | carrier     | brokerage         | file    | modules               |
      | Organization  | baton     | baton   | bartavanade@gmail.com | {null}      | Carrier   | baton     | TestCarrier | {null}            | {null}  | Users,Create Single   |

  Scenario Outline: Verify user access
    Given User is on login page
    When Created user enters '<login>' and '<password>'
    Then Main page is displayed
    Then User has access to '<modulesAvailable>' modules
    Then User does not have access to '<modulesUnavailable>' modules
    When User logs out

    Examples:
      | login	                          | password		| modulesAvailable                                | modulesUnavailable                                  |
      |	baton@csiodev.onmicrosoft.com     | Si3ple9Ass      | Create Single,Drafts,Sent,Create User,User List | Dashboard,Reporting,Create Bulk,E-mail,E-Slip Back  |

  Scenario Outline: User can login
    Given User is on login page
    When User enters '<login>' and '<password>'
    Then Main page is displayed

    Examples:
      | login			                          | password		|
      |	admin.five@csiodev.onmicrosoft.com        | Si3ple9Ass      |

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
      | name    | policyNo    | email                 | phoneNo   | lang    | province    | address1  | address2  | city  | code    | effDate     | expDate     | insurer     | broker      |
      | banan   | 123123123   | bartavanade@gmail.com | 123123123 | English | Manitoba    | temp1     | temp1     | krk   |  30300  | 12/12/2020  | 12/12/2022  | RSA Canada  | Some Broker |

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
      | banan | 2000    | temp    | temp    | temp123     |
      | banan | 2000    | temp    | temp    | temp234     |
      | banan | 2000    | temp    | temp    | temp345     |
      | banan | 2000    | temp    | temp    | temp456     |

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
      | banan | temp345 |

  Scenario: User can remove added vehicle
    Given User opens drafted 'banan' eSlip
    When User clicks next
    When User removes vehicle with 'temp345' vin number
    Then Vehicle with 'temp345' vin number is removed from eSlip
    When User saves eSlip draft

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
      | name  | title   | text      |
      | banan | temp1   | some text |
      | banan | temp2   | some text |
      | banan | temp3   | some text |

  Scenario: User can reorder back text sections
    Given User opens drafted 'banan' eSlip
    When User clicks next
    When User clicks next
    When User moves up back text with 'temp2' title
    Then Back text with 'temp2' title is reordered
    When User moves down back text with 'temp2' title
    Then Back text with 'temp2' title is reordered
    When User saves eSlip draft

  Scenario: User can remove added back text
    Given User opens drafted 'banan' eSlip
    When User clicks next
    When User clicks next
    When User removes back text with 'temp2' title
    Then Back text with 'temp2' is removed from eSlip
    When User saves eSlip draft

  Scenario Outline: User can preview and fill email form
    Given User opens drafted '<name>' eSlip
    When User clicks next
    When User clicks next
    When User clicks next
    When User fill email form with given data
      | Salutation          | <salutation>  |
      | Customized Message  | <message>     |
    When User sends created eSlip
    Then Created '<name>' eSlip is sent to user
    Then ESlips are sent to given email account

    Examples:
      | name    | salutation    | message   |
      | banan   | test          | some test |

  Scenario: Tear down browser
    Given Close browser