package test.pages;

import org.openqa.selenium.By;
import utilities.PageHelper;

public class ProductListingPage extends PageHelper {
    public ProductListingPage() {
        super();
    }

    By btnProductList = By.id("com.example.pocandroidapp:id/listProductsButton");
    By imgProduct = By.xpath("//android.widget.TextView[contains(@text, 'Product 1')]");


    public void clickProductListButton() {
        waitAndClick(btnProductList);
    }

    public void verifyProductList() {
        waitAndFindElement(imgProduct);
    }

    public void selectProduct(int count) {
        By selectedProduct = By.xpath("//android.widget.TextView[contains(@text, 'Product " + count + "')]");
        scrollUntilElement(selectedProduct);
        waitAndClick(selectedProduct);
    }

}
