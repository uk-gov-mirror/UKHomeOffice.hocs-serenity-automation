package com.hocs.test.glue;

import static org.junit.Assert.fail;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.create_case.AddDocuments;
import com.hocs.test.pages.create_case.SingleCase;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class CreateCaseStepDefs {

    private AddDocuments addDocuments;

    private Page page;

    private SingleCase singleCase;

    @Given("^I am presented with \"([^\"]*)\"")
    public void iAmPresentedWith(String userView) {
        switch (userView.toUpperCase()) {
            case "NO CASE TYPES":
                singleCase.radioButtonsNotDisplayed();
                break;
            default:
                fail(userView + " is not defined with CreateCaseStepDefs.iAmPresentedWith");
        }
    }

    @When("^I create a case$")
    public void iCreateACase() {
        singleCase.clickDcuMinRadioButton();
        singleCase.clickNextButton();
        addDocuments.uploadDocument();
        page.clickSubmitButton();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
