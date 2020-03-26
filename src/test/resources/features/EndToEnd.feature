Feature: End To End

  Background:
    Given I am user "AUTOMATION_USER"

  @EndToEnd @Workflow @SmokeTests
  Scenario Outline: New case moves to Data Input stage
    When I create a single case "<caseType>"
    Then the "<caseType>" case should be moved to the "DATA INPUT" stage
    Examples:
      | caseType|
      | MIN  |
      | TRO  |
      | DTEN |

  @EndToEnd @Workflow @SmokeTests
  Scenario Outline: New case moves to Markup stage
    When I create a single case "<caseType>"
    And I complete the Data Input Stage for "<caseType>" case type
    Then the "<caseType>" case should be moved to the "MARKUP" stage
    Examples:
      | caseType|
      | MIN |
      | TRO |
      | DTEN |

  @EndToEnd @Workflow @SmokeTests
  Scenario Outline: New case moves to Initial Draft stage
    When I create a single case "<caseType>"
    And I complete the Data Input Stage for "<caseType>" case type
    And I complete the markup stage
    Then the "<caseType>" case should be moved to the "INITIAL DRAFT" stage
    Examples:
      | caseType|
      | MIN |
      | TRO |
      | DTEN |

  @EndToEnd @Workflow @SmokeTests
  Scenario Outline: New case moves to QA Response stage
    When I create a single case "<caseType>"
    And I complete the Data Input Stage for "<caseType>" case type
    And I complete the markup stage
    And I complete the Initial Draft stage for "<caseType>" case type
    Then the "<caseType>" case should be moved to the "QA RESPONSE" stage
    Examples:
      | caseType|
      | MIN |
      | TRO |
      | DTEN |

  @EndToEnd @Workflow @SmokeTests
  Scenario Outline: New case moves to Private Office stage
    When I create a single case "<caseType>"
    And I complete the Data Input Stage for "<caseType>" case type
    And I complete the markup stage
    And I complete the Initial Draft stage for "<caseType>" case type
    And I complete the QA response stage
    Then the "<caseType>" case should be moved to the "PRIVATE OFFICE APPROVAL" stage
    Examples:
      | caseType|
      | MIN |
      | DTEN |

  @EndToEnd @Workflow @SmokeTests
  Scenario Outline: New case moves to Minister Sign Off stage
    When I create a single case "<caseType>"
    And I complete the Data Input Stage for "<caseType>" case type
    And I complete the markup stage
    And I complete the Initial Draft stage
    And I complete the QA response stage
    And I complete the Private Office stage for "<caseType>"
    Then the "<caseType>" case should be moved to the "MINISTERIAL SIGN OFF" stage
    Examples:
      | caseType|
      | MIN |

  @EndToEnd @Workflow @SmokeTests
  Scenario Outline: New case moves to Dispatch stage
    When I create a single case "<caseType>"
    And I complete the Data Input Stage for "<caseType>" case type
    And I complete the markup stage
    And I complete the Initial Draft stage for "<caseType>" case type
    And I complete the QA response stage
    And I complete the Private Office stage for "<caseType>"
    And I complete the minister sign off stage for "<caseType>"
    Then the "<caseType>" case should be moved to the "DISPATCH" stage
    Examples:
      | caseType|
      | MIN |
      | TRO |
      | DTEN |

  @EndToEnd @Workflow @SmokeTests
  Scenario: Dispatch a case with Copy to Number Ten selected
    Given I create a single case "MIN"
    And I complete the Data Input stage and send a copy to Number Ten
    And I complete the markup stage
    And I complete the Initial Draft stage for "MIN" case type
    And I complete the QA response stage
    And I complete the Private Office stage
    And I complete the minister sign off stage
    And I complete the dispatch stage
    Then the "MIN" case should be moved to the "COPY TO NUMBER 10" stage

  @EndToEnd @EndToEnd @SmokeTests
  Scenario: End to end flow with DCU MIN CaseType
    When I create a single case "MIN"
    And I complete the Data Input Stage for "MIN" case type
    And I complete the markup stage
    And I complete the Initial Draft stage
    And I complete the QA response stage
    And I complete the Private Office stage
    And I complete the minister sign off stage
    And I complete the dispatch stage
    Then the case is completed

  @EndToEnd @SmokeTests
  Scenario: End to end flow with DCU N10 CaseType
    When I create a single case "DTEN"
    And I complete the Data Input Stage for "DTEN" case type
    And I complete the markup stage
    And I complete the Initial Draft stage
    And I complete the QA response stage
    And I complete the Private Office stage
    And I complete the dispatch stage
    Then the case is completed

  @EndToEnd @SmokeTests
  Scenario: End to end flow with DCU TRO CaseType
    When I create a single case "TRO"
    And I complete the Data Input Stage for "TRO" case type
    And I complete the markup stage
    And I complete the Initial Draft stage for "TRO" case type
    And I complete the QA response stage
    And I complete the dispatch stage
    Then the case is completed

  Scenario: User creates a MIN case and completes the workflow
    And I create a "MIN" case and move it to the "CASE CLOSED" stage

  Scenario: User creates a TRO case and completes the workflow
    And I create a "TRO" case and move it to the "CASE CLOSED" stage

  Scenario: User creates a DTEN case and completes the workflow
    And I create a "DTEN" case and move it to the "CASE CLOSED" stage