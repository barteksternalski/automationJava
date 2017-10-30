@Main
Feature: Manage eSlip list
  As a User I want to have possibility to view list of existing eSlips

  Scenario: User can see list of drafted eSlips
    Given User is on dashboard screen
    When User navigates to list of drafts
    Then List of created drafts is displayed

  Scenario: User can preview sent eSlip
    Given User is on dashboard screen
    When User navigates to list of sent eSlips
    When User previews one of sent eSlip
    Then ESlip preview is displayed

  Scenario: User can send eSlip from create single form
    Given User is creating single eSlip
    When User sends created eSlip
    Then ESlip is saved in DB
    Then ESlip status is changed to Sent
    Then ESlip is not displayed on drafts list

  Scenario Outline: Selected carrier user is not able to see drafts created by broker
    Given Broker created eSlip draft with '<carrier>' carrier
    When '<carrier>' logs in to application
    When Carrier navigates to list of drafted eSlips
    Then Created eSlip is not visible

    Examples:
      | carrier   |
      |           |

  Scenario Outline: User can clone sent eSlip
    Given User is on login page
    When User enters '<login>' and '<password>'
    When User navigates to Sent tab
    When User clones selected '<name>' eSlip
    Then ESlip '<name>' is displayed on Drafts list
    Then Customer Information is copied
    Then Policy Information is copied
    Then Vehicles are copied
    Then Back text sections are copied

    Examples:
      | login			                          | password		| name      |
      #csio admin
      #boker admin
      #broker user
      #carrier admin
      #carrier user

  Scenario Outline: User can clone multiple sent eSlips
    Given User is on login page
    When User enters '<login>' and '<password>'
    When User navigates to Sent tab
    When User clones selected eSlips
      | ESlip list  | <list>  |
    Then ESlips are displayed on Drafts list
      | ESlip list  | <list>  |

    Examples:
      | list        |
      | 1, 2, ,3 ,4 |

  Scenario Outline: User can delete drafted eSlip
    Given User is on login page
    When User enters '<login>' and '<password>'
    When User navigates to Draft tab
    When User deletes selected '<name>' eSlip
    Then ESlip '<name>' is not displayed on Drafts list

    Examples:
      | login			                          | password		| name      |
      #csio admin
      #boker admin
      #broker user
      #carrier admin
      #carrier user

  Scenario Outline: User can delete undelivered eSlip
    Given Undelivered eSlips exists in system
    Given User is on login page
    When User enters '<login>' and '<password>'
    When User navigates to Draft tab
    When User deletes selected '<name>' eSlip
    Then ESlip '<name>' is not displayed on Drafts list
    Then Generated PKPasses are removed from BlobStorage

    Examples:
      | login			                          | password		| name      |
      #csio admin
      #boker admin
      #broker user
      #carrier admin
      #carrier user