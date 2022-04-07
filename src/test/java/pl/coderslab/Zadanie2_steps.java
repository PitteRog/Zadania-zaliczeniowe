package pl.coderslab;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Zadanie2_steps {
    private WebDriver driver;
    private List<String> priceList;

    public Zadanie2_steps(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#_desktop_logo > a > img")
    WebElement myStoreLogo;

    @FindBy(css = "#content > section > div > article:nth-child(1) > div > div.product-description > h3 > a")
    WebElement hummingbirdPrintedSweater;

    @FindBy(css = "#main > div.row > div:nth-child(2) > div.product-prices > div.product-price.h5.has-discount > div > span.discount.discount-percentage")
    WebElement discount;

    @FindBy(xpath = "//*[@id=\"quantity_wanted\"]")
    WebElement quantity;

    @FindBy(css = "#add-to-cart-or-refresh > div.product-add-to-cart > div > div.add > button")
    WebElement addToCartButton;

    @FindBy(css = "#blockcart-modal > div > div > div.modal-body > div > div.col-md-7 > div > div > a")
    WebElement checkoutButton;

    @FindBy(css = "#main > div > div.cart-grid-right.col-xs-12.col-lg-4 > div.card.cart-summary > div.checkout.cart-detailed-actions.card-block > div > a")
    WebElement checkout2;

    @FindBy(name = "address1")
    WebElement addressInput;

    @FindBy(name = "postcode")
    WebElement postalCodeInput;

    @FindBy(name = "city")
    WebElement cityInput;

    @FindBy(name = "phone")
    WebElement phoneInput;

    @FindBy(name = "confirm-addresses")
    WebElement confirmAddressButton;

    @FindBy(name = "confirmDeliveryOption")
    WebElement confirmDeliveryButton;

    @FindBy(css = "#payment-option-1")
    WebElement paymentOption;

    @FindBy(id = "conditions_to_approve[terms-and-conditions]")
    WebElement termsAndConditions;

    @FindBy(css = "#payment-confirmation > div.ps-shown-by-js > button")
    WebElement paymentConfirmation;

    @FindBy(css = "#order-items > div > table > tbody > tr.font-weight-bold > td:nth-child(2)")
    WebElement price;

    @FindBy(css = "#_desktop_user_info > div > a.account")
    WebElement userInfo;

    @FindBy(css = "#history-link")
    WebElement orderDetails;


    //YOUR ORDER IS CONFIRMED

    public void addToCart() {
        myStoreLogo.click();

        hummingbirdPrintedSweater.click();

        Assertions.assertEquals("SAVE 20%", discount.getText());

        Select size = new Select(driver.findElement(By.id("group_1")));
        size.selectByValue("2");

        try {
            quantity.clear();
            quantity.sendKeys("2");
        } catch (StaleElementReferenceException e) {
            System.out.println("exception");
        }

        addToCartButton.click();

        checkoutButton.click();
    }

    public void addAddress() {

        checkout2.click();

        addressInput.sendKeys("Street");

        postalCodeInput.sendKeys("00000");

        cityInput.sendKeys("City");

        Select countrySelect = new Select(driver.findElement(By.name("id_country")));
        countrySelect.selectByIndex(1);

        phoneInput.sendKeys("8888888888");

        confirmAddressButton.click();

    }

    public void orderPlacement() {

        confirmDeliveryButton.click();

        paymentOption.click();

        termsAndConditions.click();

        paymentConfirmation.click();

        priceList = new ArrayList<>();
        priceList.add(price.getText());


    }

    public void takeAScreenshot() {
        TakesScreenshot screenshot = (TakesScreenshot)driver;
        File orderScreenshot = screenshot.getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(orderScreenshot.toPath(), Paths.get("C:", "test evidence", "order_success.png"));
        } catch (IOException e) {
            System.out.println("Exception caught");
        }


    }


    public void orderStatus() {
        userInfo.click();
        orderDetails.click();

        WebElement price = driver.findElement(By.cssSelector("#content > table > tbody > tr:nth-child(1) > td.text-xs-right"));
        Assertions.assertEquals(priceList.get(0), price.getText());

        WebElement status = driver.findElement(By.cssSelector("#content > table > tbody > tr:nth-child(1) > td:nth-child(5) > span"));
        Assertions.assertTrue(status.getText().equals("Awaiting check payment"));

    }

}
