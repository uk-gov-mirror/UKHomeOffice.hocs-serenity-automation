package com.hocs.test.glue.dcu;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.Homepage;
import com.hocs.test.pages.dcu.MinisterialSignOff;
import com.hocs.test.pages.Workstacks;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class MinisterialSignOffStepDefs extends BasePage {

    Homepage homepage;

    MinisterialSignOff minister;

    Workstacks workstacks;

    @When("I complete the Ministerial Sign Off stage")
    public void completeTheMinisterSignOffStagePerCaseType() {
        String caseType = sessionVariableCalled("caseType");
        switch (caseType.toUpperCase()) {
            case "MIN" :
                if (homepage.myCases.isVisible()) {
                    homepage.getCurrentCase();
                    safeClickOn(workstacks.allocateToMeButton);
                }
                safeClickOn(minister.ministerSignOffAcceptRadioButton);
                safeClickOn(minister.continueButton);
                break;
            case "TRO" :
            case "DTEN" :
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    @Then("an error message should be displayed as I have not selected a radio button on the approve response screen")
    public void assertThatApproveResponseErrorMessageIsShown() {
        minister.assertDoYouApproveTheResponseErrorMessage();
    }

    @Then("an error message should be displayed as I have not entered feedback in the text box")
    public void assertThatFeedbackResponseMinisterSignOffErrorMessageIsShown() {
        minister.assertFeedbackResponseMinisterSignOffErrorMessage();
    }

}