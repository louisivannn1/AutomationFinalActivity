package louisivanvirgo;

import io.github.bonigarcia.wdm.WebDriverManager;
import louisivanvirgo.AbstractComponents.BaseTest;
import louisivanvirgo.LoginPage;

import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {

	@Test
	public void testLoginPageElements() {
		LoginPage loginPage = new LoginPage(driver);
		Assert.assertTrue(loginPage.isUsernameInputDisplayed(), "Username field is not displayed");
		Assert.assertTrue(loginPage.isPasswordInputDisplayed(), "Password field is not displayed");
		Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "Login button field is not displayed");
	}

	@Test
	public void testIncorrectPassword() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginAs("standard_user", "wrong_pass");
		Assert.assertTrue(loginPage.isErrorDisplayed());
		
	}

	@Test
	public void testIncorrectUsername() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginAs("incorrect_user", "wrong_pass");
		Assert.assertTrue(loginPage.isErrorDisplayed());
	}

	@Test
	public void testEmptyUsername() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginAs("", "wrong_pass");
		Assert.assertTrue(loginPage.isErrorDisplayed());
	}

	@Test
	public void testEmptyPassword() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginAs("standard_user", "");
		Assert.assertTrue(loginPage.isErrorDisplayed());
	}

	@Test
	public void testEmptyFields() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginAs("", "");
		Assert.assertTrue(loginPage.isErrorDisplayed());
	}
	@Test
	public void testValidLogin() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginAs("standard_user", "secret_sauce");
		Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"), "User did not land on the products page after login");
	}
	@Test
	public void testLockedOutUserLogin() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginAs("locked_out_user", "secret_sauce");
		
		String expectedError = "Epic sadface: Sorry, this user has been locked out.";
		String actualError = loginPage.getErrorMessage();
		
		Assert.assertTrue(actualError.contains(expectedError),"Expected error message is not displayed for locked out user. Actual: " + actualError);
	}

	
}
