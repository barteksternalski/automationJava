@Smoke @Main
Feature: Landing Page

  Scenario: Open landing page
    Given Open url "http://cssitcacweb01-dev.azurewebsites.net/"
    When User navigates to eslips drafts
    Then Page title is "Drafts"