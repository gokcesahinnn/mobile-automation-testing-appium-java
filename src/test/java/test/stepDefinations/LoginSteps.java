package test.stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import test.pages.LoginPage;

public class LoginSteps {
    LoginPage loginPage = new LoginPage();


    @Given("fill user name with {string} on the page")
    public void fillUserNameWithOnThePage(String username) {
        loginPage.fillUsername(username);
    }

    @Given("fill password with {string} on the page")
    public void fillPasswordWithOnThePage(String password) {
        loginPage.fillPassword(password);
    }

    @When("click login on the page")
    public void clickLoginOnThePage() {
        loginPage.clickLogin();
    }

    @Then("verify successful login")
    public void verifySuccessfulLogin() {
        loginPage.verifyLogin();
    }

}
