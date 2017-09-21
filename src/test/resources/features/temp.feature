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
      |	b.sternalski@avanade.com  | pass		    |

  Scenario Outline: Error handling on Customer and Policy Information form
    Given User is on dashboard page
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


  Scenario: Tear down browser
    Given Close browser