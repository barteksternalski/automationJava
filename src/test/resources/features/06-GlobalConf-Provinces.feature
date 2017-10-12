Feature: Global Configuration - Provinces handling
  As a User I want to be able to create, modify and delete provinces.

  Scenario Outline: User can add new province
    Given User is on dashboard page
    When User adds new province with given data
      | Name    | <name>    |
      | Code    | <code>    |
      | Color   | <color>   |
      | Logo    | <logo>    |
    Then Province '<name>' is displayed on the list

    Examples:
      | name    | code    | color   | logo    |
      |         |         |         |         |

  Scenario Outline: Error handling on new province form
    Given User is on dashboard page
    When User adds new province with given data
      | Name    | <name>    |
      | Code    | <code>    |
      | Color   | <color>   |
      | Logo    | <logo>    |
    Then Proper error message '<message>' is displayed

    Examples:
      | name    | code    | color   | logo    | message                                 |
      |         |         |         |         | Province with such name already exists  |
      |         |         |         |         | Province with such code already exists  |

  Scenario Outline: User can modify existing province
    Given User is on dashboard page
    Given At least one province exists in system
    When User updates '<oldName>' province with given data
      | Name    | <oldName> |
      | Code    | <code>    |
      | Color   | <color>   |
      | Logo    | <logo>    |
    Then Province '<newName>' is updated

    Examples:
      | oldName    | newName  | code    | color   | logo    |
      |            |          |         |         |         |

  Scenario Outline: User can delete existing province
    Given User is on dashboard page
    Given At least one province exists in system
    When User deletes '<name>' province
    Then Province '<name>' is no longer displayed on the list

    Examples:
      | name    |
      |         |