Feature: Global Configuration - Brokers handling
  As a User I want to be able to create, modify and delete carriers.

  Scenario Outline: User can add new broker
    Given User is on dashboard page
    When User adds new broker with given data
      | Name    | <name>    |
      | Address | <address> |
      | Code    | <code>    |
      | Details | <details> |
    Then Broker '<name>' is displayed on the list

    Examples:
      | name    | address    | code     | details     |
      |         |            |          |             |

  Scenario Outline: Error handling on new broker form
    Given User is on dashboard page
    When User adds new broker with given data
      | Name    | <name>    |
      | Address | <address> |
      | Code    | <code>    |
      | Details | <details> |
    Then Proper error message '<message>' is displayed

    Examples:
      | name    | address | code    | details | message                                |
      |         |         |         |         | Broker with such name already exists   |
      |         |         |         |         | Broker with such code already exists   |
      |         |         |         |         | Please enter the name                  |
      |         |         |         |         | Please enter the code                  |
      |         |         |         |         | Please enter the address               |

  Scenario Outline: User can modify existing broker
    Given User is on dashboard page
    Given At least one broker exists in system
    When User updates '<oldName>' broker with given data
      | Name    | <oldName> |
      | Address | <address> |
      | Code    | <code>    |
      | Details | <details> |
    Then Broker '<newName>' is updated

    Examples:
      | oldName | newName | address    | code     | details     |
      |         |         |            |          |             |

  Scenario Outline: User can delete existing broker
    Given User is on dashboard page
    Given At least one broker exists in system
    When User deletes '<name>' broker
    Then Broker '<name>' is no longer displayed on the list

    Examples:
      | name    |
      |         |

  Scenario Outline: User cannot delete assigned broker
    Given User is on dashboard page
    Given At least one broker exists in system and is assigned to eSlip
    When User deletes '<name>' broker
    Then Proper error message '<message>' is displayed

    Examples:
      | name    | message       |
      |         | Cannot delete |