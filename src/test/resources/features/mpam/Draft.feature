@Drafting
Feature: Drafting

  Background:
    Given I log in to DECS

  @Navigation
  Scenario: User should be on the MPAM Draft Page
    When I create a "MPAM" case and move it to the "Draft" stage
    And I load and claim the current case
    Then the "MPAM Draft" page should be displayed

  @MPAMWorkflow @SmokeTests
  Scenario: User completes the Draft stage
    When I create a "MPAM" case and move it to the "Draft" stage
    And I load and claim the current case
    When I complete the "Draft" stage
    Then the case should be moved to the "QA" stage

  Scenario: User moves a draft case to dispatch bypassing QA
    When I create a MPAM case  with "UKVI" as the Business Area and "B:Ref" as the Reference Type and move it to the "Draft" stage
    And I load and claim the current case
    And I move a B:Ref case from Draft to Dispatch bypassing QA
    Then the case should be moved to the "Awaiting Dispatch" stage

  Scenario: User escalates the draft case to workflow manager
    When I create a "MPAM" case and move it to the "Draft" stage
    And I load and claim the current case
    And I send the Draft case to "Workflow Manager"
    Then the case should be moved to the "Draft (Escalated)" stage

  Scenario: User puts the draft case on hold
    When I create a "MPAM" case and move it to the "Draft" stage
    And I load and claim the current case
    And I send the Draft case to "On Hold"
    Then the case should be moved to the "Draft (On Hold)" stage