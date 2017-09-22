@Main
Feature: Manage eSlip list
  As a User I want to have possibility to view list of existing eSlips

  Scenario: User can see list of drafted eSlips
    Given User is on dashboard screen
    When User navigates to list of drafts
    Then List of created drafts is displayed

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