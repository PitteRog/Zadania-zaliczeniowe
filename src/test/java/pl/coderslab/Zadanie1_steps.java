package pl.coderslab;

import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;

public class Zadanie1_steps {
    WebDriver driver;
    PageObject pageObject;

    @Given("opened myStore webpage")
    public void beforeEach() {
        System.setProperty("webdriver.edge.driver", "src/test/resources/msedgedriver.exe");
        this.driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(16));
        //driver.manage().window().maximize();
        driver.get("https://mystore-testlab.coderslab.pl/index.php");
    }

    @When("user is signed in")
    public void userIsSignedIn() {
        pageObject = new PageObject(driver);
        pageObject.login();
    }

    @And("add first address button is clicked or addresses button is clicked")
    public void userClicksAddressButton() {
        pageObject.addAddress();
    }

    @And("^address form is filled with (.*) (.*) (.*) (.*)$")
    public void userFillsAddressForm(String address, String city, String postalCode, String phoneNumber) {
        pageObject.createNewAddress(address, city, postalCode, phoneNumber);

    }

    @And("save button is clicked")
    public void userClicksSaveButton() {
        pageObject.clickSaveButton();

    }

    @Then("{string} is displayed")
    public void userSees(String message) {
        message = "Address successfully added!";
        Assertions.assertTrue(pageObject.getSuccessInfo().equals(message));

    }

    @And("address is deleted")
    public void userDeletesAddress() {
        pageObject.deleteAddress();

    }
    @And("user sees {string}")
    public void deletionMessage(String message){
        message = "Address successfully deleted!";
        Assertions.assertTrue(pageObject.getDeletionInfo().equals(message));
    }
}
