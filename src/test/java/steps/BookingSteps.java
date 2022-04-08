package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import staticdata.WebUrl;

import java.util.concurrent.TimeUnit;

public class BookingSteps {
    WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Given("User is on main screen")
    public void userIsOnMainScreen() {
        driver.get(WebUrl.BOOKING);
    }

    @When("User enters {string}")
    public void userEntersHotelName(String inputDestination) {
        String inputDestinationXpath = "//input[@name='ss']";
        driver.findElement(By.xpath(inputDestinationXpath)).sendKeys(inputDestination);
    }

    @And("User clicks on the Search button")
    public void userClicksOnTheButton() {
        try {
            String searchButtonXpath = "//button[@type ='submit']";
            driver.findElement(By.xpath(searchButtonXpath)).click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            String inputXpath = "//input[@name='ss']";
            driver.findElement(By.xpath(inputXpath)).click();
            String searchButtonXpath = "//button[@type ='submit']";
            driver.findElement(By.xpath(searchButtonXpath)).click();
        }
    }

    @Then("The page shows {string} and its rating")
    public void thePageShowsHotelNameAndItsRating(String expectedResult) {
        String hotelNameOnPageXpath = "//div[@data-testid = 'title' and contains(.,'Hilton Hurghada')]";
        String actualResult = driver.findElement(By.xpath(hotelNameOnPageXpath)).getText();
        Assert.assertEquals(actualResult, expectedResult, "Hotel names are not the same");
    }


    @Then("The page shows {string} rating")
    public void thePageShowsRating(String ratingOnPage) {
        String ratingOnPageXpath = "//ancestor::div[@data-testid='property-card' and contains(.,'Hurghada Plaza')]//div[contains(@aria-label,'Scored')]";
        String actualResult = driver.findElement(By.xpath(ratingOnPageXpath)).getText();
        Assert.assertEquals(actualResult, ratingOnPage, "The rating does not meet expectations");
    }
}
