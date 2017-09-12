@Main
Feature: misc test
  I want to verify things

  Scenario Outline: Create draft
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
    Then ESlip draft is created

  Examples:
    | name    | policyNo    | email           | phoneNo       | lang    | province    | address1  | address2  | city  | code  | effDate     | expDate     | insurer     | broker        |
    | Kokos   | X123456789  | kokos@gmail.com | 123-123-1234  | English | {null}      | baton     | banan     | krk   | 30300 | 10/10/2017  | 10/10/2018  | {null}      | Some Brocker  |