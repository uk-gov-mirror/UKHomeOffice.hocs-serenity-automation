package com.hocs.test.pages;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.setSessionVariable;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.hocs.test.pages.dcu.AccordionDCU;
import com.hocs.test.pages.dcu.Markup;
import com.hocs.test.pages.mpam.AccordionMPAM;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;

public class Search extends BasePage {

    Workstacks workstacks;

    PeopleTab peopleTab;

    SummaryTab summaryTab;

    Markup markup;

    Dashboard dashboard;

    AccordionDCU accordionDCU;

    AccordionMPAM accordionMPAM;

    UnallocatedCaseView unallocatedCaseView;

    AddCorrespondent addCorrespondent;

    @FindBy(css = "label[for='caseTypes_MIN']")
    public WebElementFacade searchMINCheckbox;

    @FindBy(css = "label[for='caseTypes_DTEN']")
    public WebElementFacade searchDTENCheckbox;

    @FindBy(css = "label[for='caseTypes_TRO']")
    public WebElementFacade searchTROCheckbox;

    @FindBy(css = "label[for='caseTypes_MPAM']")
    public WebElementFacade searchMPAMCheckbox;

    @FindBy(css = "label[for='caseTypes_MTS']")
    public WebElementFacade searchMTSCheckbox;

    @FindBy(xpath = "//input[@id='dateReceivedFrom-day']")
    public WebElementFacade receivedAfterDayTextbox;

    @FindBy(xpath = "//input[@id='dateReceivedFrom-month']")
    public WebElementFacade receivedAfterMonthTextbox;

    @FindBy(xpath = "//input[@id='dateReceivedFrom-year']")
    public WebElementFacade receivedAfterYearTextbox;

    @FindBy(xpath = "//input[@id='dateReceivedTo-day']")
    public WebElementFacade receivedBeforeDayTextbox;

    @FindBy(xpath = "//input[@id='dateReceivedTo-month']")
    public WebElementFacade receivedBeforeMonthTextbox;

    @FindBy(xpath = "//input[@id='dateReceivedTo-year']")
    public WebElementFacade receivedBeforeYearTextbox;

    @FindBy(xpath = "//input[@id='correspondent']")
    public WebElementFacade searchCorrespondentTextbox;

    @FindBy(xpath = "//input[@id='topic']")
    public WebElementFacade searchTopicTextbox;

    @FindBy(xpath = "//select[@id='signOffMinister']")
    public WebElementFacade searchSignOffTeamDropdown;

    @FindBy(xpath = "//label[@for='caseStatus_active']")
    public WebElementFacade caseStatusActiveCheckbox;

    @FindBy(xpath = "//a[text()='No search criteria specified']")
    public WebElementFacade noSearchCriteriaErrorMessage;

    @FindBy(css = "div span[class='govuk-hint']")
    public WebElementFacade numberOfSearchResults;

    @FindBy(id = "reference")
    public WebElementFacade caseReferenceSearchBox;

    @FindBy(id = "RefType")
    public WebElementFacade mpamRefTypeDropdown;

    @FindBy(xpath = "//input[@id='react-select-3-input']")
    public WebElementFacade memberOfParliamentSearchBox;

    @FindBy(id = "correspondentReference")
    public WebElementFacade correspondentReferenceNumber;

    @FindBy(xpath = "//label[text()='MPAM Case']")
    public WebElementFacade mpamCaseCheckbox;

    @FindBy(xpath = "//label[text()='MTS Case']")
    public WebElementFacade mtsCaseCheckbox;

    @FindBy(xpath = "//label[text()='Include Home Secretary Interest Cases only']")
    public WebElementFacade includeHomeSecInterestCasesOnlyCheckbox;

    @FindBy(xpath = "//div[@id='CampaignType']//input")
    public WebElementFacade campaignSearchField;

    @FindBy(id = "MinSignOffTeam")
    public WebElementFacade ministerialSignOffDropdown;

    @FindBy(id = "correspondentNameNotMember")
    public WebElementFacade applicantOrConstituentFullNameTextField;

    @FindBy(id = "OfficialEngagement")
    public WebElementFacade telephoneSurgeryOfficialEngagementDropdown;

    //Enter search criteria

