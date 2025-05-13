package louisivanvirgo;

import org.testng.annotations.BeforeMethod;
import louisivanvirgo.AbstractComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartPageTest extends BaseTest {
	private CartFunc cartFunc;
	private CartPage cartPage;

	@BeforeMethod
	public void setUpPages() {

		cartFunc = new CartFunc(driver);
		cartPage = new CartPage(driver);
	}

	@Test
	public void testAccessCartPage() {
		cartPage.goToCartPage();
		Assert.assertTrue(cartPage.isCartPageAccessible(), "Cart page is not accessible");
	}

	@Test
	public void testCartDetailsIsDisplayed() {
		cartFunc.addItemToCartByIndex(0);
		cartPage.goToCartPage();
		Assert.assertTrue(cartPage.areCartDetailsVisible(), "Not all cart details are visible");
	}

	@Test
	public void testCSButtonButtonRedirectsToInventory() {
		cartPage.goToCartPage();
		cartPage.clickContinueShopping();
		Assert.assertTrue(cartPage.isOnProductPage(), "Continue Shopping Button did not redirect back to product page");
	}

	@Test
	public void testAddedProducIsDisplayedInCart() {
		cartFunc.addItemToCartByIndex(0);
		cartPage.goToCartPage();
		Assert.assertTrue(cartPage.getCartItemCount() >= 1, "Product not displayed in cart");
	}

	@Test
	public void testMultipleAddedProductDisplayedInCart() {
		cartFunc.addMultipleItemsToCart(3);
		cartPage.goToCartPage();
		Assert.assertEquals(cartPage.getCartItemCount(), 3, "Not all products were added to cart");
	}

	@Test
	public void testProductDetailsInCart() {
		cartFunc.addItemToCartByIndex(0);
		cartPage.goToCartPage();
		Assert.assertTrue(cartPage.areCartDetailsVisible(), "Product details are not displayed properly");
	}

	@Test
	public void testRemoveItemFromCart() {
		cartFunc.addMultipleItemsToCart(2);
		cartPage.goToCartPage();
		int initialCount = cartPage.getCartItemCount();
		cartPage.removeItemByIndex(0);
		Assert.assertTrue(cartPage.getCartItemCount() < initialCount, "Cart count did not update");
	}

	@Test
	public void testRemoveAllItemsFromCart() {
		cartFunc.addMultipleItemsToCart(3);
		cartPage.goToCartPage();
		cartPage.removeAllItems();
		Assert.assertEquals(cartFunc.getCartCount(), 0, "Not all items were removed");
	}

	@Test
	public void testIfCartPersistAfterRelogin() {
		cartFunc.addItemToCartByIndex(0);
		Assert.assertEquals(cartFunc.getCartCount(), 1);

		cartFunc.openSidebar();
		cartFunc.logOut();

		login.loginAs("standard_user", "secret_sauce");

		cartPage = new CartPage(driver);
		cartPage.goToCartPage();
		Assert.assertTrue(cartPage.getCartItemCount() > 0, "Cart did not persist after re-login");
	}
}