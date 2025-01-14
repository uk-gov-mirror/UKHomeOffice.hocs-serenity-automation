package com.hocs.test.pages.mpam;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.Dashboard;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class DispatchStages extends BasePage {

    Dashboard dashboard;

    @FindBy(id = "DateDispatched-day")
    public WebElementFacade dispatchedDateDayTextField;

    @FindBy(id = "DateDispatched-month")
    public WebElementFacade dispatchedDateMonthTextField;

    @FindBy(id = "DateDispatched-year")
    public WebElementFacade dispatchedDateYearTextField;

    @FindBy(css = "label[for='ChannelOut-Email']")
    public WebElementFacade responseChannelEmailRadioButton;

    @FindBy(css = "label[for='ChannelOut-Letter']")
    public WebElementFacade responseChannelLetterRadioButton;

    @FindBy(css = "label[for='ChannelOut-Phone']")
    public WebElementFacade responseChannelPhoneRadioButton;

    @FindBy(css = "label[for='ChannelOut-Outreach']")
    public WebElementFacade responseChannelOutreachRadioButton;

    @FindBy(xpath = "//label[text()='Dispatch, close case']")
    public WebElementFacade dispatchedCloseCaseRadioButton;

    @FindBy(xpath = "//label[text()='Draft rejected by private office']")
    public WebElementFacade draftRejectedRadioButton;

    @FindBy(xpath = "//label[text()='Dispatch, close case']")
    public WebElementFacade dispatchedRadioButtonAtDispatch;

    @FindBy(xpath = "//label[text()='Dispatched']")
    public WebElementFacade dispatchedRadioButtonAtPrivateOffice;

    @FindBy(xpath = "//label[text()='Dispatched (follow-up)']")
    public WebElementFacade dispatchedFollowUpRadioButton;

    @FindBy(xpath = "//label[text()='Return to Draft']")
    public WebElementFacade returnToDraftButton;

    @FindBy(xpath = "//label[text()='Follow-up completed']")
    public WebElementFacade followUpCompletedRadioButton;

    @FindBy(xpath = "//label[text()='Close, follow-up action not completed']")
    public WebElementFacade closeFollowUpNotCompletedRadioButton;

    @FindBy(xpath = "//a[text()='Dispatched date is required']")
    public WebElementFacade dispatchedDateRequiredErrorMessage;

    @FindBy(xpath = "//a[text()='Response channel is required']")
    public WebElementFacade responseChannelRequiredErrorMessage;

    @FindBy(xpath = "//a[text()='Actions is required']")
    public WebElementFacade actionsRequiredErrorMessage;

    @FindBy(xpath = "//a[@href='#DueDate-error']")
    public WebElementFacade followUpDateRequiredErrorMessage;

    @FindBy(xpath = "//a[@href='#CaseNote_DispatchFollowUpRequest-error']")
    public WebElementFacade followUpDetailsRequiredErrorMessage;

    @FindBy(xpath = "//a[@href='#CaseNote_DispatchFollowUpNotCompleted-error']")
    public WebElementFacade followUpNotCompletedReasonRequiredErrorMessage;

    @FindBy(css = "input[value='Confirm and close case']")
    public WebElementFacade confirmAndCloseCaseButton;

    @FindBy(id = "CaseNote_RejectPfs")
    public WebElementFacade rejectionReasonTextArea;

    @FindBy(id = "CaseNote_DispatchReturnToDraft")
    public WebElementFacade returnReasonTextArea;

    @FindBy(id = "CaseNote_DispatchFollowUpNotCompleted")
    public WebElementFacade followUpNotCompletedReasonTextArea;

    @FindBy(xpath = "//input[@name='ChannelOut'][@checked]/following-sibling::label")
    public WebElementFacade selectedResponseChannel;

    @FindBy(id = "DueDate-day")
    public WebElementFacade dueDateDayTextField;

    @FindBy(id = "DueDate-month")
    public WebElementFacade dueDateMonthTextField;

    @FindBy(id = "DueDate-year")
    public WebElementFacade dueDateYearTextField;

    @FindBy(id = "CaseNote_DispatchFollowUpRequest")
    public WebElementFacade followUpDetailsTextArea;

    public void inputDispatchedDate(String date) {
        typeIntoDateField(dispatchedDateDayTextField, dispatchedDateMonthTextField, dispatchedDateYearTextField,
                date);
    }

    public void moveCaseFromPrivateOfficeToCaseClosed() {
        safeClickOn(responseChannelEmailRadioButton);
        safeClickOn(dispatchedRadioButtonAtPrivateOffice);
        safeClickOn(confirmButton);
        inputDispatchedDate(getDatePlusMinusNDaysAgo(-1));
        safeClickOn(confirmAndCloseCaseButton);
    }

    public void moveCaseFromAwaitingDispatchToCaseClosed() {
        inputDispatchedDate(getDatePlusMinusNDaysAgo(-1));
        safeClickOn(dispatchedRadioButtonAtDispatch);
        safeClickOn(confirmButton);
    }

    public void triggerAwaitingDispatchErrorMessage(String message) {
        switch (message.toUpperCase()) {
            case "DISPATCHED DATE REQUIRED":
                safeClickOn(dispatchedCloseCaseRadioButton);
                safeClickOn(confirmButton);
                break;
            case "ACTIONS REQUIRED":
                inputDispatchedDate(getDatePlusMinusNDaysAgo(-1));
                safeClickOn(confirmButton);
                break;
            case "FOLLOW-UP DATE REQUIRED":
                inputDispatchedDate(getDatePlusMinusNDaysAgo(-1));
                safeClickOn(dispatchedFollowUpRadioButton);
                safeClickOn(confirmButton);
                enterFollowUpDetails("Test follow-up details");
                safeClickOn(confirmButton);
                break;
            case "FOLLOW-UP DETAILS REQUIRED":
                inputDispatchedDate(getDatePlusMinusNDaysAgo(-1));
                safeClickOn(dispatchedFollowUpRadioButton);
                safeClickOn(confirmButton);
                followUpDateInput(getDatePlusMinusNDaysAgo(1));
                safeClickOn(confirmButton);
                break;
            case "FOLLOW-UP NOT COMPLETED REASON REQUIRED":
                inputDispatchedDate(getDatePlusMinusNDaysAgo(-1));
                safeClickOn(dispatchedFollowUpRadioButton);
                safeClickOn(confirmButton);
                followUpDateInput(getDatePlusMinusNDaysAgo(1));
                enterFollowUpDetails("Test follow-up details");
                safeClickOn(confirmButton);
                dashboard.getCurrentCase();
                safeClickOn(closeFollowUpNotCompletedRadioButton);
                safeClickOn(confirmButton);
                safeClickOn(confirmAndCloseCaseButton);
                break;
            default:
                pendingStep(message + " is not defined within " + getMethodName());
        }
    }

    public void triggerPrivateOfficeErrorMessage(String message) {
        switch (message.toUpperCase()) {
            case "DISPATCHED DATE REQUIRED":
                safeClickOn(dispatchedRadioButtonAtPrivateOffice);
                safeClickOn(confirmButton);
                safeClickOn(confirmAndCloseCaseButton);
                break;
            case "ACTIONS REQUIRED":
                safeClickOn(confirmButton);
                break;
            case "FOLLOW-UP DATE REQUIRED":
                safeClickOn(dispatchedFollowUpRadioButton);
                safeClickOn(confirmButton);
                inputDispatchedDate(getDatePlusMinusNDaysAgo(-1));
                enterFollowUpDetails("Test follow-up details");
                safeClickOn(confirmButton);
                break;
            case "FOLLOW-UP DETAILS REQUIRED":
                safeClickOn(dispatchedFollowUpRadioButton);
                safeClickOn(confirmButton);
                inputDispatchedDate(getDatePlusMinusNDaysAgo(-1));
                followUpDateInput(getDatePlusMinusNDaysAgo(1));
                safeClickOn(confirmButton);
                break;
            case "FOLLOW-UP NOT COMPLETED REASON REQUIRED":
                safeClickOn(dispatchedFollowUpRadioButton);
                safeClickOn(confirmButton);
                inputDispatchedDate(getDatePlusMinusNDaysAgo(-1));
                followUpDateInput(getDatePlusMinusNDaysAgo(1));
                enterFollowUpDetails("Test follow-up details");
                safeClickOn(confirmButton);
                dashboard.getCurrentCase();
                safeClickOn(closeFollowUpNotCompletedRadioButton);
                safeClickOn(confirmButton);
                safeClickOn(confirmAndCloseCaseButton);
                break;
            default:
                pendingStep(message + " is not defined within " + getMethodName());
        }
    }

    public void assertDispatchedDateRequiredErrorMessageDisplayed() {
        dispatchedDateRequiredErrorMessage.shouldContainText("Dispatched date is required");
    }

    public void assertResponseChannelErrorMessageDisplayed() {
        responseChannelRequiredErrorMessage.shouldContainText("Response channel is required");
    }

    public void assertActionsRequiredErrorMessageDisplayed() {
        actionsRequiredErrorMessage.shouldContainText("Actions is required");
    }

    public void submitReasonToRejectToDraft(String rejectionReason) {
        typeInto(rejectionReasonTextArea, rejectionReason);
        safeClickOn(confirmButton);
        setSessionVariable("rejectionReason").to(rejectionReason);
    }

    public void submitReasonToReturnToDraft(String returnReason) {
        typeInto(returnReasonTextArea, returnReason);
        safeClickOn(confirmButton);
        setSessionVariable("rejectionReason").to(returnReason);
    }

    public void assertThatRejectionReasonTextAreaVisible() {
        assertThat(rejectionReasonTextArea.isVisible(), is(true));
    }

    public void assertThatResponseChannelIsPreSelected() {
        String previouslySelectedResponseChannel = sessionVariableCalled("responseChannel").toString().toUpperCase();
        String currentlySelectedResponseChannel = selectedResponseChannel.getText().toUpperCase();
        assertThat(currentlySelectedResponseChannel.equals(previouslySelectedResponseChannel), is(true));
    }

    public void followUpDateInput(String date) {
        typeIntoDateField(dueDateDayTextField, dueDateMonthTextField, dueDateYearTextField, date);
        setSessionVariable("dueDate").to(date);
    }

    public void enterFollowUpDetails(String followUpDetails) {
        typeInto(followUpDetailsTextArea, followUpDetails);
        setSessionVariable("followUpDetails").to(followUpDetails);
    }

    public void enterReasonForNotCompletingFollowUp(String reason) {
        typeInto(followUpNotCompletedReasonTextArea, reason);
        setSessionVariable("followUpNotCompletedReason").to(reason);
    }

    public void assertFollowUpDateRequiredErrorMessageDisplayed() {
        followUpDateRequiredErrorMessage.shouldContainText("Follow-up due by is required");
    }

    public void assertFollowUpDetailsRequiredErrorMessageDisplayed() {
        followUpDetailsRequiredErrorMessage.shouldContainText("Details of follow up is required");
    }

    public void assertFollowUpNotCompletedReasonRequiredErrorMessageDisplayed() {
        followUpNotCompletedReasonRequiredErrorMessage.shouldContainText("Details of not completing the follow up action is required");
    }
}
