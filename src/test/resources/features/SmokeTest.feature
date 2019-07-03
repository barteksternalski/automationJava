Feature: Selenide demo

  Scenario: Search is working
    Given User opens Google page
    When User searches for 'selenide'
    Then Proper result 'Selenide: concise UI tests in Java' is displayed
