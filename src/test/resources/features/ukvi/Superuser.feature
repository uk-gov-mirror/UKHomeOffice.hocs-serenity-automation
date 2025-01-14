@Superuser @UKVI
Feature: Superuser

  @UKVIRegression @Smoketest
  Scenario: MPAM Superuser can complete the entire MPAM workflow
    Given I switch to user "MPAM_SUPERUSER"
    When I create a "MPAM" case and move it to the "Case Closed" stage
    Then the case should be closed

  @UKVIRegression
  Scenario: Superuser can complete a stage whilst case is owned by another user
    Given I switch to user "UKVI_USER"
    And I create a single "MPAM" case
    When I allocate the case to myself via the successful case creation screen
    And I switch to user "MPAM_SUPERUSER"
    And I load the current case
    And I complete the "Creation" stage
    Then the case should be moved to the "Triage" stage
    And I logout of the application
    