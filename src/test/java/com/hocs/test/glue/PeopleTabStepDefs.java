package com.hocs.test.glue;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.BasePage;
import com.hocs.test.pages.PeopleTab;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class PeopleTabStepDefs extends BasePage {

    PeopleTab peopleTab;

    @And("I add a {string} correspondent to the case")
    public void iAddANewCorrespondent(String correspondentType) {
        safeClickOn(peopleTab.peopleTab);
        switch (correspondentType.toUpperCase()) {
            case "MEMBER":
                peopleTab.addAMemberCorrespondent("Boris Johnson");
                break;
            case "PUBLIC":
                peopleTab.addAPublicCorrespondent();
                break;
            default:
                pendingStep(correspondentType + " is not defined within " + getMethodName());
        }
    }

    @And("I edit the {string} detail for {string}")
    public void iEditCorrespondentDetails(String detail, String correspondent) {
        peopleTab.editCorrespondent(detail, correspondent);
    }

    @And("I remove {string} as a correspondent of the case")
    public void iRemoveACorrespondentFromTheCase(String correspondent) {
        safeClickOn(peopleTab.peopleTab);
        peopleTab.removeCorrespondent(correspondent);
    }

    @And("I change the primary correspondent of the case to {string}")
    public void iChangePrimaryCorrespondent(String correspondent) {
        safeClickOn(peopleTab.peopleTab);
        peopleTab.changePrimaryCorrespondent(correspondent);
    }

    @Then("the new correspondent is added to the case")
    public void assertNewCorrespondentIsAddedToTheCase() {
        peopleTab.assertNewCorrespondentIsDisplayed();
    }

    @Then("the primary correspondent should be {string}")
    public void assertPrimaryCorrespondentHasChangedTo(String correspondent) {
        peopleTab.assertNewPrimaryCorrespondent(correspondent);
    }

    @Then("{string} should be removed as a correspondent on the case")
    public void assertCorrespondentRemovedFromCase(String correspondent) {
        peopleTab.assertCorrespondentHasBeenRemoved(correspondent);
    }
}
