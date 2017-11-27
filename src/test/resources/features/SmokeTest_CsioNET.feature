@SmokeAPI
Feature: CsioNET integration
  As a User I want to integrate properly with CsioNET

  Scenario Outline: Verify unsuccessful sign in request to CsioNET
    Given CsioNET API is up and running
    When User sends sign in request to CsioNET with following data
      | Command   | <comm>    |
      | Login     | <login>   |
      | Password  | <pass>    |
    Then CsioNET system responses with proper error '<message>'

    Examples:
      | comm    | login                         | pass      | message                     |
      | SignIn  | avanade@vendor.edi.csio.comX  | r49M5VTT  | Failed to sign in this user |
      | SignIn  | avanade@vendor.edi.csio.com   | X49M5VTT  | Failed to sign in this user |

  Scenario Outline: Verify successful sign in request to CsioNET
    Given CsioNET API is up and running
    When User sends sign in request to CsioNET with following data
      | Command   | <comm>  |
      | Login     | <login> |
      | Password  | <pass>  |
    Then CsioNET sessionID is sent back by the system

    Examples:
      | comm    | login                         | pass      |
      | SignIn  | avanade@vendor.edi.csio.com   | r49M5VTT  |

  Scenario Outline: Get list of messages from CsioNET
    Given CsioNET API is up and running
    When Users sends request to get CsioNET messages with following data
      | From Date     | <from>      |
      | To Date       | <to>        |
      | Page No       | <page>      |
      | Items on page | <items>     |
    Then List of CsioNET messages is sent

    Examples:
      | from                    | to                      | page    | items   |
      | 2017-11-01 00:00:00 AM  | 2017-11-30 11:59:59 PM  | 53      | 10      |

  Scenario Outline: Get single message from CsioNET
    Given CsioNET API is up and running
    When User sends request to get '<no>' message from obtained list
    Then Message details are sent by CsioNET system

    Examples:
      | no    |
      | 1     |
      | 3     |

  Scenario Outline: Sent notification message to CsioNET
    Given CsioNET API is up and running
    When User sends notification message to CsioNET with following data
      | Policy Number   | <policy>  |
      | Effective Date  | <effDate> |
      | Expiration Date | <expDate> |
      | Email           | <email>   |
    Then Message is successfully delivered to CsioNET system

    Examples:
      | policy      | effDate    | expDate    | email                     |
      | POL123461   | 2017-07-15 | 2018-07-15 | b.sternalski@avanade.com  |

  Scenario: Verify successful logout request from CsioNET
    Given CsioNET API is up and running
    When User logs out from CsioNET
    Then CsioNET sessionID is closed