Feature: API integration
  As a User I want to verify API integration with the system

  Scenario Outline: Verify successful sign in request
    Given System API is up and running
    When User sends sign in request with following data
      | Command   | <comm>  |
      | Login     | <login> |
      | Password  | <pass>  |
    Then Access token is sent back by the system

    Examples:
      | comm    | login                                   | pass        |
      | SignIn  | default.carrier@csiodev.onmicrosoft.com | Si3ple9Ass  |

  Scenario Outline: Verify unsuccessful sign in request
    Given System API is up and running
    When User sends sign in request with following data
      | Command   | <comm>  |
      | Login     | <login> |
      | Password  | <pass>  |
    Then System responses with proper error '<message>'

    Examples:
      | comm    | login                               | pass        | message                                                         |
      | SignIn  | admin.five@csiodev.onmicrosoft.com  | BadPass     | Invalid username or password                                    |
      | SignIn  | admin.five@op.pl                    | Si3ple9Ass  | account must be added to the csiodev.onmicrosoft.com directory  |
      | SignIn  | admin.five@csiodev.onmicrosoft.com  |             | Password field is required                                      |
      | SignIn  |                                     | Kokos       | UserId field is required                                        |
      | SignIn  |                                     |             | UserId field is required                                        |
      | SignIn  |                                     |             | Password field is required                                      |
      | SignIn  | dsadsa                              | dsadsa      | Unknown User Type                                               |

  Scenario Outline: Verify successful eSlip request
    Given System API is up and running
    When User sends eSlip creation request with following data
      | OtherId         | <otherId>     |
      | CommercialName  | <carrierName> |
    Then ESlip is properly created in the system

    Examples:
      | otherId       | carrierName       |
      | id 32432      | RSA Canada        |