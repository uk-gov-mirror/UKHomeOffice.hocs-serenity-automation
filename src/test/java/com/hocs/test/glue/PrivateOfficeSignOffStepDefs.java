package com.hocs.test.glue;
import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.accordion.CaseDetailsAccordion;
import com.hocs.test.pages.create_case.SuccessfulCaseCreation;
import com.hocs.test.pages.private_office.PrivateOffice;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.data_input.DataInput;
import com.hocs.test.pages.workstacks.Workstacks;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import net.thucydides.core.annotations.Managed;

import org.openqa.selenium.WebDriver;


public class PrivateOfficeSignOffStepDefs extends Page {

    @Managed
    WebDriver driver;

    Homepage homepage;

    PrivateOffice privateOffice;

    Workstacks workstacks;

    CaseDetailsAccordion caseDetailsAccordion;

    @When("^I complete the Private Office stage$")
    public void completePrivateOfficeStage() {
        homepage.getCurrentCase();
        clickOn(workstacks.allocateToMeButton);
        clickOn(privateOffice.privateOfficeAcceptRadioButton);
        clickOn(privateOffice.continueButton);
    }

    @When("^I complete the Private Office stage for \"([^\"]*)\"$")
    public void completePrivateOfficeStagePerCaseType(String caseType) {
        switch(caseType.toUpperCase()) {
            case "DCU MIN" :
                homepage.getCurrentCase();
                clickOn(workstacks.allocateToMeButton);
                clickOn(privateOffice.privateOfficeAcceptRadioButton);
                clickOn(privateOffice.continueButton);
                break;
            case "DCU TRO" :
                break;
            case "DCU N10" :
                homepage.getCurrentCase();
                clickOn(workstacks.allocateToMeButton);
                clickOn(privateOffice.privateOfficeAcceptRadioButton);
                clickOn(privateOffice.continueButton);
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    @When("^I click the continue button on PO approve response screen$")
    public void clickContinueButtonOnPOApproveResponseScreen() {
        clickOn(privateOffice.continueButton);
    }

    @Then("^an error message should be displayed as I have not selected whether I approve the response$")
    public void assertThatApprovedResponseErrorMessageIsShown() {
        privateOffice.assertDoYouApproveTheResponseErrorMessage();
    }

    @When("^I click the finish button on the change minister screen$")
    public void clickFinishButtonOnChangeMinisterScreen() {
        clickOn(privateOffice.privateOfficeChangeMinisterRadioButton);
        clickOn(privateOffice.continueButton);
        clickOn(privateOffice.finishButton);
    }

    @Then("^error messages should be displayed as I have not selected an override team or entered change reasoning$")
    public void assertThatChangeMinisterErrorMessagesAreShown() {
        privateOffice.assertChangeMinisterErrorMessages();
    }

    @When("^I click the finish button on the what is your feedback response screen$")
    public void clickFinishButtonOnWhatIsFeedbackResponseScreen() {
        clickOn(privateOffice.privateOfficeRejectRadioButton);
        clickOn(privateOffice.continueButton);
        clickOn(privateOffice.finishButton);
    }

    @Then("^an error message should be displayed as I have not entered feedback into the text box")
    public void assertThatFeedbackResponseErrorMessageIsShown() {
        privateOffice.assertWhatIsYourFeedbackResponse();
    }

    @And("^I select to change minister$")
    public void iSelectToChangeMinister() {
        privateOffice.getToChangeMinisterScreenPrerequisites();
    }

    @And("^I select \"([^\"]*)\" as the new Private Office team$")
    public void iSelectAsTheNewMinister(String newPOTeam) {
        privateOffice.selectNewPrivateOfficeTeamFromDropdown(newPOTeam);
    }

    @And("^I enter \"([^\"]*)\" as the reason for changing Private Office team$")
    public void iEnterAsTheReasonForChangingPrivateOfficeTeam(String reason) {
        privateOffice.enterAReasonForChangingPOTeam(reason);
    }

    @Then("^the information shown should match what I entered on the change Private Office Team page$")
    public void theInformationShownShouldMatchWhatIEnteredOnTheChangePrivateOfficeTeamPage() {
        caseDetailsAccordion.assertAccordionPrivateOfficeApprovalFieldsAfterPOTeamChange();
    }
}
