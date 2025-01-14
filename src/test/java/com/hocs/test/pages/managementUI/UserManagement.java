package com.hocs.test.pages.managementUI;

import com.hocs.test.pages.BasePage;
import config.User;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.Keys;

import static net.serenitybdd.core.Serenity.setSessionVariable;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class UserManagement extends BasePage {

    @FindBy(id = "Users-input")
    public WebElementFacade userSearchTypeAhead;

    @FindBy(xpath = "//button[text()='View user']")
    public WebElementFacade viewUserButton;

    @FindBy(xpath = "//button[text()='Add teams']")
    public WebElementFacade addTeamsButton;

    @FindBy(id = "Select teams-input")
    public WebElementFacade selectTeamsTypeAhead;

    @FindBy(xpath = "//button[text()='Add selected teams']")
    public WebElementFacade addSelectedTeamsButton;

    @FindBy(xpath = "//h2[text()='Success']")
    public WebElementFacade successBanner;

    public void searchForAUsersTeams(String inputUser) {
        User user = User.valueOf(inputUser.toUpperCase());
        String input = user.getAllocationText();
        setSessionVariable("inputUser").to(input);
        waitFor(userSearchTypeAhead);
        safeClickOn(userSearchTypeAhead);
        userSearchTypeAhead.sendKeys(input);
        waitABit(10000);
        userSearchTypeAhead.sendKeys(Keys.RETURN);
        safeClickOn(viewUserButton);
    }

    public void addUserToTeams(String team) {
        setSessionVariable("inputTeam").to(team);
        safeClickOn(addTeamsButton);
        waitFor(selectTeamsTypeAhead);
        safeClickOn(selectTeamsTypeAhead);
        selectTeamsTypeAhead.sendKeys(team);
        waitABit(10000);
        selectTeamsTypeAhead.sendKeys(Keys.RETURN);
        safeClickOn(addSelectedTeamsButton);
    }

    public void removeTeam(String team) {
        setSessionVariable("inputTeam").to(team);
        WebElementFacade teamRemoveHypertext = findBy("//td[contains(text(), '" + team + "')]/following-sibling::td/a");
        safeClickOn(teamRemoveHypertext);
    }

    public void assertCorrectUserTeamsAreDisplayed() {
        WebElementFacade usernameField = findBy("//input[@id='username']");
        String displayedUsername = usernameField.getValue();
        String inputUser = sessionVariableCalled("inputUser");
        waitABit(1000);
        assertThat(inputUser.contains(displayedUsername), is(true));
    }

    public void assertNewTeamIsAddedToUserTeams() {
        WebElementFacade teamDisplayedInTable = findBy("//td[text()='" + sessionVariableCalled("inputTeam") + "']");
        waitFor(teamDisplayedInTable);
        teamDisplayedInTable.shouldBeVisible();
    }

    public void assertSuccessfulAdditionToTeamBanner() {
        waitFor(successBanner);
        assertThat(successBanner.isVisible(), is(true));
    }

    public void assertTeamHasBeenRemoved() {
        WebElementFacade teamRemoveHypertext = findBy("//td[contains(text(), '" + sessionVariableCalled("inputTeam") + "')]");
        waitABit(500);
        assertThat(teamRemoveHypertext.isVisible(), is(false));
    }
}
