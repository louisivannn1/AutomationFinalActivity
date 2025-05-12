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

public class ProductDetailTest extends BaseTest {
	ProductPage productPage;
	ProductDetailPage detailPage;

	@BeforeMethod
	public void setUpPages() {
		LoginPage login = new LoginPage(driver);
		login.loginAs("standard_user", "secret_sauce");
		Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"), "Not on inventory page!");

		productPage = new ProductPage(driver);
		detailPage = productPage.clickProduct(0);

	}

	@Test
	public void userCanAccessProductDetailPage() {
		Assert.assertTrue(driver.getCurrentUrl().contains("inventory-item.html"),
				"Product detail page did not load properly.");
	}

	@Test
	public void verifyAllDetailsAreDisplayed() {
		Assert.assertTrue(detailPage.areDetailsDisplayed(), "Some product details are not displayed");
	}

	@Test
	public void verifyRemoveButtonWhenInCart() {
		detailPage.clickAddToCart();
		Assert.assertEquals(detailPage.getCartButtonText(), "Remove", "Remove button not shown");
	}

	@Test
	public void verifyCartCountAfterRemove() {
		detailPage.clickAddToCart();
		String countAfterAdd = detailPage.getCartCount();

		detailPage.clickAddToCart();
		String countAfterRemove = detailPage.getCartCount();
		Assert.assertNotEquals(countAfterAdd, countAfterRemove, "Cart count did not change after removing");
		Assert.assertEquals(detailPage.getCartButtonText(), "Add to cart",
				"Button text did not revert to 'Add to cart'");

	}

	@Test
	public void verifyCartCountAfterAdd() {
		String countBefore = detailPage.getCartCount();
		detailPage.clickAddToCart();
		String countAfter = detailPage.getCartCount();

		Assert.assertEquals(detailPage.getCartButtonText(), "Remove", "Add to Cart button not clickable");
		Assert.assertNotEquals(countBefore, countAfter, "Cart count did not update after adding");
	}

}
