package com.hocs.test.glue.mpam;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.mpam.AccordionMPAM;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;


public class MPAMCaseDetailsAccordionStepDefs extends BasePage {

    AccordionMPAM accordionMPAM;

    @And("the {string} accordion in case details should display the correct information for {string}")
    public void accordionInCaseDetailsDisplaysCorrectInformation(String accordion, String responseType) {
        switch (accordion.toUpperCase()) {
            case "CREATION":
                accordionMPAM.openCreationAccordion();
                break;
            case "TRIAGE":
                accordionMPAM.openTriageAccordion();
                break;
            case "DRAFT":
                accordionMPAM.openDraftAccordion();
                break;
            default:
                pendingStep(accordion + " is not defined within " + getMethodName());
        }
        accordionMPAM.getQuestionResponse(responseType);
        accordionMPAM.assertInputMatchesCaseDetailsResponse(responseType);
    }

    @And("the {string} accordion contains all of the correct information previously input")
    public void accordionInCaseDetailsContainsAllCorrectInformation(String accordion) {
        switch (accordion.toUpperCase()) {
            case "CREATION":
                accordionMPAM.openCreationAccordion();
                accordionMPAM.assertAllCreationResponsesMatchInput();
                break;
            case "TRIAGE":
                accordionMPAM.openTriageAccordion();
                accordionMPAM.assertAllTriageResponsesMatchInput();
                break;
            case "DRAFT":
                accordionMPAM.openDraftAccordion();
                accordionMPAM.assertAllDraftResponsesMatchInput();
                break;
            default:
                pendingStep(accordion + " is not defined within " + getMethodName());
        }
    }

    @And("I change the business area of the case to {string}")
    public void iChangeTheBusinessAreaOfTheCase(String newBusinessArea) {
        safeClickOn(accordionMPAM.caseDetailsAccordionButton);
        accordionMPAM.changeBusinessAreaAndUnit(newBusinessArea);
    }

    @And("I change the Ministerial sign off team of the case to {string}")
    public void changeMinisterialSignOffTeamOfCase(String newSignOffTeam) {
        if (!accordionMPAM.ministerialSignOffTeamDropdown.isVisible()) {
            accordionMPAM.openCaseDetailsAccordion();
        }
        accordionMPAM.changeMinisterialSignOffTeam(newSignOffTeam);
    }

    @Then("the case should have changed to the {string} business area")
    public void assertBusinessAreaChange(String businessArea) {
        accordionMPAM.assertBusinessAreaHasChanged(businessArea);
    }

    @And("I trigger the {string} error message and it is displayed at the change business area screen")
    public void assertErrorMessageDisplayedAtChangeBusinessArea(String errorMessage) {
        accordionMPAM.openCaseDetailsAccordion();
        safeClickOn(accordionMPAM.changeBusinessAreaHypertext);
        switch (errorMessage.toUpperCase()) {
            case "BUSINESS UNIT REQUIRED":
                safeClickOn(continueButton);
                break;
            case "ACTIONS REQUIRED":
                accordionMPAM.selectBusinessArea("UKVI");
                accordionMPAM.businessUnitDropdown.selectByIndex(1);
                safeClickOn(continueButton);
                safeClickOn(confirmButton);
                break;
            default:
                pendingStep(errorMessage + " is not defined within " + getMethodName());
        }
        accordionMPAM.assertChangeBusinessAreaErrorMessageIsDisplayed(errorMessage);
    }

    @Then("the change business area hypertext {string} displayed at {string}")
    public void assertChangeBusinessAreaVisibilityAtStage(String visibleOrNot, String stage) {
        accordionMPAM.openCaseDetailsAccordion();
        switch (visibleOrNot.toUpperCase()) {
            case "IS":
            case "ISN'T":
                accordionMPAM.assertChangeBusinessAreaHyperTextIsAtStage(stage);
                break;
            default:
                pendingStep(visibleOrNot + " is not defined within " + getMethodName());
        }
    }

    @And("I change the reference type of the case to {string}")
    public void iChangeReferenceTypeOfCase(String reason) {
        accordionMPAM.openCaseDetailsAccordion();
        switch (reason.toUpperCase()) {
            case "CONVERT THE CASE":
                accordionMPAM.changeRefTypeConvertingACase();
                break;
            case "CORRECT AN ERROR":
                accordionMPAM.changeRefTypeCorrectingAnError();
                break;
            default:
                pendingStep(reason + " is not defined within " + getMethodName());
        }
    }

    @Then("the reference type that is displayed should be {string}")
    public void refTypeIsCorrect(String refType) {
        accordionMPAM.openCaseDetailsAccordion();
        accordionMPAM.assertRefTypeHasChanged(refType);
    }
}