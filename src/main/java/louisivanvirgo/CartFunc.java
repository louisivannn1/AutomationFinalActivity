package louisivanvirgo;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import louisivanvirgo.AbstractComponents.BasePage;
import louisivanvirgo.AbstractComponents.BaseTest;

public class CartFunc extends BasePage {
	private WebDriver driver;

	public CartFunc(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(className = "shopping_cart_badge")
	private WebElement cartBadge;

	@FindBy(className = "shopping_cart_link")
	private WebElement cartLink;

	@FindBy(id = "react-burger-menu-btn")
	private WebElement menuButton;

	@FindBy(id = "react-burger-cross-btn")
	private WebElement closeSidebarButton;

	@FindBy(css = ".inventory_item button.btn_inventory")
	private List<WebElement> addToCartButtons;

	@FindBy(css = ".bm-item-list a")
	private List<WebElement> sidebarOptions;
	
	@FindBy(id= "logout_sidebar_link")
	private WebElement logoutOption;

	public int getCartCount() {
		try {
			WebElement badge = waitForElementToBeVisible(cartBadge);
			return Integer.parseInt(badge.getText());
		} catch (Exception e) {
			return 0;
		}

	}

	public void addItemToCartByIndex(int index) {
		wait.until(ExpectedConditions.visibilityOfAllElements(addToCartButtons));
		if (index >= 0 && index < addToCartButtons.size()) {
			WebElement button = waitForElementToBeClickable(addToCartButtons.get(index));
			button.click();
			wait.until(driver -> getCartCount() > 0);
		} else {
			throw new IllegalArgumentException("Invalid item index: " + index);
		}
	}

	public void addMultipleItemsToCart(int count) {
		for (int i = 0; i < count && i < addToCartButtons.size(); i++) {
			WebElement button = waitForElementToBeClickable(addToCartButtons.get(i));
			button.click();
		}
		wait.until(driver -> getCartCount() == count);
	}

	// Sidebar/Menu

	public void openSidebar() {
		waitForElementToBeClickable(menuButton).click();
		waitForElementToBeVisible(closeSidebarButton);
		waitForAllElementsToBeVisible(sidebarOptions);
	}

	public void closeSidebar() {
		waitForElementToBeClickable(closeSidebarButton).click();
	}

	public boolean isMenuOptionVisible(String optionText) {
		waitForAllElementsToBeVisible(sidebarOptions);
		return sidebarOptions.stream().anyMatch(option -> option.getText().equalsIgnoreCase(optionText));
	}

	public boolean isSidebarClosed() {
		try {
			return wait.until(ExpectedConditions.invisibilityOf(closeSidebarButton));
		} catch (Exception e) {
			return true;
		}
		
		
	}
	public void logOut() {
		waitForElementToBeClickable(logoutOption).click();
	}
}
