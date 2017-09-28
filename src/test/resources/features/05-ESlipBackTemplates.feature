Feature: ESlip Back Text Templates
  As a user I want to have possibility to create and modify eSlip back text template

  Scenario Outline: Error handling on back template form
    Given User is on login page
    When User opens back template page
    When User adds new back template section with given data
      | Title   | <title> |
      | Text    | <text>  |
    Then Proper error message '<message>' is displayed

    Examples:
      | title   | text    | message             |
      |         |         | Please enter Title  |
      |         |         | Please enter Text   |

  Scenario Outline: User can add sections to back template
    Given User is on login page
    When User opens back template page
    When User adds new back template section with given data
      | Title     | <title> |
      | Text      | <text>  |
      | Read only | <read>  |
    Then Back template section with '<title>' title is added to eSlip
    When User saves eSlip back template

    Examples:
      | title   | text      | read    |
      | temp1   | some text | Yes     |
      | temp2   | some text | No      |
      | temp3   | some text | No      |

  Scenario Outline: User can modify back template section
    Given User is on login page
    When User opens back template page
    When User edits '<title>' back template section with given data
      | Title     | <newTitle> |
      | Text      | <text>     |
      | Read only | <read>     |
    Then Back template section with title '<newTitle>' is updated
    When User saves eSlip back template

    Examples:
      | title   | newTitle   | text           | read    |
      | temp2   | newTemp    | different text | No      |

  Scenario: User can reorder back template sections
    Given User is on login page
    When User opens back template page
    When User moves up back template section with 'temp2' title
    Then Back template section with 'temp2' title is reordered
    When User moves down back template section with 'temp2' title
    Then Back template section with 'temp2' title is reordered
    When User saves eSlip back template

  Scenario: User can remove back template section
    Given User is on login page
    When User opens back template page
    When User removes back template section with 'temp2' title
    Then Back template section with 'temp2' is removed from eSlip
    When User saves eSlip back template
