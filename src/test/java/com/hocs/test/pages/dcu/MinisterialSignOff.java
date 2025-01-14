package com.hocs.test.pages.dcu;

import com.hocs.test.pages.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import static net.serenitybdd.core.Serenity.setSessionVariable;

public class MinisterialSignOff extends BasePage {

    @FindBy(css = "label[for='MinisterSignOffDecision-ACCEPT']")
    public WebElementFacade ministerSignOffAcceptRadioButton;

    @FindBy(css = "label[for='MinisterSignOffDecision-REJECT']")
    public WebElementFacade ministerSignOffRejectRadioButton;

    @FindBy(id = "CaseNote_MinisterReject")
    public WebElementFacade ministerRejectionNote;

    @FindBy(xpath = "//a[text()='Do you approve the response? is required']")
    public WebElementFacade doYouApproveTheResponseErrorMessage;

    @FindBy(xpath = "//a[text()='What is your feedback about the response? is required']")
    public WebElementFacade whatIsYourFeedbackMinisterSignOffErrorMessage;

    @FindBy(xpath = "//label[text()='Not applicable']")
    public WebElementFacade notApplicableRadioButton;

    @FindBy(id = "CaseNote_MinisterNotApplicable")
    public WebElementFacade whyIsCaseNotApplicableFreeTextField;

    // Basic Methods

    public void enterMinisterRejectionNote() {
        typeInto(ministerRejectionNote, generateRandomString());
    }

    public void getToMinisterFeedbackResponseScreenPrerequisites() {
        safeClickOn(ministerSignOffRejectRadioButton);
        safeClickOn(continueButton);
        waitABit(500);
    }

    public void assertDoYouApproveTheResponseErrorMessage() {
        doYouApproveTheResponseErrorMessage.shouldContainText("Do you approve the response? is required");
    }

    public void assertFeedbackResponseMinisterSignOffErrorMessage() {
        whatIsYourFeedbackMinisterSignOffErrorMessage.shouldContainText("What is your feedback about the response? is required");
    }

    public void moveCaseFromMinisterToDispatch() {
        safeClickOn(ministerSignOffAcceptRadioButton);
        safeClickOn(continueButton);
    }

    public void completeMinisterialSignOffStageAndStoreEnteredInformation() {
        safeClickOn(ministerSignOffAcceptRadioButton);
        setSessionVariable("ministerialSignOffDecision").to(ministerSignOffAcceptRadioButton.getTextContent());
        safeClickOn(continueButton);
    }

    public void moveCaseFromMinisterSignOffToPrivateOfficeApproval() {
        safeClickOn(notApplicableRadioButton);
        safeClickOn(continueButton);
        typeInto(whyIsCaseNotApplicableFreeTextField, "Test");
        setSessionVariable("rejectionReason").to("Test");
        safeClickOn(continueButton);
    }
}
