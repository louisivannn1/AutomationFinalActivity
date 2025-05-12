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

public class ProductTest extends BaseTest {

	private ProductPage productPage;

	@BeforeMethod
	public void loginBeforeTest() {
		LoginPage login = new LoginPage(driver);
		login.loginAs("standard_user", "secret_sauce");
		productPage = new ProductPage(driver);
	}

	@Test
	public void testAccessToProductsPage() {
		Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"), "User isn't on products page");
	}

	@Test 
	public void testAllProductsAreVisible() {
		Assert.assertTrue(productPage.areAllProductsVisible(), "Not all products are visible");
	}
	@Test
	public void testAllProductDetailsAreDisplayed() {
		Assert.assertTrue(productPage.areAllProductDetailsPresent(), "Some details are missing");
	}

	@Test
	public void testAddToCartButtonChanges() {
		String buttonTextBefore = productPage.getAddToCartButtonText(0);
		Assert.assertEquals(buttonTextBefore, "Add to cart", "The initial button label is incorrect");

		productPage.clickAddToCart(0);
		String buttonTextAfter = productPage.getAddToCartButtonText(0);
		Assert.assertEquals(buttonTextAfter, "Remove", "Button label did not change to 'Remove'");
	}

	@Test
	public void testSortByNameAToZ() {
		productPage.selectSortOption("Name (A to Z)");
		Assert.assertTrue(productPage.isSortedByName(true), "Products are not sorted A to Z");
	}

	@Test
	public void testSortByNameZtoA() {
		productPage.selectSortOption("Name (Z to A)");
		Assert.assertTrue(productPage.isSortedByName(false), "Products are not sorted Z to A");
	}

	@Test
	public void testSortByPriceHighToLow() {
		productPage.selectSortOption("Price (high to low)");
		Assert.assertTrue(productPage.isSortedByPrice(false), "Products are not sorted High to Low");
	}

	@Test
	public void testSortByPriceLowToHigh() {
		productPage.selectSortOption("Price (low to high)");
		Assert.assertTrue(productPage.isSortedByPrice(true), "Products are not sorted Low to High");
	}
}
