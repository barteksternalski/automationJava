@Main
Feature: User management
  As u user I want to have possibility to view, create, edit and delete users

  Scenario Outline: Error handling during new user creation
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
    Then Proper error message '<message>' is displayed

    Examples:
      | userType    | userId    | name    | email   | orgUserId   | orgType   | csioId    | carrier   | brokerage   | file    | modules   | message                             |
      |             | {null}    |         |         |             |           |           |           |             |         |           | Please enter userId.                |
      |             |           | {null}  |         |             |           |           |           |             |         |           | Please enter User Name.             |
      |             |           |         | babol.w |             |           |           |           |             |         |           | Please enter a valid email address  |
      |             |           |         |         |             |           | {null}    |           |             |         |           | Please enter CSIO Net Id.           |
      |             |           |         |         |             |           |           | {null}    |             |         |           | Please enter Carrier Organization.  |
      |             |           |         |         |             |           |           |           | {null}      |         |           | Please enter Broker Organization.   |

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

    Examples:
      | userType      | userId    | name    | email       | orgUserId   | orgType   | csioId    | carrier   | brokerage         | file    | modules         |
      | Organization  | baton     | baton   | baton@op.pl | {null}      | Brokerage | baton     | {null}    | Sample Brokerage  | {null}  | Users,Templates |

  Scenario: Deactivate user
    Given User is on user listing page
    When User deactivates selected 'baton'
    Then 'baton' user is deactivated

  Scenario: Activate user
    Given User is on user listing page
    When User activates selected 'baton'
    Then 'baton' user is activated

  Scenario: User can reset password
    Given User is on user listing page
    When User resets password for selected 'baton'
    Then New password is sent to given email

  Scenario: User can be deleted
    Given User is on user listing page
    When User deletes selected 'baton'
    Then User 'baton' is no longer listed