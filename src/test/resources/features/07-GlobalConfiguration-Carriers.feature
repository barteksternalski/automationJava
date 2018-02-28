Feature: Global Configuration - Carriers handling
  As a User I want to be able to create, modify and delete carriers.

  Scenario Outline: User can add new carrier
    Given User is on dashboard page
    When User adds new carrier with given data
      | Name    | <name>    |
      | Address | <address> |
      | Code    | <code>    |
      | Details | <details> |
    Then Carrier '<name>' is displayed on the list

    Examples:
      | name    | address    | code     | details     |
      |         |            | 12       |             |
      |         |            | aa       |             |
      |         |            | 12       |             |
      |         |            | 12345    |             |
      |         |            | ABCDE    |             |
      |         |            | a2C9x    |             |

  Scenario Outline: Error handling on new carrier form
    Given User is on dashboard page
    When User adds new carrier with given data
      | Name    | <name>    |
      | Address | <address> |
      | Code    | <code>    |
      | Details | <details> |
    Then Proper error message '<message>' is displayed

    Examples:
      | name    | address | code    | details | message                                           |
      |         |         |         | {null}  | Carrier with such code already exists             |
      | Test    | Test    | a       | {null}  | Carrier code must be at least 2 characters long.  |
      | Test    | Test    | {null}  | {null}  | Please enter the code.                            |
      | Test    | Test    | -302    | {null}  | Carrier Code must be alphanumeric.                |
      | Test    | Test    | 2 3     | {null}  | Carrier Code must be alphanumeric.                |
      | Test    | Test    | 0-[2]   | {null}  | Carrier Code must be alphanumeric.                |


  Scenario Outline: User can modify existing carrier
    Given User is on dashboard page
    Given At least one carrier exists in system
    When User updates '<oldName>' carrier with given data
      | Name    | <oldName> |
      | Address | <address> |
      | Code    | <code>    |
      | Details | <details> |
    Then Carrier '<newName>' is updated

    Examples:
      | oldName | newName | address    | code     | details     |
      |         |         |            |          |             |

  Scenario Outline: User can delete existing carrier
    Given User is on dashboard page
    Given At least one carrier exists in system
    When User deletes '<name>' carrier
    Then Carrier '<name>' is no longer displayed on the list

    Examples:
      | name    |
      |         |

  Scenario Outline: User cannot delete assigned carrier
    Given User is on dashboard page
    Given At least one carrier exists in system and is assigned to eSlip
    When User deletes '<name>' carrier
    Then Proper error message '<message>' is displayed

    Examples:
      | name    | message       |
      |         | Cannot delete |