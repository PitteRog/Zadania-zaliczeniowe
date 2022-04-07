package pl.coderslab;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PageObject {
    private WebDriver driver;

    public PageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#_desktop_user_info > div > a")
    WebElement signInButton;

    @FindBy(css = "#login-form > section > div:nth-child(2) > div.col-md-6 > input")
    WebElement emailInput;

    @FindBy(css = "#login-form > section > div:nth-child(3) > div.col-md-6 > div > input")
    WebElement passwordInput;

    @FindBy(css = "#submit-login")
    WebElement submitButton;

    @FindBy(id = "address-link")
    WebElement addAddressButton;

    @FindBy(css = "#wrapper > div > nav > ol > li:nth-child(3) > a")
    WebElement addressesButton;

    @FindBy(css = "#content > div.addresses-footer > a")
    WebElement createNewAddressButton;

    @FindBy(name ="address1")
    WebElement addressInput;

    @FindBy (name = "postcode")
    WebElement postalCodeInput;

    @FindBy(name = "city")
    WebElement cityInput;

    @FindBy(name = "phone")
    WebElement phoneNumberInput;

    @FindBy (css = "#content > div > div > form > footer > button")
    WebElement saveButton;

    @FindBy(css = "#notifications > div > article")
    WebElement successInformation;

    @FindBy (xpath = "/html/body/main/section/div/div/section/section/div[1]/article/div[2]/a[2]")
    WebElement deleteButton;

    @FindBy(css = "#notifications > div > article")
    WebElement deletionSuccessInfo;


    public void login() {

        signInButton.click();

        emailInput.sendKeys("randomEmail@gmail.com");

        passwordInput.sendKeys("Mypassword123");

        submitButton.click();
    }

    public void addAddress() {

        addAddressButton.click();
        addressesButton.click();
        createNewAddressButton.click();
    }

    public void createNewAddress(String address, String city, String postalCode, String phoneNumber) {

        addressInput.sendKeys(address);

        postalCodeInput.sendKeys(postalCode);

        cityInput.sendKeys(city);

        Select countrySelect = new Select(driver.findElement(By.name("id_country")));
        countrySelect.selectByIndex(1);

        phoneNumberInput.sendKeys(phoneNumber);

    }

    public void clickSaveButton() {

        try {
            saveButton.click();
        } catch (StaleElementReferenceException e) {

        }

    }
    public String getSuccessInfo() {
        return successInformation.getText();
    }

    public void deleteAddress() {
        deleteButton.click();
    }
    public String getDeletionInfo(){
        return deletionSuccessInfo.getText();
    }
}
