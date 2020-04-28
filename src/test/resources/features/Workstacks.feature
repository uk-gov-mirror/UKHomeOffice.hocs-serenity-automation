@Workstacks
Feature: Workstacks

  Background:
    Given I am user "AUTOMATION_USER"

  @SmokeTests
  Scenario: User creates a case and allocates to another user
    And I create a new case and view it in the Performance and Process team workstack
    When I assign the current case number to "CASEY"
    Then the owner field should display "CASEY"

  @SmokeTests
  Scenario: User creates a case and assigns it to themselves from the workstack
    And I create a new case and view it in the Performance and Process team workstack
    When I assign this case to me, and check if it has been correctly allocated

  @SmokeTests
  Scenario: User creates 3 cases and allocates these cases to another User
    And I create three cases, and view them in performance and process workstack
    Then I assign these three cases to "CASEY"
    And I check that the three cases created have been correctly assigned to "CASEY"

  @SmokeTests
  Scenario: User creates and allocates 3 cases to another User, then unallocates these cases
    And I create three cases, and assign them to "CASEY"
    Then I view these cases in Performance and Process workstack, and unallocate from "CASEY"
    And I then check whether the correct cases have been unallocated

  @SmokeTests
  Scenario: User creates a Ministerial case and uses the MIN filter card to view it in the Performance and Process workstack
    Given I create a single "MIN" case and return to the dashboard
    When I enter the Performance and Process team workstack and narrow down the visible cases using the "MIN" filter card
    Then only "MIN" cases should be visible
    And the created case should be visible in the workstack

  @SmokeTests
  Scenario: User creates a Number 10 case and uses the DTEN filter card to view it in the Transfers and No10 team workstack
    Given I create a single "DTEN" case and return to the dashboard
    When I enter the Transfers and No10 team workstack and narrow down the visible cases using the TRO filter card
    Then only "DTEN" cases should be visible
    And the created case should be visible in the workstack

  @SmokeTests
  Scenario: User creates a Treat Official case and uses the TRO filter card to view it in the Performance and Process
  workstack
    Given I create a single "TRO" case and return to the dashboard
    When I enter the Performance and Process team workstack and narrow down the visible cases using the "TRO" filter card
    Then only "TRO" cases should be visible
    And the created case should be visible in the workstack

