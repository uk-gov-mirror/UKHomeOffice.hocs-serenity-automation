@Markup
Feature: Markup

  Background:
    Given I am user "AUTOMATION_USER"
    When I create a "DTEN" case and move it to the "MARKUP" stage
    And I load and claim the current case

  @SmokeTests
  Scenario Outline: Central Drafting Team user selects an initial decision of Policy Response or FAQ
    When I select an initial decision of "<radioButton>"
    And I click the "CONTINUE" button
    And I click the "ADD A TOPIC" button
    Then a mandatory Topic free text field is displayed
    Examples:
      | radioButton     |
      | Policy Response |
      | FAQ             |

  Scenario: User selects an initial decision of Transfer to OGD
    When I select an initial decision of "REFER TO OGD"
    And I click the "CONTINUE" button
    Then the Other Government Department name free text field is displayed

  Scenario: User selects an initial decision of No Response Needed
    When I select an initial decision of "NO RESPONSE NEEDED"
    And I click the "CONTINUE" button
    Then the No Response Needed casenote field is displayed

  @Validation
  Scenario: User does not enter department in free text field
    When I select an initial decision of "Refer to OGD"
    And I click the "CONTINUE" button
    But I do not enter a "Other Government Department"
    Then an error message is displayed

  Scenario: User selects an initial decision of Reject to Data Input
    When I select an initial decision of "REJECT TO DATA INPUT"
    And I click the "CONTINUE" button
    Then the reason for rejection casenote field is displayed

  @Validation
  Scenario: User does not enter reasons for no reply needed
    When I select an initial decision of "NO RESPONSE NEEDED"
    And I click the "CONTINUE" button
    But I do not enter a "REASON FOR NO RESPONSE NEEDED"
    Then an error message is displayed

  @Validation
  Scenario: User does not enter reason for rejecting case to Data Input
    When I select an initial decision of "REJECT TO DATA INPUT"
    And I click the "CONTINUE" button
    But I do not enter a "REASON FOR REJECTING TO DATA INPUT"
    Then an error message is displayed

  @SmokeTests
  Scenario: User selects topic
    When I select an initial decision of "POLICY RESPONSE"
    And I click the "CONTINUE" button
    And I add the topic "CARDIFF UNIVERSITY KITTENS"
    Then the topic should be added to the case

  Scenario: User can select a topic for a FAQ response
    When I select an initial decision of "FAQ"
    And I click the "CONTINUE" button
    And I add the topic "CARDIFF UNIVERSITY KITTENS"
    Then the topic should be added to the case

  @Validation
  Scenario: User must select a response on the first Markup Stage screen
    And I click the "CONTINUE" button
    Then an error message should be displayed as I have not selected a response

  @Validation
  Scenario: User must add a topic at the Markup Stage
    And I click the "CONTINUE" button on the "ADD A TOPIC" page
    Then an error message should be displayed as I have not added a topic

  @Validation
  Scenario: User must select a topic from the dropdown box at the Markup Stage
    And I click the "ADD" button on the "ENTER A NEW TOPIC" page
    Then an error message should be displayed as I have not selected a topic

  @Validation
  Scenario: User must enter text in the text box when creating a Case note at the Markup stage
    And I click the add button when creating a case note
    Then an error message should be displayed as I have not "ADDED ANY TEXT INTO THE CASE NOTE TEXT BOX"

  @Workflow @SmokeTests
  Scenario: Case is returned to Data Input stage when rejected at Markup stage
    And I reject the case at the "MARKUP" stage
    Then the "DTEN" case should be moved to the "DATA INPUT" stage





  