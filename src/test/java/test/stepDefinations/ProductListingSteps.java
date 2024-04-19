package test.stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import test.pages.ProductListingPage;

public class ProductListingSteps {
    ProductListingPage productListingPage = new ProductListingPage();

    @Given("click product list button")
    public void clickProductListButton() {
        productListingPage.clickProductListButton();
    }

    @Then("verify product list page")
    public void verifyProductListPage() {
        productListingPage.verifyProductList();
    }

    @When("select {int}. product from the list")
    public void selectProductFromTheList(int count) {
        productListingPage.selectProduct(count);

    }
}
