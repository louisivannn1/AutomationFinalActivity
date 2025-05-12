package louisivanvirgo;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import louisivanvirgo.AbstractComponents.BasePage;
import louisivanvirgo.AbstractComponents.BaseTest;

public class ProductDetailPage extends BasePage {

	public ProductDetailPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(className = "inventory_details_name")
	private WebElement detailName;

	@FindBy(className = "inventory_details_desc")
	private WebElement detailDescription;

	@FindBy(className = "inventory_details_price")
	private WebElement detailPrice;

	@FindBy(css = ".inventory_details_img")
	private WebElement detailImage;

	@FindBy(css = ".btn_inventory")
	private WebElement addToCartButton;

	@FindBy(className = "shopping_cart_badge")
	private WebElement cartBadge;

	public boolean areDetailsDisplayed() {
		waitForElementToBeVisible(detailName);
		waitForElementToBeVisible(detailDescription);
		waitForElementToBeVisible(detailPrice);
		waitForElementToBeVisible(detailImage);
		waitForElementToBeVisible(addToCartButton);
		
		return detailName.isDisplayed() && detailDescription.isDisplayed() && detailPrice.isDisplayed()
				&& detailImage.isDisplayed() && addToCartButton.isDisplayed();
	}

	public void clickAddToCart() {
		waitForElementToBeClickable(addToCartButton).click();
	}

	public String getCartButtonText() {
		return waitForElementToBeVisible(addToCartButton).getText();
	}

	public String getCartCount() {
	    List<WebElement> badges = driver.findElements(By.className("shopping_cart_badge"));
	    if (badges.isEmpty()) {
	        return "0";
	    } else {
	        return waitForElementToBeVisible(cartBadge).getText();
	    }
	}


}
