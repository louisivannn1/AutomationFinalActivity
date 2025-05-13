package louisivanvirgo;

import louisivanvirgo.AbstractComponents.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;

public class OverviewTest extends BaseTest {
	private ProductPage productPage;
	private CartPage cartPage;
	private CheckoutPage checkoutPage;
	private OverviewPage overviewPage;
	private LoginPage login;

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
	public void testAccessToOverviewPage() {
		Assert.assertTrue(overviewPage.isOnOverviewPage(), "Did not redirect to overview page");
	}

	@Test
	public void testRequiredDetailsDisplayed() {
		Assert.assertTrue(overviewPage.areOverviewDetailsDisplayed(), "Not all overview details are displayed");
	}

	@Test
	public void testCancelButtonRedirection() {
		overviewPage.clickCancel();
		Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"), "Did not redirect to product page");
	}

	@Test
	public void testProductDetailsInCart() {

		cartPage.goToCartPage();
		Assert.assertTrue(cartPage.areCartDetailsVisible(), "Product details are not displayed properly");
	}

	@Test
	public void testTotalPriceCalculationIsCorrect() {
		double itemTotal = overviewPage.getItemTotal();
		double tax = overviewPage.getTax();
		double expectedTotal = itemTotal + tax;
		double actualTotal = overviewPage.getTotal();

		Assert.assertEquals(actualTotal, expectedTotal, 0.01, "Total price calculation is incorrect");
	}

}
