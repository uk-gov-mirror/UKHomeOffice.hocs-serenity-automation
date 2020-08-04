package com.hocs.test.pages.dcu;

import com.hocs.test.pages.AddCorrespondent;
import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.Workstacks;
import com.hocs.test.pages.Homepage;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class DataInput extends BasePage {

    AddCorrespondent addCorrespondent;

    // Elements

    @FindBy(css = "label[for='OriginalChannel-EMAIL']")
    public WebElementFacade emailOriginalChannelRadioButton;

    @FindBy(css = "label[for='OriginalChannel-POST']")
    public WebElementFacade postOriginalChannelRadioButton;

    @FindBy(css = "label[for='OriginalChannel-PHONE']")
    public WebElementFacade phoneOriginalChannelRadioButton;

    @FindBy(css = "label[for='OriginalChannel-NO10']")
    public WebElementFacade numberTenOriginalChannelRadioButton;

    @FindBy(id = "DateOfCorrespondence-day")
    public WebElementFacade dateCorrespondenceSentDayField;

    @FindBy(id = "DateOfCorrespondence-month")
    public WebElementFacade dateCorrespondenceSentMonthField;

    @FindBy(id = "DateOfCorrespondence-year")
    public WebElementFacade dateCorrespondenceSentYearField;

    @FindBy(id = "DateReceived-day")
    public WebElementFacade dateCorrespondenceReceivedDayField;

    @FindBy(id = "DateReceived-month")
    public WebElementFacade dateCorrespondenceReceivedMonthField;

    @FindBy(id = "DateReceived-year")
    public WebElementFacade dateCorrespondenceReceivedYearField;

    @FindBy(css = "label[for='CopyNumberTen-TRUE']")
    public WebElementFacade shouldResponseBeCopiedN10YesRadioButton;

    @FindBy(css = "label[for='CopyNumberTen-FALSE']")
    public WebElementFacade shouldResponseBeCopiedN10NoRadioButton;

    @FindBy(xpath = "//input[@name='DCU_DTEN_INITIAL_DRAFT_DEADLINE-day']")
    public WebElementFacade dtenDraftingDeadlineDayField;

    @FindBy(xpath = "//input[@name='DCU_DTEN_INITIAL_DRAFT_DEADLINE-month']")
    public WebElementFacade dtenDraftingDeadlineMonthField;

    @FindBy(xpath = "//input[@name='DCU_DTEN_INITIAL_DRAFT_DEADLINE-year']")
    public WebElementFacade dtenDraftingDeadlineYearField;

    @FindBy(xpath = "//input[@name='DCU_DTEN_DISPATCH_DEADLINE-day']")
    public WebElementFacade dtenDispatchDeadlineDayField;

    @FindBy(xpath = "//input[@name='DCU_DTEN_DISPATCH_DEADLINE-month']")
    public WebElementFacade dtenDispatchDeadlineMonthField;

    @FindBy(xpath = "//input[@name='DCU_DTEN_DISPATCH_DEADLINE-year']")
    public WebElementFacade dtenDispatchDeadlineYearField;

    @FindBy(xpath = "//a[text()='When was the correspondence sent? is required']")
    public WebElementFacade correspondenceDateErrorMessage;

    @FindBy(xpath = "//a[text()='How was the correspondence received? is required']")
    public WebElementFacade howWasCorrespondenceReceivedErrorMessage;

    @FindBy(xpath = "//span[text()='Should the response be copied to Number 10? is required']")
    public WebElementFacade shouldTheResponseBeCopiedN10ErrorMessage;

    @FindBy(css = "label[for='HomeSecInterest-TRUE']")
    public WebElementFacade homeSecInterestYesRadioButton;

    @FindBy(css = "label[for='HomeSecInterest-FALSE']")
    public WebElementFacade homeSecInterestNoRadioButton;

    // Multi Step Methods

    public void moveCaseFromDataInputToMarkup() {
        fillAllMandatoryCorrespondenceFields();
        safeClickOn(continueButton);
        addCorrespondent.addAPublicCorrespondent();
        safeClickOn(finishButton);
    }

    public void dataInputFullFlowWithCopyToN10() {
        fillAllMandatoryCorrespondenceFieldsWithCopyToNumberTenYes();
        safeClickOn(continueButton);
        addCorrespondent.addAPublicCorrespondent();
        safeClickOn(finishButton);
    }

    public void clearDateCorrespondenceReceived() {
        dateCorrespondenceReceivedDayField.clear();
        dateCorrespondenceReceivedMonthField.clear();
        dateCorrespondenceReceivedYearField.clear();
    }

    public void clearDateCorrespondenceSent() {
        dateCorrespondenceSentDayField.clear();
        dateCorrespondenceSentMonthField.clear();
        dateCorrespondenceSentYearField.clear();
    }

    public void enterDayOfCorrespondenceReceived(String day) {
        typeInto(dateCorrespondenceReceivedDayField, day);
    }

    public void enterMonthOfCorrespondenceReceived(String month) {
        typeInto(dateCorrespondenceReceivedMonthField, month);
    }

    public void enterYearOfCorrespondenceReceived(String year) {
        typeInto(dateCorrespondenceReceivedYearField, year);
    }

    public void enterDayOfCorrespondenceSent(String day) {
        typeInto(dateCorrespondenceSentDayField, day);
    }

    public void enterMonthOfCorrespondenceSent(String month) {
        typeInto(dateCorrespondenceSentMonthField, month);
    }

    public void enterYearOfCorrespondenceSent(String year) {
        typeInto(dateCorrespondenceSentYearField, year);
    }

    public void fillAllMandatoryCorrespondenceFields() {
        String caseType = sessionVariableCalled("caseType");
        if (caseType.equals("DTEN")) {
            typeInto(dtenDraftingDeadlineDayField, "01");
            typeInto(dtenDraftingDeadlineMonthField, "01");
            typeInto(dtenDraftingDeadlineYearField, "2019");
            setSessionVariable("dtenInitialDraftDeadline").to("01/01/2019");
            typeInto(dtenDispatchDeadlineDayField, "01");
            typeInto(dtenDispatchDeadlineMonthField, "01");
            typeInto(dtenDispatchDeadlineYearField, "2019");
            setSessionVariable("dtenDispatchDeadline").to("01/01/2019");
            safeClickOn(continueButton);
            enterDayOfCorrespondenceSent(todayPlusMinusNDaysGetDay(-2));
            enterMonthOfCorrespondenceSent(todayPlusMinusNMonthsGetMonth(-2));
            enterYearOfCorrespondenceSent(todayPlusMinusNYearsGetYear(-2));
            enterDayOfCorrespondenceReceived(getCurrentDay());
            enterMonthOfCorrespondenceReceived(getCurrentMonth());
            enterYearOfCorrespondenceReceived(getCurrentYear());
            safeClickOn(emailOriginalChannelRadioButton);
        } else {
            enterDayOfCorrespondenceSent(todayPlusMinusNDaysGetDay(-2));
            enterMonthOfCorrespondenceSent(todayPlusMinusNMonthsGetMonth(-2));
            enterYearOfCorrespondenceSent(todayPlusMinusNYearsGetYear(-2));
            enterDayOfCorrespondenceReceived(getCurrentDay());
            enterMonthOfCorrespondenceReceived(getCurrentMonth());
            enterYearOfCorrespondenceReceived(getCurrentYear());
            safeClickOn(emailOriginalChannelRadioButton);
            safeClickOn(shouldResponseBeCopiedN10NoRadioButton);
            safeClickOn(homeSecInterestYesRadioButton);
        }
    }

    public void fillAllMandatoryCorrespondenceFieldsWithCopyToNumberTenYes() {
        enterDayOfCorrespondenceSent(todayPlusMinusNDaysGetDay(-2));
        enterMonthOfCorrespondenceSent(todayPlusMinusNMonthsGetMonth(-2));
        enterYearOfCorrespondenceSent(todayPlusMinusNYearsGetYear(-2));
        enterDayOfCorrespondenceReceived(getCurrentDay());
        enterMonthOfCorrespondenceReceived(getCurrentMonth());
        enterYearOfCorrespondenceReceived(getCurrentYear());
        safeClickOn(emailOriginalChannelRadioButton);
        safeClickOn(shouldResponseBeCopiedN10YesRadioButton);
        safeClickOn(homeSecInterestYesRadioButton);
    }

    public void invalidCorrespondenceReceivedDate() {
        enterDayOfCorrespondenceReceived(tomorrowsDay());
        enterMonthOfCorrespondenceReceived(tomorrowsMonth());
        enterYearOfCorrespondenceReceived(tomorrowsYear());
    }

    public void invalidCorrespondenceSentDate() {
        enterDayOfCorrespondenceSent(tomorrowsDay());
        enterMonthOfCorrespondenceSent(tomorrowsMonth());
        enterYearOfCorrespondenceSent(tomorrowsYear());
    }

    public void completeDataInputStageWithMPCorrespondent(String correspondent) {
        fillAllMandatoryCorrespondenceFields();
        clickContinueButton();
        addCorrespondent.addAMemberCorrespondent(correspondent);
        safeClickOn(finishButton);
    }

    public void completeDataInputStageAndStoreEnteredInformation() {
        enterDayOfCorrespondenceSent(getCurrentDay());
        String currentDay = dateCorrespondenceSentDayField.getValue();
        setSessionVariable("currentDay").to(currentDay);

        enterMonthOfCorrespondenceSent(getCurrentMonth());
        String currentMonth = dateCorrespondenceSentMonthField.getValue();
        setSessionVariable("currentMonth").to(currentMonth);

        enterYearOfCorrespondenceSent(getCurrentYear());
        String currentYear = dateCorrespondenceSentYearField.getValue();
        setSessionVariable("currentYear").to(currentYear);

        safeClickOn(emailOriginalChannelRadioButton);
        String selectedCorrespondenceReceivedRadioButton = emailOriginalChannelRadioButton.getText();
        setSessionVariable("selectedCorrespondenceReceivedRadioButton").to(selectedCorrespondenceReceivedRadioButton);

        safeClickOn(shouldResponseBeCopiedN10NoRadioButton);
        String selectedCopiedN10NoRadioButton = shouldResponseBeCopiedN10NoRadioButton.getTextContent();
        setSessionVariable("selectedCopiedN10NoRadioButton").to(selectedCopiedN10NoRadioButton);

        safeClickOn(continueButton);
        waitABit(500);
        addCorrespondent.addAMemberCorrespondent("Nicola Sturgeon MSP");
        safeClickOn(finishButton);
    }

    public void completeDataInputStageWithThreeMPCorrespondents() {
        fillAllMandatoryCorrespondenceFields();
        clickContinueButton();
        addCorrespondent.addAMemberCorrespondent("Boris Johnson");
        addCorrespondent.addAMemberCorrespondent("Nicola Sturgeon");
        addCorrespondent.addAMemberCorrespondent("Theresa May");
        safeClickOn(finishButton);
    }

    public void completeDataInputWithThreePublicCorrespondents() {
        fillAllMandatoryCorrespondenceFields();
        clickContinueButton();
        addCorrespondent.addAPublicCorrespondent();
        addCorrespondent.addAPublicCorrespondent();
        addCorrespondent.addAPublicCorrespondent();
        safeClickOn(finishButton);
    }

    public void completeDataInputStageWithHomeSecInterestNo() {
        enterDayOfCorrespondenceSent(todayPlusMinusNDaysGetDay(-2));
        enterMonthOfCorrespondenceSent(todayPlusMinusNMonthsGetMonth(-2));
        enterYearOfCorrespondenceSent(todayPlusMinusNYearsGetYear(-2));
        enterDayOfCorrespondenceReceived(getCurrentDay());
        enterMonthOfCorrespondenceReceived(getCurrentMonth());
        enterYearOfCorrespondenceReceived(getCurrentYear());
        safeClickOn(emailOriginalChannelRadioButton);
        safeClickOn(shouldResponseBeCopiedN10NoRadioButton);
        safeClickOn(homeSecInterestNoRadioButton);
        safeClickOn(continueButton);
        addCorrespondent.addAPublicCorrespondent();
        safeClickOn(finishButton);
    }

    // Assertions

    public void assertPageTitle() {
        assertPageTitle("Data Input");
    }

    public void assertCorrespondenceDateErrorMessage() {
        correspondenceDateErrorMessage.shouldContainText("When was the correspondence sent? is required");
    }

    public void assertHowWasCorrespondenceReceivedErrorMessage() {
        howWasCorrespondenceReceivedErrorMessage.shouldContainText("How was the correspondence received? is required");
    }

    public void assertShouldResponseBeCopiedN10ErrorMessage() {
        shouldTheResponseBeCopiedN10ErrorMessage.shouldContainText("Should the response be copied to Number 10? is required");
    }
}
