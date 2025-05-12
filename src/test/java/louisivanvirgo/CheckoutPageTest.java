package louisivanvirgo;

import org.testng.annotations.BeforeMethod;
import louisivanvirgo.AbstractComponents.BaseTest;
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

public class CheckoutPageTest extends BaseTest {

	private CartFunc cartFunc;
	private CartPage cartPage;
	private CheckoutPage checkoutPage;
	private LoginPage login;

	@BeforeMethod
	public void setUpPages() {
		login = new LoginPage(driver);
		login.loginAs("standard_user", "secret_sauce");
		cartFunc = new CartFunc(driver);
		cartFunc.addItemToCartByIndex(0);
		cartPage = new CartPage(driver);
		cartPage.goToCartPage();
		cartPage.getCheckoutButton().click();
		checkoutPage = new CheckoutPage(driver);
	}

	@Test
	public void testAccessToCheckoutInfoPage() {
		Assert.assertTrue(checkoutPage.isOnYourInfoPage(), "User was not redirected to Information page");
	}

	@Test
	public void testFormElementsDisplayed() {
		Assert.assertTrue(checkoutPage.isFormDisplayed());
	}

	@Test
	public void testIfCancelRedirectsToCart() {
		checkoutPage.clickCancel();
		Assert.assertTrue(cartPage.isCartPageAccesibble());
	}

	@Test
	public void testIfEmptyFieldsShowsError() {
		checkoutPage.enterCheckoutInfo("", "", "");
		checkoutPage.clickContinue();
		Assert.assertTrue(checkoutPage.isErrorDisplayed(), "Error message was not displayed");
	}

	@Test
	public void testIfBlankFirstNameShowsError() {
		checkoutPage.enterCheckoutInfo("", "Virgo", "12345");
		checkoutPage.clickContinue();
		Assert.assertTrue(checkoutPage.isErrorDisplayed(), "Error message was not displayed");
	}

	@Test
	public void testIfBlankLastNameShowsError() {
		checkoutPage.enterCheckoutInfo("Ivan", "", "12345");
		checkoutPage.clickContinue();
		Assert.assertTrue(checkoutPage.isErrorDisplayed(), "Error message was not displayed");
	}

	@Test
	public void testIfBlankZipShowsError() {
		checkoutPage.enterCheckoutInfo("Ivan", "Virgo", "");
		checkoutPage.clickContinue();
		Assert.assertTrue(checkoutPage.isErrorDisplayed(), "Error message was not displayed");
	}

	@Test
	public void testFirstNameOnlyError() {
		checkoutPage.enterCheckoutInfo("Ivan", "", "");
		checkoutPage.clickContinue();
		Assert.assertTrue(checkoutPage.isErrorDisplayed(), "Error message was not displayed");
	}

	@Test
	public void testLastNameOnlyError() {
		checkoutPage.enterCheckoutInfo("", "Virgo", "");
		checkoutPage.clickContinue();
		Assert.assertTrue(checkoutPage.isErrorDisplayed(), "Error message was not displayed");
	}

	@Test
	public void testZipOnlyError() {
		checkoutPage.enterCheckoutInfo("", "", "12345");
		checkoutPage.clickContinue();
		Assert.assertTrue(checkoutPage.isErrorDisplayed(), "Error message was not displayed");
	}

	@Test
	public void testIfAllFieldsFilledProceeds() {
		checkoutPage.enterCheckoutInfo("Ivan", "Virgo", "12345");
		checkoutPage.clickContinue();
		Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-two.html"),
				"User was unable to proceed to overview page");
	}

}
