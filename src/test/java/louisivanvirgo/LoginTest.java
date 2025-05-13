package louisivanvirgo;

import louisivanvirgo.AbstractComponents.LoginBaseTest;
import utilities.ExcelUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends LoginBaseTest {

	LoginPage loginPage;

	public void setupLoginPage() {
		loginPage = new LoginPage(driver);
	}

	@Test
	public void testLoginPageAccessibility() {
		setupLoginPage();
		Assert.assertTrue(loginPage.isUsernameInputDisplayed(), "Username input is not displayed");
		Assert.assertTrue(loginPage.isPasswordInputDisplayed(), "Password input is not displayed");
		Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "Login button is not displayed");
	}

	@Test
	public void testIncorrectPassword() {
		setupLoginPage();
		String[] data = ExcelUtil.getTestData("incorrectPassword");
		loginPage.loginAs(parse(data[0]), parse(data[1]));
		Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message not displayed");
		Assert.assertTrue(loginPage.getErrorMessage().contains(data[2]),
				"Expected error message: '" + data[2] + "' but got: '" + loginPage.getErrorMessage() + "'");
	}

	@Test
	public void testIncorrectUsername() {
		setupLoginPage();
		String[] data = ExcelUtil.getTestData("incorrectUsername");
		loginPage.loginAs(parse(data[0]), parse(data[1]));
		Assert.assertTrue(loginPage.isErrorDisplayed());
		Assert.assertTrue(loginPage.getErrorMessage().contains(data[2]));
	}

	@Test
	public void testBlankUsername() {
		setupLoginPage();
		String[] data = ExcelUtil.getTestData("blankUsername");
		loginPage.loginAs(parse(data[0]), parse(data[1]));
		Assert.assertTrue(loginPage.isErrorDisplayed());
		Assert.assertTrue(loginPage.getErrorMessage().contains(data[2]));
	}

	@Test
	public void testBlankPassword() {
		setupLoginPage();
		String[] data = ExcelUtil.getTestData("blankPassword");
		loginPage.loginAs(parse(data[0]), parse(data[1]));
		Assert.assertTrue(loginPage.isErrorDisplayed());
		Assert.assertTrue(loginPage.getErrorMessage().contains(data[2]));
	}

	@Test
	public void testBlankBoth() {
		setupLoginPage();
		String[] data = ExcelUtil.getTestData("blankBoth");
		loginPage.loginAs(parse(data[0]), parse(data[1]));
		Assert.assertTrue(loginPage.isErrorDisplayed());
		Assert.assertTrue(loginPage.getErrorMessage().contains(data[2]));
	}

	@Test
	public void testCorrectLogin() {
		setupLoginPage();
		String[] data = ExcelUtil.getTestData("correctLogin");
		loginPage.loginAs(parse(data[0]), parse(data[1]));
		Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"), "Login failed for correct credentials");
	}

	@Test
	public void testLockedOutUser() {
		setupLoginPage();
		String[] data = ExcelUtil.getTestData("lockedOutUser");
		loginPage.loginAs(parse(data[0]), parse(data[1]));
		Assert.assertTrue(loginPage.isErrorDisplayed());
		Assert.assertTrue(loginPage.getErrorMessage().contains(data[2]));
	}

	private String parse(String input) {
		return input.equalsIgnoreCase("(blank)") ? "" : input;
	}
}