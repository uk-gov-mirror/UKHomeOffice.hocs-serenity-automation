Feature:  HOCS User is able to Dispatch a Response

  Background:
    Given I am user "EAMON"
    And I get a "DCU MIN" case at "DISPATCH" stage

  @HOCS-542
  Scenario: User has a hard copy of a case to dispatch, they decide to reject it and fill in a rejection reason
    When I "reject" the case
    And I enter "someString" in the "Reject Reason" field
    And I click the "FINISH" button
    Then the case should be moved to the "Private Office Approval" stage

  @HOCS-542
  Scenario: User has a hard copy of a case to dispatch, they decide to reject it and don't fill in a rejection reason
    When I attempt to reject the "DISPATCH" case without reason
    Then an error message is displayed

  @HOCS-443 @FixMe
  Scenario: User has a hard copy of a case to dispatch, they decide to accept the case
    When I "DISPATCH" the case
    Then the case is completed

  @HOCS-443 @DoesntFit
  Scenario: Dispatch a case with Copy to Number Ten selected
    Given the case had the "send copy to number 10" box checked
    When I "dispatch" the case
    Then the case should be moved to the "Send copy to number 10" stage
    And the "nominated person" in the "transfers and number 10" team get a notification email
    And I am taken to the "home" page

  @Navigation
  Scenario: Clicking the cancel button on the allocate case screen at the Dispatch stage should take the user back to the
  dashboard
    And I click the cancel button
    Then I should be taken to the homepage

  @Validation
  Scenario: User must select a radio button when asked if they able to dispatch the case at the Dispatch Stage
    And I click the continue button on the are you able to dispatch screen
    Then an error message should be displayed as I have selected whether the case can be dispatched

  @Validation
  Scenario: User must enter their reason for not being able to dispatch case in the text box at the Dispatch Stage
    And I click the "FINISH" button on the "UNABLE TO DISPATCH" page
    Then an error message should be displayed as I have not entered a reason for not dispatching in the text box

  @Validation
  Scenario: User must enter text in the text box when creating a Case note at the Dispatch stage
    And I click the add button when creating a case note
    Then an error message should be displayed as I have not added any text into the case note text box