    public void enterDCUSearchCriteria(String criteria, String value) {
        switch (criteria.toUpperCase()) {
            case "CASE TYPE":
                switch (value.toUpperCase()) {
                    case "MIN":
                        safeClickOn(searchMINCheckbox);
                        break;
                    case "DTEN":
                        safeClickOn(searchDTENCheckbox);
                        break;
                    case "TRO":
                        safeClickOn(searchTROCheckbox);
                        break;
                    default:
                        pendingStep(value + " is not defined within " + getMethodName());
                }
                setSessionVariable("searchCaseType").to(value);
                break;
            case "RECEIVED ON OR AFTER DATE":
                typeIntoDateField(receivedAfterDayTextbox, receivedAfterMonthTextbox, receivedAfterYearTextbox, value);
                setSessionVariable("searchReceivedOnOrAfterDate").to(value);
                break;
            case "RECEIVED ON OR BEFORE DATE":
                typeIntoDateField(receivedBeforeDayTextbox, receivedBeforeMonthTextbox, receivedBeforeYearTextbox, value);
                setSessionVariable("searchReceivedOnOrBeforeDate").to(value);
                break;
            case "MEMBER OF PARLIAMENT NAME":
                safeClickOn(memberOfParliamentSearchBox);
                memberOfParliamentSearchBox.sendKeys(value);
                waitABit(5000);
                memberOfParliamentSearchBox.sendKeys(Keys.ENTER);
                setSessionVariable("searchMemberOfParliamentName").to(value);
                break;
            case "PUBLIC CORRESPONDENT NAME":
                typeInto(applicantOrConstituentFullNameTextField, value);
                setSessionVariable("searchCorrespondentName").to(value);
                break;
            case "TOPIC":
                safeClickOn(searchTopicTextbox);
                typeInto(searchTopicTextbox, value);
                setSessionVariable("searchTopic").to(value);
                break;
            case "SIGN OFF TEAM":
                searchSignOffTeamDropdown.selectByVisibleText(value);
                setSessionVariable("searchSignOffTeam").to(value);
                break;
            case "ACTIVE CASES ONLY":
                if (value.toUpperCase().equals("YES")) {
                    safeClickOn(caseStatusActiveCheckbox);
                }
                setSessionVariable("searchActiveCases").to(value);
                break;
            case "HOME SECRETARY INTEREST":
                if (value.toUpperCase().equals("YES")) {
                    safeClickOn(includeHomeSecInterestCasesOnlyCheckbox);
                }
                break;
            default:
                pendingStep(criteria + " is not defined within " + getMethodName());
        }
    }

    public void enterMPAMSearchCriteria(String criteria, String value) {
        switch (criteria.toUpperCase()) {
            case "CASE REFERENCE":
                typeInto(caseReferenceSearchBox, value);
                setSessionVariable("searchCaseReference").to(value);
                break;
            case "REFERENCE TYPE":
                switch (value.toUpperCase()) {
                    case "MINISTERIAL":
                        mpamRefTypeDropdown.selectByIndex(1);
                        break;
                    case "OFFICIAL":
                        mpamRefTypeDropdown.selectByIndex(2);
                        break;
                    default:
                        pendingStep(value + " is not defined within " + getMethodName());
                }
                setSessionVariable("searchReferenceType").to(value);
                break;
            case "MINISTERIAL SIGN OFF TEAM":
                ministerialSignOffDropdown.selectByVisibleText(value);
                setSessionVariable("searchMinisterialSignOffTeam").to(value);
                break;
            case "MEMBER OF PARLIAMENT NAME":
                safeClickOn(memberOfParliamentSearchBox);
                memberOfParliamentSearchBox.sendKeys(value);
                waitABit(5000);
                memberOfParliamentSearchBox.sendKeys(Keys.ENTER);
                setSessionVariable("searchMemberOfParliamentName").to(value);
                break;
            case "CORRESPONDENT REFERENCE NUMBER":
                typeInto(correspondentReferenceNumber, value);
                setSessionVariable("searchCorrespondentReferenceNumber").to(value);
                break;
            case "RECEIVED ON OR BEFORE DATE":
                typeIntoDateField(receivedBeforeDayTextbox, receivedBeforeMonthTextbox, receivedBeforeYearTextbox, value);
                setSessionVariable("searchReceivedOnOrBeforeDate").to(value);
                break;
            case "RECEIVED ON OR AFTER DATE":
                typeIntoDateField(receivedAfterDayTextbox, receivedAfterMonthTextbox, receivedAfterYearTextbox, value);
                setSessionVariable("searchReceivedOnOrAfterDate").to(value);
                break;
            case "CAMPAIGN":
                campaignSearchField.sendKeys(value);
                campaignSearchField.sendKeys(Keys.RETURN);
                setSessionVariable("searchCampaign").to(value);
                break;
            case "PUBLIC CORRESPONDENT NAME":
                typeInto(applicantOrConstituentFullNameTextField, value);
                setSessionVariable("searchCorrespondentName").to(value);
                break;
            case "ACTIVE CASES ONLY":
                if (value.toUpperCase().equals("YES")) {
                    safeClickOn(caseStatusActiveCheckbox);
                }
                setSessionVariable("searchActiveCases").to(value);
                break;
            case "TELEPHONE SURGERY OFFICIAL ENGAGEMENT":
                telephoneSurgeryOfficialEngagementDropdown.selectByVisibleText(value);
                setSessionVariable("searchTelephoneSurgeryOfficialEngagement").to(value);
                break;
            default:
                pendingStep(criteria + " is not defined within " + getMethodName());
        }
    }

