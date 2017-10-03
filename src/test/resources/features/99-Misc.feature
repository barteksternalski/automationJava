Feature: Verify miscellaneous functionalities

  Scenario: User can lookup for existing carrier
    Given At least 2 carrier organizations exists
    When User creates new eSlip with 'carrier' carrier
    Then Carrier is selected from list

  Scenario: User cannot enter non-existing carrier
    Given At least 2 carrier organizations exists
    When User creates new eSlip with non-existing 'carrier2' carrier
    Then Carrier field is empty

  Scenario: User can lookup for existing broker
    Given At least 2 broker organizations exists
    When User creates new eSlip with 'broker' broker
    Then Broker is selected from list

  Scenario: User can enter non-existing broker
    Given At least 2 broker organizations exists
    When User creates new eSlip with non-existing 'broker2' broker
    Then Broker field is filled

  Scenario: Create new eEslip as Carrier with assigned organization
    Given Carrier with 'carrier' organization assigned is logged to system
    When User carrier creates new eSlip
    Then Carrier field is prefilled with current carrier organization

  Scenario: Create new eEslip as Broker with assigned organization
    Given Broker with 'broker' organization assigned is logged to system
    When User carrier creates new eSlip
    Then Broker field is prefilled with current carrier organization