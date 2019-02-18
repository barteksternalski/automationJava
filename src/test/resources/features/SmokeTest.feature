Feature: Avanade recruitment tasks

  Scenario Outline: 001. Main navigation verification
    Given User opens Avanade page
    When User navigates to '<tab>'
    Then Proper page title '<title>' is displayed

    Examples:
      | tab                           | title                                                   |
      | Solutions                     | Digital Business Solutions & Cloud Services \| Avanade  |
      | Technologies and Capabilities | Technologies & Capabilities \| Avanade                  |
      | Client Stories                | Client Stories \| Avanade                               |
      | Thinking                      | Thinking - Business Issues and Insights \| Avanade      |
      | Careers                       | Careers in Business Technology & IT \| Avanade          |
      | About Avanade                 | About Avanade \| Avanade                                |

##  Scenario Outline: 02. Position search
##    Given User opens Avanade page
##    When User searches for '<position>' position
##    Then Proper records are displayed
##
##    Examples:
##      | position    |
##      | qa          |
##      | devops      |
##      | avanade     |
##      | qwertyuiop  |
#
#
#
