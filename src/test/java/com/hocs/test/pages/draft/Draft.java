package com.hocs.test.pages.draft;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.create_case.AddDocuments;
import com.hocs.test.pages.create_case.SuccessfulCaseCreation;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.qa_response.QAResponse;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class Draft extends Page {

    AddDocuments addDocuments;

    Qa qa;

    @FindBy(css = "label[for=InitialDraftDecision-REJECT]")
    public WebElementFacade answeredByMyTeamNoRadioButton;

    @FindBy(css = "label[for=InitialDraftDecision-ACCEPT")
    public WebElementFacade answeredByMyTeamYesRadioButton;

    @FindBy(xpath = "//a[text()='document']")
    public WebElementFacade draftStageAddDocumentsButton;

    @FindBy(xpath = "//a[text()='Can this correspondence be answered by your team? is required']")
    public WebElementFacade correspondenceAnsweredErrorMessage;

    @FindBy(xpath = "//a[text()='Why should this should not be answered by your team? is required']")
    public WebElementFacade shouldBeAnsweredErrorMessage;

    @FindBy(xpath = "//a[text()='How do you intend to respond? is required']")
    public WebElementFacade howDoYouIntendToRespondErrorMessage;

    @FindBy(xpath = "//a[text()='Please summarise your call. is required']")
    public WebElementFacade pleaseSummariseYourCallIsRequiredErrorMessage;

    @FindBy(xpath = "//a[text()='Which is the primary draft document? is required']")
    public WebElementFacade whichIsThePrimaryDraftDocumentErrorMessage;

    @FindBy(xpath = "//a[text()='Do you want to QA this offline? is required']")
    public WebElementFacade doYouWantToQAThisOfflineErrorMessage;

    @FindBy(xpath = "//a[text()='Who has done the Offline QA for this case? is required']")
    public WebElementFacade whoHadDoneTheOfflineQAErrorMessage;

    @FindBy(id = "document_type")
    public WebElementFacade documentTypeDropDown;

    @FindBy(css = ".govuk-heading-l")
    public WebElementFacade draftAResponseHeader;

    @FindBy(id = "")
    public WebElementFacade draftingDeadline;

    @FindBy(id = "")
    public WebElementFacade rejectNoteField;

    @FindBy(id = "")
    public WebElementFacade emailReplyRadioButton;

    @FindBy(css = "label[for=ResponseChannel-LETTER]")
    public WebElementFacade letterReplyRadioButton;

    @FindBy(css = "label[for=ResponseChannel-PHONE]")
    public WebElementFacade phoneReplyRadioButton;

    @FindBy(id = "")
    public WebElementFacade allocationNoteFreeTextField;

    @FindBy(id = "")
    public WebElementFacade downloadTemplateLink;

    @FindBy(id = "")
    public WebElementFacade addResponseButton;

    @FindBy(id = "")
    public WebElementFacade supportingDocumentsNoRadioButton;

    @FindBy(id = "")
    public WebElementFacade supportingDocumentsYesRadioButton;

    @FindBy(id = "")
    public WebElementFacade addSupportingDocumentsButton;

    @FindBy(id = "")
    public WebElementFacade standardLine;

    @FindBy(css = "label[for=OfflineQA-TRUE]")
    public WebElementFacade offlineQaRadioButton;

    @FindBy(css = "label[for=OfflineQA-FALSE]")
    public WebElementFacade onlineQaRadioButton;

    @FindBy(id = "OfflineQaUser")
    public WebElementFacade allocateToOfflineQaDropdown;

    @FindBy(id = "")
    public WebElementFacade allocateToOnlineQaDropdown;

    @FindBy(xpath = "//textarea[@name='CaseNote_PhonecallNote']")
    public WebElementFacade summariseCallTextBox;

    @FindBy(xpath = "//textarea[@name='CaseNote_RejectionNote']")
    public WebElementFacade whyShouldThisNotBeAnsweredTextBox;

    // Basic Methods

    public void clickAddDocumentsButton() {
        draftStageAddDocumentsButton.click();
    }

    public void clearAllocationNoteField() {
        allocationNoteFreeTextField.clear();
    }

    public void selectDocumentTypeByIndex(int index) {
        documentTypeDropDown.selectByIndex(index);
    }

    public void enterAllocationNoteField() {
        clearAllocationNoteField();
        allocationNoteFreeTextField.sendKeys(generateRandomString());
    }

    public String getDraftingDeadline() {
        return draftingDeadline.getText();
    }

    public void selectOfflineQualityAssurer(String userName) {
        allocateToOfflineQaDropdown.selectByVisibleText(userName);
    }

    public void selectOnlineQualityAssurer() {
        allocateToOnlineQaDropdown.selectByVisibleText("");
    }

    public void enterTextInSummariseCallTextbox() {
        summariseCallTextBox.sendKeys(" ");
    }

    public void enterTextInWhyShouldThisBeAnsweredTextbox() {
        whyShouldThisNotBeAnsweredTextBox.sendKeys(" ");
    }

    // Multi Step Methods

    public void getToDraftCaseRejectionScreenPrerequisites() {
        clickOn(answeredByMyTeamNoRadioButton);
        clickOn(continueButton);
    }

    public void getToHowDoYouIntendToRespondScreenPrerequisites() {
        clickOn(answeredByMyTeamYesRadioButton);
        clickOn(continueButton);
        sleep(500);
    }

    public void getToSummariseYouCallScreenPrerequisites() {
        clickOn(answeredByMyTeamYesRadioButton);
        clickOn(continueButton);
        clickOn(phoneReplyRadioButton);
        clickOn(continueButton);
    }

    public void getToPrimaryDraftDocumentScreenPrerequisites() {
        clickOn(answeredByMyTeamYesRadioButton);
        clickOn(continueButton);
        clickOn(letterReplyRadioButton);
        clickOn(continueButton);
        sleep(500);
    }

    public void getToAddDocumentScreenPrerequisites() {
        clickOn(answeredByMyTeamYesRadioButton);
        clickOn(continueButton);
        clickOn(letterReplyRadioButton);
        clickOn(continueButton);
        clickOn(draftStageAddDocumentsButton);
    }

    public void getToDoYouWantToQAOfflineScreenPrerequisites() {
        clickOn(answeredByMyTeamYesRadioButton);
        clickOn(continueButton);
        clickOn(letterReplyRadioButton);
        clickOn(continueButton);
        clickOn(draftStageAddDocumentsButton);
        selectDocumentTypeByIndex(2);
        addDocuments.uploadDocument();
        clickOn(addDocuments.addButton);
        clickOn(continueButton);
        sleep(500);
    }

    public void getToWhoDidTheQAOfflineScreenPrerequisites() {
        clickOn(answeredByMyTeamYesRadioButton);
        clickOn(continueButton);
        clickOn(letterReplyRadioButton);
        clickOn(continueButton);
        clickOn(draftStageAddDocumentsButton);
        selectDocumentTypeByIndex(2);
        addDocuments.uploadDocument();
        clickOn(addDocuments.addButton);
        clickOn(continueButton);
        clickOn(qa.offlineQaYesRadioButton);
        clickOn(continueButton);
        clickOn(finishButton);
    }

    // Assertions

    public void assertEnterCallNotesError() {
        assertThat(getErrorDetails(), is("Text to be confirmed"));
    }

    public void assertEnterQaMethodError() {
        assertThat(getErrorDetails(), is("Text to be confirmed"));
    }

    public void assertEnterRejectionReasonsError() {
        assertThat(getErrorDetails(), is("Text to be confirmed"));
    }

    public void assertCorrespondenceAnsweredErrorMessage() {
        assertThat(correspondenceAnsweredErrorMessage.getText(),
                is("Can this correspondence be answered by your team? is required"));
    }

    public void assertShouldBeAnsweredErrorMessage() {
        assertThat(shouldBeAnsweredErrorMessage.getText(),
                is("Why should this should not be answered by your team? is required"));
    }

    public void assertHowDoYouIntendToRespondErrorMessage() {
        assertThat(howDoYouIntendToRespondErrorMessage.getText(),
                is("How do you intend to respond? is required"));
    }

    public void assertPleaseSummariseYourCallErrorMessage() {
        assertThat(pleaseSummariseYourCallIsRequiredErrorMessage.getText(), is("Please summarise your call. is required"));
    }

    public void assertWhichIsThePrimaryDraftDocumentErrorMessage() {
        assertThat(whichIsThePrimaryDraftDocumentErrorMessage.getText(),
                is("Which is the primary draft document? is required"));
    }

    public void assertDoYouWantToQAThisOfflineErrorMessage() {
        assertThat(doYouWantToQAThisOfflineErrorMessage.getText(), is("Do you want to QA this offline? is required"));
    }

    public void assertWhoHasDoneOfflineQAErrorMessage() {
        assertThat(whoHadDoneTheOfflineQAErrorMessage.getText(),
                is("Who has done the Offline QA for this case? is required"));
    }

    public void draftingDeadlineIsDisplayed() {
        assertThat(isElementDisplayed(draftingDeadline), is(true));
    }

}