    public void searchBySubstringOfCaseReference() {
        int n = 0;
        String substring = null;
        String firstCharOfSubstring = "";
        String caseRef = sessionVariableCalled("caseReference");
        String split = caseRef.split("/01")[1];
        int randomStringLength = (int)(Math.random() * ((5 - 1) + 1)) + 1;
        while (n <= randomStringLength) {
            substring = split.substring(n, randomStringLength);
            firstCharOfSubstring = String.valueOf(substring.charAt(0));
            if (!firstCharOfSubstring.equals("0")) {
                break;
            }
            n++;
        }
        int caseNumberUpperBound = Integer.parseInt(substring);
        int randomCaseInteger = new Random().nextInt(caseNumberUpperBound);
        String randomCaseIntToString = Integer.toString(randomCaseInteger);
        if (randomCaseInteger < 1000) {
            randomCaseIntToString = "0" + randomCaseIntToString;
        }
        if (randomCaseInteger < 100) {
            randomCaseIntToString = "0" + randomCaseIntToString;
        }
        if (randomCaseInteger < 10) {
            randomCaseIntToString = "0" + randomCaseIntToString;
        }
        String randomCaseRefString = "/012" + randomCaseIntToString;
        setSessionVariable("caseReferenceSubstring").to(randomCaseRefString);
        typeInto(caseReferenceSearchBox, randomCaseRefString);
        safeClickOn(searchButton);
    }

    //Assertions

    public void assertCurrentCaseIsDisplayed() {
        int n = 0;
        List<WebElementFacade> listOfCaseRefs = findAll("//tr[@class='govuk-radios govuk-table__row']//a");
        while (n < listOfCaseRefs.size()) {
            String caseRef = listOfCaseRefs.get(n).getText();
            if (caseRef.contains(sessionVariableCalled("caseReference"))) {
                listOfCaseRefs.get(n).shouldContainText(sessionVariableCalled("caseReference"));
                break;
            }
            n++;
        }
    }

    public void assertNoSearchCriteriaErrorMessage() {
        noSearchCriteriaErrorMessage.shouldContainText("No search criteria specified");
    }

    public void assertAllDisplayedCaseRefsContainSubstring() {
        List<WebElementFacade> listOfCaseRefs = findAll("//tr/td[1]");
        String substringInput = sessionVariableCalled("caseReferenceSubstring");
        for (WebElementFacade caseRef : listOfCaseRefs) {
            caseRef.shouldContainText(substringInput);
        }
    }

