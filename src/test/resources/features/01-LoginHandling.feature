@Main
Feature: Login handling
  As u user I want to have possibility to login to website

#LGN-01
  Scenario Outline: User can login
    Given User is on login page
    When User enters '<login>' and '<password>'
    Then Main page is displayed

    Examples:
      | login			          | password		|
      |	b.sternalski@avanade.com  | baton   		|