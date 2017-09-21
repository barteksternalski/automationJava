@Main
Feature: User management
  As u user I want to have possibility to view, create, edit and delete users

#USR-02
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

#USR-02
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

  #USR-04
  Scenario: Activate user
    Given User is on user listing page
    When User activates selected 'user'
    Then 'user' user is activated

#USR-04
  Scenario: Deactivate user
    Given User is on user listing page
    When User deactivates selected 'user'
    Then 'user' user is deactivated

  Scenario: User can reset password
    Given User is on user listing page
    When User resets password for selected 'user'
    Then New password is sent to given email

  Scenario: User can be deleted
    Given User is on user listing page
    When User deletes selected 'user'
    Then User is no longer listed


  #USR-01
  Scenario Outline: View user details
    Given '<user>' is on user listing page
    When User selects listed user '<type>'
    Then Action pane is displayed

    Examples:
      | user		| type			|
      | CSIOAdmin	| CSIOAdmin		|
      | CSIOAdmin	| Organization	|
      | CSIOAdmin	| Regular		|
      | Regular	    | Regular		|