    public void assertDCUInformationRandomSearchResult(String criteria) {
        Date searchDate = null;
        Date caseDate = null;
        boolean trueFalse;
        numberOfSearchResults.waitUntilVisible();
        int numberOfCasesDisplayed = Integer.parseInt(numberOfSearchResults.getText().split("\\s+")[0]);
        int randomNumber = new Random().nextInt(numberOfCasesDisplayed);
        WebElementFacade randomSearchResult = findBy("//tr[" + randomNumber + "]/td/a");
        setSessionVariable("randomCaseRef").to(randomSearchResult.getText());
        switch (criteria.toUpperCase()) {
            case "CASE TYPE":
                List<WebElementFacade> searchResultCaseRefs = findAll("//tr/td/a");
                assertThat(searchResultCaseRefs.size() == numberOfCasesDisplayed, is(true));
                break;
            case "RECEIVED ON OR AFTER DATE":
                safeClickOn(randomSearchResult);
                try {
                    searchDate = new SimpleDateFormat("dd/MM/yyyy").parse(sessionVariableCalled("searchReceivedOnOrAfterDate"));
                    caseDate = new SimpleDateFormat("dd/MM/yyyy").parse(workstacks.getCorrespondenceReceivedDateFromSummary());
                } catch (ParseException pE) {
                    System.out.println("Could not parse dates");
                }
                assert caseDate != null;
                trueFalse = (caseDate.after(searchDate) || caseDate.equals(searchDate));
                assertThat(trueFalse, is(true));
                break;
            case "RECEIVED ON OR BEFORE DATE":
                safeClickOn(randomSearchResult);
                searchDate = null;
                caseDate = null;
                try {
                    searchDate = new SimpleDateFormat("dd/MM/yyyy").parse(sessionVariableCalled("searchReceivedOnOrBeforeDate"));
                    caseDate = new SimpleDateFormat("dd/MM/yyyy").parse(workstacks.getCorrespondenceReceivedDateFromSummary());
                } catch (ParseException pE) {
                    System.out.println("Could not parse dates");
                }
                assert caseDate != null;
                trueFalse = (caseDate.before(searchDate) || caseDate.equals(searchDate));
                assertThat(trueFalse, is(true));
                break;
            case "MEMBER OF PARLIAMENT NAME":
                safeClickOn(randomSearchResult);
                safeClickOn(peopleTab.peopleTab);
                peopleTab.assertMPCorrespondentIsAddedToTheCase(sessionVariableCalled("searchMemberOfParliamentName"));
                break;
            case "PUBLIC CORRESPONDENT NAME":
                safeClickOn(randomSearchResult);
                safeClickOn(peopleTab.peopleTab);
                peopleTab.assertPublicCorrespondentAddedToTheCase(sessionVariableCalled("searchCorrespondentName"));
                break;
            case "CORRESPONDENT REFERENCE NUMBER":
                safeClickOn(randomSearchResult);
                peopleTab.selectPeopleTab();
                WebElementFacade correspondentRefNumber = findBy("//th[text()='Reference']/following-sibling::td");
                correspondentRefNumber.shouldContainText(sessionVariableCalled("searchCorrespondentReferenceNumber"));
                break;
            case "TOPIC":
                safeClickOn(randomSearchResult);
                summaryTab.selectSummaryTab();
                summaryTab.primaryTopic.shouldContainText(sessionVariableCalled("searchTopic"));
                break;
            case "SIGN OFF TEAM":
                String signOffTeam;
                safeClickOn(randomSearchResult);
                summaryTab.selectSummaryTab();
                if (summaryTab.overridePrivateOfficeTeam.isVisible()) {
                    signOffTeam = summaryTab.overridePrivateOfficeTeam.getText().toUpperCase();
                } else {
                    signOffTeam = summaryTab.privateOfficeTeam.getText().toUpperCase();
                }
                assertThat(signOffTeam.equals(sessionVariableCalled("searchSignOffTeam").toString().toUpperCase()),
                        is(true));
                break;
            case "ACTIVE CASES ONLY":
                List activeCases = findAll("//td[2][not(text() = 'Closed')]");
                if (sessionVariableCalled("searchActiveCases").toString().toUpperCase().equals("YES")) {
                    assertThat(!activeCases.isEmpty(), is(true));
                }
                break;
            case "HOME SECRETARY INTEREST":
                safeClickOn(randomSearchResult);
                summaryTab.selectSummaryTab();
                assertThat(summaryTab.homeSecInterest.getText().equals("Yes"), is(true));
                break;
            default:
                pendingStep(criteria + " is not defined within " + getMethodName());
        }
    }

