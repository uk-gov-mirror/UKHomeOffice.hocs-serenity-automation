package com.hocs.test.pages.timeline;

import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.base_page.Page;
import com.hocs.test.pages.workstacks.Workstacks;
import config.Users;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class TimelineTab extends Page {

    Workstacks workstacks;

    @FindBy(xpath = "//a[@class='tab'][text()='Timeline']")
    public WebElementFacade timelineTab;

    @FindBy(xpath = "//span[@class='govuk-details__summary-text']")
    public WebElementFacade addCaseNoteButton;

    @FindBy(xpath = "//textarea[@id='case-note']")
    public WebElementFacade caseNoteTextbox;

    @FindBy(xpath = "//span[@id='case-note-error']")
    public WebElementFacade caseNoteMustNotBeBlankErrorMessage;

    @FindBy(xpath = "//div[@class='timeline']//li[1]")
    public WebElementFacade topCaseNoteOrLog;

    @FindBy(xpath = "//div[@class='timeline']//li[1]/p[1]")
    public WebElementFacade topNoteContents;

    @FindBy(xpath = "//div[@class='timeline']//li[1]/p[2]")
    public WebElementFacade topNoteSignature;

    @FindBy(xpath = "//div[@class='timeline']//li[2]/p[1]")
    public WebElementFacade secondNoteContents;

    public void selectTimelineTab() {
        clickOn(timelineTab);
    }

    public void clickAddCaseNote() {
        clickOn(addCaseNoteButton);
    }

    public void enterTextIntoCaseNote(String text) {
        clickOn(caseNoteTextbox);
        typeInto(caseNoteTextbox, text);
    }

    public void createACaseNote() {
        clickAddCaseNote();
        String noteContents = generateRandomString();
        setSessionVariable("createdNoteContents").to(noteContents);
        enterTextIntoCaseNote(noteContents);
        clickAddButton();
        waitABit(1000);
    }

    public void assertTopNoteContainsEnteredText(String text) {
        topNoteContents.shouldContainText(text);
    }

    public void assertCaseNoteAtTopOfTimeline() {
        assertThat(topCaseNoteOrLog.getAttribute("class").equals("case-note"), is(true));
    }

    public void assertLogAtTopOfTimeline() {
        assertThat(topCaseNoteOrLog.getClass().equals("case-note"), is(false));
    }

    public void assertTopNoteSignatureContainsCreator(Users user) {
        topNoteSignature.shouldContainText(user.getUsername());
    }

    public void assertCaseNoteMustNotBeBlankErrorMessage() {
        caseNoteMustNotBeBlankErrorMessage.shouldContainText("Case note must not be blank");
    }

    public void assertAllocationLogVisible(Users user, String stage) {
        WebElementFacade allocationLog = findBy("//strong[text()='Allocated to " + user.getUsername() +
                "']/parent::p/following-sibling::p[text()='" + stage + "']");
        try {
            assertThat(allocationLog.isVisible(), is(true));
        } catch (AssertionError e) {
            workstacks.selectSummaryTab();
            selectTimelineTab();
            assertThat(allocationLog.isVisible(), is(true));
        }
    }

    public void assertStageCompletionLogVisible(String stage) {
        WebElementFacade stageCompletionLog = findBy("//div[@class='timeline']//li/p[1]/strong[text()="
                + "'Stage: " + stage + " Completed']");
        try {
            assertThat(stageCompletionLog.isVisible(), is(true));
        } catch (AssertionError e) {
            workstacks.selectSummaryTab();
            selectTimelineTab();
            assertThat(stageCompletionLog.isVisible(), is(true));
        }
    }

    public void assertStageStartedLogVisible(String stage) {
        WebElementFacade stageStartedLog = findBy("//div[@class='timeline']//li/p[1]/strong[text()="
                + "'Stage: " + stage + " Started']");
        try {
            assertThat(stageStartedLog.isVisible(), is(true));
        } catch (AssertionError e) {
            workstacks.selectSummaryTab();
            selectTimelineTab();
            assertThat(stageStartedLog.isVisible(), is(true));
        }
    }

    public void createAnotherCaseNote() {
        String noteContents = generateRandomString();
        setSessionVariable("secondNoteContents").to(noteContents);
        enterTextIntoCaseNote(noteContents);
        clickAddButton();
        waitABit(1000);
    }

    public void assertSecondNoteContainsEnteredText(String text) {
        secondNoteContents.shouldContainText(text);
    }
}
