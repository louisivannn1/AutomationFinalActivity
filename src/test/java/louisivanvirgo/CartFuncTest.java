package louisivanvirgo;

import org.testng.annotations.BeforeMethod;
import louisivanvirgo.AbstractComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;


public class CartFuncTest extends BaseTest {

	private CartFunc cartPage;

	@BeforeMethod
	public void setUpPages() {

		cartPage = new CartFunc(driver);
	}

	@Test
	public void testCartCountUpdate() {
		cartPage.addItemToCartByIndex(0);
		Assert.assertEquals(cartPage.getCartCount(), 1);
	}

	@Test
	public void testCartCountMultiple() {
		cartPage.addMultipleItemsToCart(3);
		Assert.assertEquals(cartPage.getCartCount(), 3);
	}

	@Test
	public void testIfMenuOptionsAreVisible() {
		cartPage.openSidebar();
		Assert.assertTrue(cartPage.isMenuOptionVisible("All Items"));
		Assert.assertTrue(cartPage.isMenuOptionVisible("About"));
		Assert.assertTrue(cartPage.isMenuOptionVisible("Logout"));
		Assert.assertTrue(cartPage.isMenuOptionVisible("Reset App State"));
	}

	@Test
	public void testIfSidebarCloses() {
		cartPage.openSidebar();
		cartPage.closeSidebar();
		Assert.assertTrue(cartPage.isSidebarClosed());
	}
}