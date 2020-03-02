package com.hocs.test.glue;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.base_page.Page;
import com.hocs.test.pages.documents.Documents;
import cucumber.api.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class DocumentsStepDefs extends Page {

    Documents documents;

    GenericInputStepDefs genericInputStepDefs;

    @And("I upload a \"([^\"]*)\" document")
    public void IUploadADocument(String docType) {
        switch (docType.toUpperCase()) {
            case "ORIGINAL":
                documents.addAOriginalDocument();
                break;
            case "DRAFT":
                documents.addADraftDocumentAtDraftStage();
                break;
            case "FINAL":
                documents.addAFinalDocument();
                break;
            default:
                pendingStep(docType + " is not defined within " + getMethodName());
        }
        continueButton.withTimeoutOf(Duration.ofSeconds(30)).waitUntilVisible().click();
    }

    @And("^I click manage documents$")
    public void iClickManageDocuments() {
        clickOn(documents.manageDocumentsLink);
    }

    @When("^I click add documents$")
    public void iClickAddDocuments() {
        clickOn(documents.addDocumentLink);
    }

    @And("^I choose the document type \"([^\"]*)\"$")
    public void iChooseTheDocumentType(String docType) {
        switch (docType.toUpperCase()) {
            case "ORIGINAL":
                documents.selectDocumentTypeByIndex(1);
                break;
            case "DRAFT":
                documents.selectDocumentTypeByIndex(2);
                break;
            case "FINAL":
                documents.selectDocumentTypeByIndex(3);
                break;
            default:
                pendingStep(docType + " is not defined within " + getMethodName());
        }
    }

    @And("I upload a file of type {string}")
    public void iUploadAFileOfType(String fileType) {
        documents.uploadDocumentOfType(fileType);
        clickAddButton();
    }

    @Then("^I can see the \"([^\"]*)\" file in the uploaded document list$")
    public void iCanSeeTheFileInTheUploadedDocumentList(String fileType) {
        documents.waitForFileToUpload();
        documents.assertFileIsVisible(fileType);
    }

    @Then("^the document should have the \"([^\"]*)\" tag$")
    public void theDocumentShouldHaveTheTag(String Tag) {
        documents.assertDocumentHasTag(Tag.toUpperCase());
    }

    @Then("^an error message should be displayed as I have not selected a document type$")
    public void anErrorMessageShouldBeDisplayedAsIHaveNotSelectedADocumentType() {
        documents.assertDocumentTypeIsRequiredErrorMessage();
    }

    @Then("^an error message should be displayed as I have not selected a file to upload$")
    public void anErrorMessageShouldBeDisplayedAsIHaveNotSelectedAFileToUpload() {
        documents.assertDocumentIsRequiredErrorMessage();
    }

    @Then("^an error message should be displayed as I have selected a file type which is not allowed$")
    public void anErrorMessageShouldBeDisplayedAsIHaveSelectedAFileTypeWhichIsNotAllowed() {
        documents.assertFileTypeIsNotAllowedErrorMessage();
    }

    @And("^I cannot see the \"([^\"]*)\" file in the uploaded document list$")
    public void iCannotSeeTheFileInTheUploadedDocumentList(String fileIdentifier) {
        documents.assertFileIsNotVisible(fileIdentifier);
    }

    @And("^I select a file that is (\\d+)MB in size$")
    public void iSelectAFileThatIsLargerThanMB(int fileSize) {
        switch (fileSize)  {
            case 51:
                documents.upload51MBDocument();
                break;
            case 5:
                documents.upload5MBDocument();
                break;
            default:
                pendingStep(fileSize + " is not defined within " + getMethodName());
        }
        genericInputStepDefs.clickTheButton("add");
    }

    @Then("^an error message should be displayed as I have selected a file which is larger than the allowed limit$")
    public void anErrorMessageShouldBeDisplayedAsIHaveSelectedAFileWhichIsLargerThanTheAllowedLimit() {
        documents.assertFileTooLargeErrorMessage();
    }

    @And("^I upload a docx and a txt file$")
    public void iUploadADocxAndATxtFile() {
        iClickAddDocuments();
        iChooseTheDocumentType("Draft");
        documents.uploadDocumentOfType("docx");
        genericInputStepDefs.clickTheButton("add");
        documents.waitForFileToUpload();
        iClickManageDocuments();
        iClickAddDocuments();
        iChooseTheDocumentType("Draft");
        documents.uploadDocumentOfType("txt");
        genericInputStepDefs.clickTheButton("add");
    }

    @Then("^the \"([^\"]*)\" document should be select to be displayed in the preview pane$")
    public void theFileShouldBeDisplayedInThePreviewPane(String fileIdentifier) {
        documents.assertDocumentIsDisplayedInPreviewPane(fileIdentifier);
    }

    @And("^I click the preview button of the \"([^\"]*)\" file$")
    public void iClickThePreviewButtonOfTheFile(String fileIdentifier) {
        documents.clickPreviewButtonForFile(fileIdentifier);
    }

    @And("^I add a \"([^\"]*)\" document to the case$")
    public void iAddADocumentToTheCase(String fileIdentifier) {
        iClickAddDocuments();
        iChooseTheDocumentType("Draft");
        iUploadAFileOfType(fileIdentifier);
        iCanSeeTheFileInTheUploadedDocumentList(fileIdentifier);
    }

    @And("^I select to remove the \"([^\"]*)\" document$")
    public void iClickTheRemoveLinkForTheFile(String fileIdentifier) {
        documents.clickRemoveLinkForFile(fileIdentifier);
        documents.clickRemoveButton();
    }

    @Then("^the document should have the Pending tag$")
    public void theDocumentShouldHaveThePendingTag() {
        documents.assertPendingTagVisible();
    }

    @Then("^the document should be under the \"([^\"]*)\" header$")
    public void theDocumentShouldBeUnderTheHeader(String header) {
        documents.waitForFileToUpload();
        documents.assertDocumentIsUnderHeader(header.toUpperCase());
    }
}
