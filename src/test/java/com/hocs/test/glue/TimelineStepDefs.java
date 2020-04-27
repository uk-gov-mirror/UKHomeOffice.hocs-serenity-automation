package com.hocs.test.glue;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.TimelineTab;
import config.Users;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class TimelineStepDefs extends BasePage {

    TimelineTab timelineTab;

    @And("I select the Timeline tab")
    public void iSelectTheTimelineTab() {
        waitABit(5000);
        timelineTab.selectTimelineTab();
    }

    @And("I create a case note with random content")
    public void iCreateACaseNoteWithRandomContent() {
        timelineTab.createACaseNote();
    }

    @Then("a note should be created at the top of the timeline")
    public void aNoteShouldBeCreatedAtTheTopOfTheTimeline() {
        timelineTab.assertCaseNoteAtTopOfTimeline();
    }

    @And("it should have the same content")
    public void itShouldHaveTheSameContent() {
        timelineTab.assertTopNoteContainsEnteredText(sessionVariableCalled("createdNoteContents"));
    }

    @And("it should state that user {string} created it")
    public void itShouldStateThatUserCreatedIt(String user) {
        timelineTab.assertTopNoteSignatureContainsCreator(Users.valueOf(user));
    }

    @Then("a log should be at the top of the timeline")
    public void aLogShouldBeAtTheTopOfTheTimeline() {
        timelineTab.assertLogAtTopOfTimeline();
    }

    @Then("there should be a log showing the case was allocated to user {string} at stage {string}")
    public void thereShouldBeALogShowingTheCaseWasAllocatedToUserAtStage(String user, String stage) {
        timelineTab.assertAllocationLogVisible(Users.valueOf(user), stage);
    }

    @And("a log should be visible for completing the {string} stage")
    public void aLogShouldBeVisibleForCompletingTheStage(String stage) {
        timelineTab.assertStageCompletionLogVisible(stage);
    }

    @And("a log should be visible for starting the {string} stage")
    public void aLogShouldBeVisibleForStartingTheStage(String stage) {
        timelineTab.assertStageStartedLogVisible(stage);
    }

    @And("I attempt to add an empty case note")
    public void iAttemptToAddAnEmptyCaseNote() {
        timelineTab.clickAddCaseNote();
        clickAddButton();
    }

    @Then("an error message is displayed as I have not entered text content for the case note")
    public void anErrorMessageIsDisplayedAsIHaveNotEnteredTextContentForTheCaseNote() {
        timelineTab.assertCaseNoteMustNotBeBlankErrorMessage();
    }

    @And("I create another case note with random content")
    public void iCreateAnotherCaseNoteWithRandomContent() {
        timelineTab.createAnotherCaseNote();
    }

    @Then("the top note in the timeline should be the second note created")
    public void theTopNoteInTheTimelineShouldBeTheSecondNoteCreated() {
        timelineTab.assertTopNoteContainsEnteredText(sessionVariableCalled("secondNoteContents"));
    }

    @And("the one below it should be the first note created")
    public void theOneBelowItShouldBeTheFirstNoteCreated() {
        timelineTab.assertSecondNoteContainsEnteredText(sessionVariableCalled("createdNoteContents"));
    }

    @And("I edit the top case note")
    public void iEditTopCaseNote() {
        timelineTab.editACase("Test 1");
    }

    @And("the case note should appear between the {string} logs")
    public void editedCaseNoteDisplaysCorrectValue(String stage) {
        timelineTab.assertEditedCaseNoteAppearInCorrectStage(stage);
    }

    @And("the case note should contain the edited content")
    public void itShouldHaveEditedContent() {
        timelineTab.assertTopNoteContainsEnteredText("Case note 1." + sessionVariableCalled("createdNoteContents"));
    }
}
