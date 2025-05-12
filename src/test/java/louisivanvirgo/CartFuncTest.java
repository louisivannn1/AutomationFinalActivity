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


public class CartFuncTest extends BaseTest {

	private CartFunc cartPage;

	@BeforeMethod
	public void setUpPages() {
		LoginPage login = new LoginPage(driver);
		login.loginAs("standard_user", "secret_sauce");
		Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"), "Not on inventory page!");
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
		Assert.assertTrue( cartPage.isSidebarClosed());
	}
}
