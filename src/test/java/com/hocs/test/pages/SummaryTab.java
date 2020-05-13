package com.hocs.test.pages;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.Duration;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

public class SummaryTab extends BasePage {

    @FindBy(xpath = "//a[text()='Summary']")
    private WebElementFacade summaryTab;

    @FindBy(xpath = "//th[text()='Deadline']/following-sibling::td")
    private WebElementFacade deadline;

    @FindBy(xpath = "//th[text()='Primary topic']/following-sibling::td")
    private WebElementFacade primaryTopic;

    @FindBy(xpath = "//th[text()='Primary correspondent']/following-sibling::td")
    private WebElementFacade primaryCorrespondent;

    @FindBy(xpath = "//th[text()='How was the correspondence received?']/following-sibling::td")
    private WebElementFacade howCorrespondenceReceived;

    @FindBy(xpath = "//th[text()='Should the response be copied to Number 10?']/following-sibling::td")
    private WebElementFacade shouldResponseCopyToN10;

    @FindBy(xpath = "//th[text()='When was the correspondence received?']/following-sibling::td")
    private WebElementFacade whenCorrespondenceReceived;

    @FindBy(xpath = "//th[text()='When was the correspondence sent?']/following-sibling::td")
    private WebElementFacade whenCorrespondenceSent;

    @FindBy(xpath = "//h2[text()='Active stage']/following-sibling::table[1]/caption")
    private WebElementFacade activeStage;

    @FindBy(xpath = "//th[text()='Team']/following-sibling::td")
    private WebElementFacade currentTeam;

    @FindBy(xpath = "//th[text()='User']/following-sibling::td")
    private WebElementFacade assignedUser;

    public void selectSummaryTab() {
        safeClickOn(summaryTab);
    }

    public String getPrimaryCorrespondent() {
        return primaryCorrespondent.withTimeoutOf(Duration.ofSeconds(10)).waitUntilVisible().getText();
    }

    public String getActiveStage() {
        return activeStage.getText();
    }

    public void assertCaseStage(String stage) {
        assertThat(getActiveStage().toUpperCase(), is(stage.toUpperCase()));
    }
}