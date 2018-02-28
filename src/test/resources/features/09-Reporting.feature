@UI
Feature: Reporting
  As a User I want to have possibility to check current statistics and filter needed data

  Scenario Outline: Verify dashboard data access
    Given User is on login page
    When User enters '<login>' and '<password>'
    When User navigates to Dashboard tab
    Then Displayed data is filtered based on user type

    Examples:
      | login                               | password    |
      | sitBroker@csiodev.onmicrosoft.com   | Z$2JYbI;    |
      | sitCarrier@csiodev.onmicrosoft.com  | q#5PRs{S    |
      | admin.five@csiodev.onmicrosoft.com  | Si3ple9Ass  |

  Scenario Outline: Verify dashboard data filtering
    Given User is on dashboard tab
    When User enters dates and hit Apply button
      | From  | <fromDate>  |
      | To    | <toDate>    |
    Then Displayed data is filtered properly

    Examples:
      | fromDate    | toDate      |
      | 2018-01-01  | 2018-01-10  |

  Scenario Outline: Dashboard tiles navigation
    Given User is on dashboard tab
    When User enters dates
      | From  | <fromDate>  |
      | To    | <toDate>    |
    When User view all on '<tileType>' tile
    Then List of '<eslipType>' eSlips in '<state>' is displayed

    Examples:
      | fromDate    | toDate      | tileType      | eslipType   | state   |
      | 2018-01-01  | 2018-01-10  | Drafts        |             |         |
      | 2018-01-01  | 2018-01-10  | Sent          |             |         |
      | 2018-01-01  | 2018-01-10  | Received      |             |         |
      | 2018-01-01  | 2018-01-10  | Undelivered   |             |         |
      | 2018-01-01  | 2018-01-10  | Opened        |             |         |
      | 2018-01-01  | 2018-01-10  | Link clicked  |             |         |

  Scenario Outline: View All Drafts - different levels access
    Given User is on login page
    When User enters '<login>' and '<password>'
    When User navigates to Dashboard tab
    Then '<type>' page is displayed with proper filters

    Examples:
      | login                               | password    | type      |
      | sitBroker@csiodev.onmicrosoft.com   | Z$2JYbI;    | Drafts    |
      | sitCarrier@csiodev.onmicrosoft.com  | q#5PRs{S    | Drafts    |
      | admin.five@csiodev.onmicrosoft.com  | Si3ple9Ass  | Reporting |

  Scenario Outline: Verify reporting data filtering
    Given User is on reporting tab
    When User filters data with '<filter>' filters
    Then Proper data is displayed

    Examples:
      | filter                                                                  |
      | {'Customer Name':'bart'}                                                |
      | {'Status':'Clicked'; 'Customer E-Mail':'b.sternalski'}                  |
      | {'Status':'Undelivered, Failed'}                                        |
      | {'Type':'eDelivery'; 'Brokerage':'brokerUAT'; 'Customer Name':'baton'}  |
      | {'Policy Expiration Date':'2020-01-01, 2020-01-30'}                     |

  Scenario Outline: Calendar control canceling selection
    Given User is on dashboard tab
    When User selects dates and hit Cancel button
      | From  | <fromDate>  |
      | To    | <toDate>    |
    Then Data is not filtered with newly selected range

    Examples:
      | fromDate    | toDate      |
      | 2018-01-01  | 2018-01-10  |

  Scenario Outline: Calendar control canceling selection by clicking outside the box
    Given User is on dashboard tab
    When User selects dates and click outside calendar box
      | From  | <fromDate>  |
      | To    | <toDate>    |
    Then Data is not filtered with newly selected range

    Examples:
      | fromDate    | toDate      |
      | 2018-01-01  | 2018-01-10  |