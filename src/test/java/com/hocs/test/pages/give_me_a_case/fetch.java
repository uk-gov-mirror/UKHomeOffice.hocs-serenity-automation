package com.hocs.test.pages.give_me_a_case;

import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.markup.MarkUpDecision;
import net.serenitybdd.core.pages.WebElementFacade;

import com.hocs.test.pages.workstacks.Workstacks;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.dispatch.Dispatch;
import com.hocs.test.pages.minister.MinisterSignOff;
import com.hocs.test.pages.private_office.PrivateOffice;
import com.hocs.test.pages.qa_response.QAResponse;
import com.hocs.test.pages.draft.DraftingTeamDecision;
import com.hocs.test.pages.markup.MarkupFull;
import com.hocs.test.pages.data_input.DataInput;
import com.hocs.test.pages.create_case.CreateCase;
import net.thucydides.core.webdriver.exceptions.ElementShouldBeEnabledException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class fetch extends Page {

    CreateCase createCase;

    DataInput dataInput;

    MarkupFull markupFullFlow;

    DraftingTeamDecision draftingTeamDecision;

    QAResponse qa;

    PrivateOffice privateOffice;

    MinisterSignOff minister;

    Dispatch dispatch;

    Homepage homepage;

    Workstacks workstacks;

    Page page;

    MarkUpDecision markUpDecision;

    WebDriver driver;

    public void getFirstUnallocatedMINCaseDataInputCase() {
        WebElementFacade firstUnallocatedMINCase = findAll(
                "//td[following-sibling::td[1][contains(text(), 'Data "
                        + "Input')]][following-sibling::td[2]"
                        + "[not(contains(text(), '@'))]][descendant::a[contains(text(), 'MIN')]]").get(0);
        firstUnallocatedMINCase.click();
        page.clickOn(workstacks.allocateToMeButton);
    }

    public void getFirstUnallocatedMINCaseMarkupCase() {
        WebElementFacade firstUnallocatedMINCase = findAll("//td[following-sibling::td[1][contains(text(), "
                + "'Markup')]][following-sibling::td[2]"
                + "[not(contains(text(), '@'))]][descendant::a[contains(text(), 'MIN')]]").get(0);
        firstUnallocatedMINCase.click();
        page.clickOn(workstacks.allocateToMeButton);
    }

    public void getFirstUnallocatedMINInitialDraftCase() {
        WebElementFacade firstUnallocatedMINCase = findAll("//td[following-sibling::td[1][contains(text(), "
                + "'Initial Draft')]][following-sibling::td[2]"
                + "[not(contains(text(), '@'))]][descendant::a[contains(text(), 'MIN')]]").get(0);
        firstUnallocatedMINCase.click();
        page.clickOn(workstacks.allocateToMeButton);
    }

    public void getFirstUnallocatedMINQAResponseCase() {
        WebElementFacade firstUnallocatedMINCase = findAll("//td[following-sibling::td[1][contains(text(), "
                + "'QA Response')]][following-sibling::td[2]"
                + "[not(contains(text(), '@'))]][descendant::a[contains(text(), 'MIN')]]").get(0);
        firstUnallocatedMINCase.click();
        page.clickOn(workstacks.allocateToMeButton);
    }

    public void getFirstUnallocatedMINPrivateOfficeCase() {
        WebElementFacade firstUnallocatedMINCase = findAll("//td[following-sibling::td[1][contains(text(), "
                + "'Private Office Approval')]][following-sibling::td[2]"
                + "[not(contains(text(), '@'))]][descendant::a[contains(text(), 'MIN')]]").get(0);
        firstUnallocatedMINCase.click();
        page.clickOn(workstacks.allocateToMeButton);
    }

    public void getFirstUnallocatedMINMinisterialSignOffCase() {
        WebElementFacade firstUnallocatedMINCase = findAll("//td[following-sibling::td[1][contains(text(), "
                + "'Ministerial Sign off')]][following-sibling::td[2]"
                + "[not(contains(text(), '@'))]][descendant::a[contains(text(), 'MIN')]]").get(0);
        firstUnallocatedMINCase.click();
        page.clickOn(workstacks.allocateToMeButton);
    }

    public void getFirstUnallocatedMINDispatchCase() {
        WebElementFacade firstUnallocatedMINCase = findAll(
                "//td[following-sibling::td[1][contains(text(), 'Dispatch')]][following-sibling::td[2]"
                        + "[not(contains(text(), '@'))]][descendant::a[contains(text(), 'MIN')]]").get(0);
        firstUnallocatedMINCase.click();
        page.clickOn(workstacks.allocateToMeButton);
    }

    public void giveMeACase(String caseType, String stage) {
        setSessionVariable("caseType").to(caseType);
        setSessionVariable("stage").to(stage);
        switch (stage.toUpperCase()) {
            case "DATA INPUT":
                getFirstUnallocatedDataInputCase(caseType);
                break;
            case "MARKUP":
                getFirstUnallocatedMarkupCase(caseType);
                break;
            case "INITIAL DRAFT":
                // Only Cardiff University Kittens ATM, add random topics later
                getFirstUnallocatedInitialDraftCase(caseType);
                break;
            case "QA RESPONSE":
                getFirstUnallocatedQaResponseCase(caseType);
                break;
            case "PRIVATE OFFICE APPROVAL":
                // As in draft stage, only cardiff uni kittens as Current topic so
                // always come to this team for now, add random topics later
                // Might not need this as we are just interested in 'a case at a stage'
                getFirstUnallocatedPrivateOfficeCase(caseType);
                break;
            case "MINISTERIAL SIGN OFF":
                getFirstUnallocatedMinisterialSignOffCase(caseType);
                break;
            case "DISPATCH":
                getFirstUnallocatedDispatchCase(caseType);
                break;
            default:
                pendingStep(stage + " is not defined within " + getMethodName());
        }
    }

    public void getFirstUnallocatedDataInputCase(String caseType) {
        switch (caseType.toUpperCase()) {
            case "DCU MIN":
                try {
                    page.clickOn(homepage.performanceProcessTeam);
                } catch (ElementShouldBeEnabledException e) {
                    System.out.println("Performance Process Team not available, therefore there are no Data Input cases, "
                            + "creating a new case");
                    page.clickOn(homepage.home);
                    createCase.createDCUMinSingleCase();
                    String thisCaseType = sessionVariableCalled("caseType").toString();
                    String thisStage = sessionVariableCalled("stage").toString();
                    page.clickOn(homepage.home);
                    giveMeACase(thisCaseType, thisStage);
                }
                try {
                    getFirstUnallocatedMINCaseDataInputCase();
                    try {
                        dataInput.dateCorrespondenceSentDayField.clear();
                    } catch (ElementShouldBeEnabledException | org.openqa.selenium.NoSuchElementException e) {
                        System.out.println("Date Sent field not available, searching for a fresh Data Input case");
                        page.clickOn(homepage.home);
                        getFirstUnallocatedDataInputCase(caseType);
                    }
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println("I couldn't find a Data Input case so I am building a new case");
                    page.clickOn(homepage.home);
                    createCase.createDCUMinSingleCase();
                    page.clickOn(homepage.home);
                    page.clickOn(homepage.performanceProcessTeam);
                    getFirstUnallocatedMINCaseDataInputCase();
                }
                break;
            case "DCU TRO":
                break;
            case "DCU N10":
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    public void getFirstUnallocatedMarkupCase(String caseType) {
        switch (caseType.toUpperCase()) {
            case "DCU MIN":
                try {
                    page.clickOn(homepage.centralDraftingTeam);
                } catch (ElementShouldBeEnabledException e) {
                    System.out.println("Central Drafting Team not available, therefore there are no Markup cases, "
                            + "searching for a Data Input Case");
                    page.clickOn(homepage.home);
                    getFirstUnallocatedDataInputCase(caseType);
                    dataInput.moveCaseFromDataInputToMarkup();
                    String thisCaseType = sessionVariableCalled("caseType").toString();
                    String thisStage = sessionVariableCalled("stage").toString();
                    giveMeACase(thisCaseType, thisStage);
                }
                try {
                    getFirstUnallocatedMINCaseMarkupCase();
                    try {
                        assertThat($("//span[text()='What sort of response is required?']").getText(), is("What sort of "
                                + "response is required?"));
                        System.out.println("Markup 'What sort of response is required?' question found therefore, "
                                + "continuing test");
                    } catch (org.openqa.selenium.NoSuchElementException e) {
                        System.out.println("Markup 'What sort of response is required?' question not found therefore, "
                                + "searching for a fresh Markup "
                                + "case");
                        page.clickOn(homepage.home);
                        getFirstUnallocatedMarkupCase(caseType);
                    }
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println("I couldn't find a Markup case so I am searching for a Data Input case");
                    page.clickOn(homepage.home);
                    getFirstUnallocatedDataInputCase(caseType);
                    dataInput.moveCaseFromDataInputToMarkup();
                    getFirstUnallocatedMarkupCase(caseType);
                }
                break;
            case "DCU TRO":
                break;
            case "DCU N10":
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }


    public void getFirstUnallocatedInitialDraftCase(String caseType) {
        switch (caseType.toUpperCase()) {
            case "DCU MIN":
                try {
                    page.clickOn(homepage.animalsInScienceTeam);
                } catch (ElementShouldBeEnabledException e) {
                    System.out.println("Animals in Science Regulation unit is not available, therefore there are no draft "
                            + "cases, searching for a Markup case");
                    page.clickOn(homepage.home);
                    getFirstUnallocatedMarkupCase(caseType);
                    markupFullFlow.moveCaseFromMarkupToInitialDraft();
                    String thisCaseType = sessionVariableCalled("caseType").toString();
                    String thisStage = sessionVariableCalled("stage").toString();
                    giveMeACase(thisCaseType, thisStage);
                }
                try {
                    getFirstUnallocatedMINInitialDraftCase();
                    try {
                        assertThat($("//span[text()='Can this correspondence be answered by your team?']").getText(),
                                is("Can this "
                                        + "correspondence be answered by your team?"));
                        System.out.println("Initial Draft 'Can this correspondence be answered by your team?' question "
                                + "found therefore, continuing test");
                    } catch (ElementShouldBeEnabledException | NoSuchElementException e) {
                        System.out.println("Initial Draft 'Can this correspondence be answered by your team?' question not "
                                + "found therefore, searching "
                                + "for a fresh "
                                + "Initial Draft case");
                        page.clickOn(homepage.home);
                        getFirstUnallocatedInitialDraftCase(caseType);
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("I couldn't find an Initial Draft case so I am searching for a Markup case");
                    page.clickOn(homepage.home);
                    getFirstUnallocatedMarkupCase(caseType);
                    markupFullFlow.moveCaseFromMarkupToInitialDraft();
                    getFirstUnallocatedInitialDraftCase(caseType);
                }
                break;
            case "DCU TRO":
                break;
            case "DCU N10":
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    public void getFirstUnallocatedQaResponseCase(String caseType) {
        switch (caseType.toUpperCase()) {
            case "DCU MIN":
                try {
                    page.clickOn(homepage.animalsInScienceTeam);
                } catch (ElementShouldBeEnabledException e) {
                    System.out.println("Animals in Science Regulation unit is not available, therefore there are no QA "
                            + "cases, searching for a Draft case");
                    page.clickOn(homepage.home);
                    getFirstUnallocatedInitialDraftCase(caseType);
                    draftingTeamDecision.moveCaseFromInitialDraftToQaResponse();
                    String thisCaseType = sessionVariableCalled("caseType").toString();
                    String thisStage = sessionVariableCalled("stage").toString();
                    giveMeACase(thisCaseType, thisStage);
                }
                try {
                    getFirstUnallocatedMINQAResponseCase();
                    try {
                        assertThat($("//span[text()='Do you approve the response?']").getText(), is("Do you approve the "
                                + "response?"));
                        System.out.println("QA Response 'Do you approve the response?' question found therefore, "
                                + "continuing "
                                + "test");
                    } catch (ElementShouldBeEnabledException | NoSuchElementException e) {
                        System.out.println("QA Response 'Do you approve the response?' question not found therefore, "
                                + "searching for a fresh QA Response case");
                        page.clickOn(homepage.home);
                        page.clickOn(homepage.animalsInScienceTeam);
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("I couldn't find a QA Response case so I am searching for a Draft case");
                    page.clickOn(homepage.home);
                    getFirstUnallocatedInitialDraftCase(caseType);
                    draftingTeamDecision.moveCaseFromInitialDraftToQaResponse();
                    getFirstUnallocatedQaResponseCase(caseType);
                }
                break;
            case "DCU TRO":
                break;
            case "DCU N10":
                break;
            default:
                pendingStep(caseType + " is not defined withing " + getMethodName());
        }

    }

    public void getFirstUnallocatedPrivateOfficeCase(String caseType) {
        switch (caseType.toUpperCase()) {
            case "DCU MIN":
                try {
                    page.clickOn(homepage.ministerForLordsTeam);
                } catch (ElementShouldBeEnabledException e) {
                    System.out.println("Minister for Lords team not available, therefore there are no Private Office "
                            + "Approval cases, "
                            + "searching for a QA Response case");
                    page.clickOn(homepage.home);
                    getFirstUnallocatedQaResponseCase(caseType);
                    qa.moveCaseFromQaResponseToPrivateOfficeApproval();
                    String thisCaseType = sessionVariableCalled("caseType").toString();
                    String thisStage = sessionVariableCalled("stage").toString();
                    giveMeACase(thisCaseType, thisStage);
                }
                try {
                    getFirstUnallocatedMINPrivateOfficeCase();
                    try {
                        page.clickOn(workstacks.caseSummaryTab);
                        assertThat($("//caption[text()='Private Office Approval']").getText(),
                                is("Private Office Approval"));
                        System.out.println("Private Office Approval is active stage");
                        assertThat($("//span[text()='Do you approve the response?']").getText(),
                                is("Do you approve the response?"));
                        System.out.println("Private Office 'Do you approve the response?' question found therefore, "
                                + "continuing test");
                    } catch (ElementShouldBeEnabledException | NoSuchElementException e) {
                        System.out.println("Elements not found therefore, searching for a fresh Private Office case");
                        page.clickOn(homepage.home);
                        getFirstUnallocatedPrivateOfficeCase(caseType);
                    }
                } catch (IndexOutOfBoundsException e) {
                    page.clickOn(homepage.home);
                    System.out.println("I couldn't find a Private Office Approval case so I am searching for a QA "
                            + "Response case");
                    getFirstUnallocatedQaResponseCase(caseType);
                    qa.moveCaseFromQaResponseToPrivateOfficeApproval();
                    getFirstUnallocatedPrivateOfficeCase(caseType);
                }
                break;
            case "DCU TRO":
                break;
            case "DCU N10":
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    public void getFirstUnallocatedMinisterialSignOffCase(String caseType) {
        switch (caseType.toUpperCase()) {
            case "DCU MIN":
                try {
                    page.clickOn(homepage.ministerForLordsTeam);
                } catch (ElementShouldBeEnabledException e) {
                    System.out.println("Minister for Lords team not available, therefore there are no Minister Sign Off "
                            + "cases, "
                            + "searching for a Private Office Approval Case");
                    page.clickOn(homepage.home);
                    getFirstUnallocatedPrivateOfficeCase(caseType);
                    privateOffice.moveCaseFromPrivateOfficeToMinisterSignOff();
                    String thisCaseType = sessionVariableCalled("caseType").toString();
                    String thisStage = sessionVariableCalled("stage").toString();
                    giveMeACase(thisCaseType, thisStage);
                }
                try {
                    getFirstUnallocatedMINMinisterialSignOffCase();
                    try {
                        page.clickOn(workstacks.caseSummaryTab);
                        assertThat($("//caption[text()='Ministerial Sign off']").getText(), is("Ministerial Sign off"));
                        System.out.println("Ministerial Sign Off is active stage");
                        assertThat($("//span[text()='Do you approve the response?']").getText(),
                                is("Do you approve the response?"));
                        System.out.println("Ministerial Sign Off 'Do you approve the response?' question found therefore, "
                                + "continuing "
                                + "test");
                    } catch (ElementShouldBeEnabledException | NoSuchElementException e) {
                        System.out.println(
                                "Elements not found therefore, searching for a fresh Ministerial Sign Off case");
                        page.clickOn(homepage.home);
                        getFirstUnallocatedMinisterialSignOffCase(caseType);
                    }
                } catch (IndexOutOfBoundsException e) {
                    page.clickOn(homepage.home);
                    System.out.println("I couldn't find a Ministerial Sign off case so I am searching for a Private "
                            + "Office Approval case");
                    getFirstUnallocatedPrivateOfficeCase(caseType);
                    privateOffice.moveCaseFromPrivateOfficeToMinisterSignOff();
                    getFirstUnallocatedMinisterialSignOffCase(caseType);
                }
                break;
            case "DCU TRO":
                break;
            case "DCU N10":
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }

    public void getFirstUnallocatedDispatchCase(String caseType) {
        switch (caseType.toUpperCase()) {
            case "DCU MIN":
                try {
                    page.clickOn(homepage.performanceProcessTeam);
                } catch (ElementShouldBeEnabledException e) {
                    System.out.println("Performance Process Team not available, therefore there are no Dispatch cases, "
                            + "searching for a Ministerial Sign off Case");
                    page.clickOn(homepage.home);
                    getFirstUnallocatedMinisterialSignOffCase(caseType);
                    minister.moveCaseFromMinisterToDispatch();
                    String thisCaseType = sessionVariableCalled("caseType").toString();
                    String thisStage = sessionVariableCalled("stage").toString();
                    giveMeACase(thisCaseType, thisStage);
                }
                try {
                    getFirstUnallocatedMINDispatchCase();
                    try {
                        page.clickOn(workstacks.caseSummaryTab);
                        assertThat($("//caption[text()='Dispatch']").getText(), is("Dispatch"));
                        System.out.println("Dispatch is active stage");
                        assertThat($("//label[text()='How do you intend to respond?']").getText(),
                                is("How do you intend to respond?"));
                        System.out.println("Dispatch 'How do you intend to respond?' question found therefore, continuing "
                                + "test");
                    } catch (ElementShouldBeEnabledException | NoSuchElementException e) {
                        System.out.println(
                                "Elements not found therefore, searching for a fresh Dispatch case");
                        page.clickOn(homepage.home);
                        getFirstUnallocatedDispatchCase(caseType);
                    }
                } catch (IndexOutOfBoundsException e) {
                    page.clickOn(homepage.home);
                    System.out.println("I couldn't find a Dispatch case so I am searching for a Ministerial Case");
                    getFirstUnallocatedMinisterialSignOffCase(caseType);
                    minister.moveCaseFromMinisterToDispatch();
                    getFirstUnallocatedDispatchCase(caseType);
                }
                break;
            case "DCU TRO":
                break;
            case "DCU N10":
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
    }


}