    public void assertMPAMInformationRandomSearchResult(String criteria) {
        numberOfSearchResults.waitUntilVisible();
        int numberOfCasesDisplayed = Integer.parseInt(numberOfSearchResults.getText().split("\\s+")[0]);
        int randomNumber = (new Random().nextInt(numberOfCasesDisplayed)) + 1;
        WebElementFacade randomSearchResult = findBy("//tr[" + randomNumber + "]/td/a");
        switch (criteria.toUpperCase()) {
            case "CASE REFERENCE":
                String caseRef;
                safeClickOn(randomSearchResult);
                if (unallocatedCaseView.allocateToMeLink.isVisible()) {
                    caseRef = caseReference.getText();
                } else {
                    caseRef = allocatedCaseReference.getText();
                }
                assertThat(caseRef.equals("searchCaseReference"), is(true));
                break;
            case "REFERENCE TYPE":
                safeClickOn(randomSearchResult);
                safeClickOn(summaryTab.summaryTab);
                summaryTab.isMinisterialResponseRequired.shouldContainText(sessionVariableCalled("searchReferenceType"));
                break;
            case "MINISTERIAL SIGN OFF TEAM":
                safeClickOn(randomSearchResult);
                String ministerialSignOffTeam = sessionVariableCalled("searchMinisterialSignOffTeam");
                if (unallocatedCaseView.allocateToMeLink.isVisible()) {
                    accordionMPAM.openCreationAccordion();
                    accordionMPAM.getQuestionResponse("Ministerial Sign Off Team");
                    String displayedResponse = sessionVariableCalled("response");
                    assertThat(displayedResponse.contains(ministerialSignOffTeam), is(true));
                } else if (addCorrespondent.addACorrespondentLink.isVisible()) {
                    clickBackButton();
                    String chosenSelection = new Select(getDriver().findElement(By.xpath("//select"))).getFirstSelectedOption().getText();
                    assertThat(chosenSelection.contains(ministerialSignOffTeam), is(true));
                } else {
                    accordionMPAM.openCaseDetailsAccordion();
                    String chosenSelection = new Select(getDriver().findElement(By.xpath("//select"))).getFirstSelectedOption().getText();
                    assertThat(chosenSelection.contains(ministerialSignOffTeam), is(true));
                }
                break;
            case "MEMBER OF PARLIAMENT NAME":
                safeClickOn(randomSearchResult);
                safeClickOn(peopleTab.peopleTab);
                peopleTab.assertMPCorrespondentIsAddedToTheCase(sessionVariableCalled("searchMemberOfParliamentName"));
                break;
            case "CORRESPONDENT REFERENCE NUMBER":
                safeClickOn(randomSearchResult);
                peopleTab.selectPeopleTab();
                WebElementFacade correspondentRefNumber = findBy("//th[text()='Reference']/following-sibling::td");
                correspondentRefNumber.shouldContainText(sessionVariableCalled("searchCorrespondentReferenceNumber"));
                break;
            case "RECEIVED ON OR BEFORE DATE":
            case "RECEIVED ON OR AFTER DATE":
                assertDCUInformationRandomSearchResult(criteria);
                break;
            case "CAMPAIGN":
                safeClickOn(randomSearchResult);
                safeClickOn(summaryTab.summaryTab);
                summaryTab.assertCampaignInSummaryTabIsCorrect(sessionVariableCalled("searchCampaign"));
                break;
            case "PUBLIC CORRESPONDENT NAME":
                safeClickOn(randomSearchResult);
                safeClickOn(peopleTab.peopleTab);
                peopleTab.assertPublicCorrespondentAddedToTheCase(sessionVariableCalled("searchCorrespondentName"));
                break;
            case "ACTIVE CASES ONLY":
                List activeCases = findAll("//td[2][not(text() = 'Closed')]");
                if (sessionVariableCalled("searchActiveCases").toString().toUpperCase().equals("YES")) {
                    assertThat(!activeCases.isEmpty(), is(true));
                }
                break;
            case "TELEPHONE SURGERY OFFICIAL ENGAGEMENT":
                safeClickOn(randomSearchResult);
                safeClickOn(summaryTab.summaryTab);
                summaryTab.telephoneSurgeryOfficialEngagement.shouldContainText(sessionVariableCalled("searchTelephoneSurgeryOfficialEngagement"));
                break;
            default:
                pendingStep(criteria + " is not defined within " + getMethodName());
        }
    }
}
