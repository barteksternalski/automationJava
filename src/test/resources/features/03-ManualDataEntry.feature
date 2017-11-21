@Main
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

  Scenario Outline: Verify proper date format
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
      | name    | policyNo    | email       | phoneNo   | lang    | province    | address1  | address2  | city    | code    | effDate     | expDate     | insurer     | broker    | message           |
      | kokos   | 123123123   | banan@wp.pl | 123123123 | English | Manitoba    | kokos     | baton     | krk     |  30300  | 2010-10-10  | 2020-10-10  | RSA Canada  | Banan     | {null}            |
      | kokos   | 123123123   | banan@wp.pl | 123123123 | English | Manitoba    | kokos     | baton     | krk     |  30300  | 2010/10/10  | 2020-10-10  | RSA Canada  | Banan     | Wrong format date |
      | kokos   | 123123123   | banan@wp.pl | 123123123 | English | Manitoba    | kokos     | baton     | krk     |  30300  | 06/10/2010  | 2020-10-10  | RSA Canada  | Banan     | Wrong format date |
      | kokos   | 123123123   | banan@wp.pl | 123123123 | English | Manitoba    | kokos     | baton     | krk     |  30300  | 06-20-2010  | 2020-10-10  | RSA Canada  | Banan     | Wrong format date |
      | kokos   | 123123123   | banan@wp.pl | 123123123 | English | Manitoba    | kokos     | baton     | krk     |  30300  | 20-06-2010  | 2020-10-10  | RSA Canada  | Banan     | Wrong format date |
      | kokos   | 123123123   | banan@wp.pl | 123123123 | English | Manitoba    | kokos     | baton     | krk     |  30300  | 20/06/2010  | 2020-10-10  | RSA Canada  | Banan     | Wrong format date |
      | kokos   | 123123123   | banan@wp.pl | 123123123 | English | Manitoba    | kokos     | baton     | krk     |  30300  | 2020-10-10  | 2010/10/10  | RSA Canada  | Banan     | Wrong format date |
      | kokos   | 123123123   | banan@wp.pl | 123123123 | English | Manitoba    | kokos     | baton     | krk     |  30300  | 2020-10-10  | 06/10/2010  | RSA Canada  | Banan     | Wrong format date |
      | kokos   | 123123123   | banan@wp.pl | 123123123 | English | Manitoba    | kokos     | baton     | krk     |  30300  | 2020-10-10  | 06-20-2010  | RSA Canada  | Banan     | Wrong format date |
      | kokos   | 123123123   | banan@wp.pl | 123123123 | English | Manitoba    | kokos     | baton     | krk     |  30300  | 2020-10-10  | 20-06-2010  | RSA Canada  | Banan     | Wrong format date |
      | kokos   | 123123123   | banan@wp.pl | 123123123 | English | Manitoba    | kokos     | baton     | krk     |  30300  | 2020-10-10  | 20/06/2010  | RSA Canada  | Banan     | Wrong format date |

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

  Scenario Outline: User is able to update Customer and Policy Information
    Given User opens drafted '<name>' eSlip
    When User updates eSlip with given customer and policy information with given data
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
    Then ESlip '<name>' is updated on Drafts list

    Examples:
      | name    | policyNo    | email       | phoneNo   | lang    | province    | address1  | address2  | city  | code    | effDate     | expDate     | insurer     | broker    |
      | kokos   | 123123123   | banan@wp.pl | 123123123 | English | Manitoba    | kokos     | baton     | krk   |  30300  | 12/12/2020  | 12/12/2022  | RSA Canada  | Banan     |

  Scenario Outline: User can upload multiple files to single policy
    Given User opens drafted '<name>' eSlip
    When User uploads '<file>' file
    When User saves eSlip draft
    Then '<file>' file is displayed on list

    Examples:
      | name      | file            |
      | kokos     | temp.pdf        |
      | kokos     | temp.jpg        |

  Scenario: User can upload up to 5 files to single policy
    Given User opens drafted '<name>' eSlip
    Given 4 files are already added to policy
    When User uploads '<file>' file
    When User saves eSlip draft
    Then '<file>' file is displayed on list
    Then User cannot add more files to policy
    
  Scenario: User can upload files smaller than 5mb
    Given User opens drafted '<name>' eSlip
    When User uploads '<file>' file
    Then File is not uploaded
    Then Proper error message 'too big' is displayed

  Scenario Outline: User can remove added file from policy
    Given User opens drafted '<name>' eSlip
    Given At lease one file is added to policy
    When User removes '<file>' file
    When User saves eSlip draft
    Then '<file>' file is no longer displayed on list

    Examples:
      | name      | file            |
      | kokos     | temp.pdf        |
      | kokos     | temp.jpg        |

  Scenario: User can download file while editing eSlip
    Given User opens drafted '<name>' eSlip
    Given At lease one file is added to policy
    When User download '<file>' file
    Then File is downloaded on local drive

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
    When User saves eSlip draft

    Examples:
      | name  | year    | make    | model   | vin         |
      | banan | 2000    | temp    | temp    | temp123     |
      | banan | 2000    | temp    | temp    | temp234     |
      | banan | 2000    | temp    | temp    | temp345     |
      | banan | 2000    | temp    | temp    | temp456     |

  Scenario Outline: User can edit vehicle info
    Given User opens drafted '<name>' eSlip
    When User clicks next
    When User edits '<oldVin>' vehicle with given data
      | Year    | <year>    |
      | Make    | <make>    |
      | Model   | <model>   |
      | VIN     | <vin>     |
    Then Vehicle with '<vin>' is updated
    When User saves eSlip draft

    Examples:
      | name  | oldVin    | year    | make    | model   | vin     |
      | kokos | temp234   |         |         |         | newTemp |

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
    Given User opens drafted 'kokos' eSlip
    When User clicks next
    When User removes vehicle with 'temp345' vin number
    Then Vehicle with 'temp345' vin number is removed from eSlip
    When User saves eSlip draft

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
    When User saves eSlip draft

    Examples:
      | name  | title   | text      |
      | banan | temp1   | some text |
      | banan | temp2   | some text |
      | banan | temp3   | some text |

  Scenario Outline: User can edit back text
    Given User opens drafted '<name>' eSlip
    When User clicks next
    When User clicks next
    When User edits '<title>' back text with given data
      | Title   | <newTitle> |
      | Text    | <text>     |
    Then Back text with title '<newTitle>' is updated
    When User saves eSlip draft

    Examples:
      | name  | title   | newTitle   | text           |
      | banan | temp2   | newTemp    | different text |

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

    Examples:
      | name    | salutation    | message   |
      | banan   | test          | some test |