package louisivanvirgo;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import louisivanvirgo.AbstractComponents.BasePage;

public class CartPage extends BasePage {
	private WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(className = "shopping_cart_link")
	private WebElement cartLink;

	@FindBy(className = "cart_item")
	private List<WebElement> cartItems;

	@FindBy(className = "inventory_item_name")
	private List<WebElement> productTitles;

	@FindBy(className = "inventory_item_desc")
	private List<WebElement> productDescriptions;

	@FindBy(className = "inventory_item_price")
	private List<WebElement> productPrices;

	@FindBy(className = "cart_quantity")
	private List<WebElement> itemQuantities;

	@FindBy(id = "continue-shopping")
	private WebElement continueShoppingButton;

	@FindBy(id = "checkout")
	public WebElement checkoutButton;

	@FindBy(css = ".cart_button")
	private List<WebElement> removeButtons;

	public void goToCartPage() {
		waitForElementToBeClickable(cartLink).click();
	}

	public boolean isCartPageAccessible() {
		return driver.getCurrentUrl().contains("cart.html");
	}

	public boolean areCartDetailsVisible() {
		return !productTitles.isEmpty() && productTitles.size() == productDescriptions.size()
				&& productDescriptions.size() == productPrices.size() && itemQuantities.size() == productTitles.size()
				&& continueShoppingButton.isDisplayed() && checkoutButton.isDisplayed();
	}

	public int getCartItemCount() {
		waitForAllElementsToBeVisible(cartItems);
		return cartItems.size();
	}

	public void clickContinueShopping() {
		waitForElementToBeClickable(continueShoppingButton).click();
	}

	public boolean isOnProductPage() {
		return driver.getCurrentUrl().contains("inventory.html");
	}

	public void removeItemByIndex(int index) {
		if (index >= 0 && index < removeButtons.size()) {
			waitForElementToBeClickable(removeButtons.get(index)).click();
			;
		}
	}

	public void removeAllItems() {
		for (WebElement button : removeButtons) {
			waitForElementToBeClickable(button).click();
		}

	}

	public WebElement getCheckoutButton() {
		return waitForElementToBeClickable(checkoutButton);
	}
}
