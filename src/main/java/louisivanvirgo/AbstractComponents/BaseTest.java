package louisivanvirgo.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import louisivanvirgo.LoginPage;

public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected LoginPage login;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Initialize the login object and perform login
        login = new LoginPage(driver);
        login.loginAs("standard_user", "secret_sauce");
        
        // Assert that the login was successful
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"), "Not on inventory page!");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
