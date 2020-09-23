package com.hocs.test.glue;

import com.hocs.test.pages.AddCorrespondent;
import com.hocs.test.pages.CreateCase;
import com.hocs.test.pages.CreateCase_SuccessPage;
import com.hocs.test.pages.Homepage;
import com.hocs.test.pages.mpam.Creation;
import io.cucumber.java.en.And;

public class AddCorrespondentStepDefs {

    AddCorrespondent addCorrespondent;

    Homepage homepage;

    CreateCase createCase;

    Creation creation;

    @And("I add a public correspondent")
    public void iAddAPublicCorrespondent() {
        addCorrespondent.addAPublicCorrespondent();
    }

    @And("I add {string} MP as a correspondent")
    public void IAddMPCorrespondent(String name) {
        addCorrespondent.addAMemberCorrespondent(name);
    }

    @And("I create a {string} case and add a correspondent with the correspondent reference number {string}")
    public void iCreateACaseAndAddACorrespondentWithARandomCorrespondentReferenceNumber(String caseType, String refNumber) {
        createCase.createCaseOfType(caseType);
        homepage.goHome();
        homepage.getAndClaimCurrentCase();
        creation.moveCaseWithCorrespondentReferenceNumber(refNumber);
    }
}
