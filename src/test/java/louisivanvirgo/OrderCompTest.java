package louisivanvirgo;

import louisivanvirgo.AbstractComponents.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;

public class OrderCompTest extends BaseTest {

	private OverviewPage overviewPage;
	private OrderCompPage orderCompPage;
	private ProductPage productPage;
	private CartPage cartPage;
	private CheckoutPage checkoutPage;

	@BeforeMethod
	public void setUpPages() {

		productPage = new ProductPage(driver);
		productPage.clickAddToCart(0);
		productPage.clickCartIcon();

		cartPage = new CartPage(driver);
		cartPage.getCheckoutButton().click();

		checkoutPage = new CheckoutPage(driver);
		checkoutPage.enterCheckoutInfo("Ivan", "Virgo", "12345");
		checkoutPage.clickContinue();

		overviewPage = new OverviewPage(driver);
	}

	@Test
	public void testFinishButton() {
		overviewPage.clickFinish();
		orderCompPage = new OrderCompPage(driver);
		Assert.assertTrue(orderCompPage.isOnOrderCompletionPage(), "Not on order completion page");
	}

	@Test
	public void verifyIfUserIsOnOrderCompletionPage() {
		overviewPage.clickFinish();
		orderCompPage = new OrderCompPage(driver);
		Assert.assertTrue(orderCompPage.isOnOrderCompletionPage(), "Not on order completion page");
	}

	@Test
	public void verifyIfAllDetailsAreDisplayed() {
		overviewPage.clickFinish();
		orderCompPage = new OrderCompPage(driver);
		Assert.assertTrue(orderCompPage.areOrderCompletionDetailsDisplayed(), "Some of the details are missing");
	}

	@Test
	public void testBackToProductPage() {
		overviewPage.clickFinish();
		orderCompPage = new OrderCompPage(driver);
		orderCompPage.clickBackHome();
		Assert.assertTrue(orderCompPage.isRedirectedToProductPage(), "Did not go back to product page");
	}
}
