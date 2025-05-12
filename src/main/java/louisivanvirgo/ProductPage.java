package louisivanvirgo;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import louisivanvirgo.AbstractComponents.BasePage;
import louisivanvirgo.AbstractComponents.BaseTest;

public class ProductPage extends BasePage {

	public ProductPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);

	}

	@FindBy(className = "inventory_item")
	private List<WebElement> productItems;

	@FindBy(className = "inventory_item_name")
	private List<WebElement> productNames;

	@FindBy(className = "inventory_item_price")
	private List<WebElement> productPrices;

	@FindBy(className = "inventory_item_desc")
	private List<WebElement> productDescriptions;

	@FindBy(css = ".inventory_item_img img")
	private List<WebElement> productImages;

	@FindBy(css = ".btn_inventory")
	private List<WebElement> addToCartButtons;

	@FindBy(className = "product_sort_container")
	WebElement sortDropdown;

	@FindBy(className = "shopping_cart_link")
	WebElement cartIcon;
	
	public boolean areAllProductsVisible() {
		for (WebElement productItem : productItems) {
			if(!productItem.isDisplayed()) {
				return false;
			}
		}
		return true;
	}

	public boolean areAllProductDetailsPresent() {
		if (productItems.isEmpty() || productNames.isEmpty() || productImages.isEmpty()
				|| productDescriptions.isEmpty() || productPrices.isEmpty()) {
			return false;
		}
		for (int i = 0; i < productItems.size(); i++) {
			if (waitForElementToBeVisible(productNames.get(i)).getText().isEmpty())
				return false;
			String imageSrc = waitForElementToBeVisible(productImages.get(i)).getDomAttribute("src");
			if (imageSrc == null || imageSrc.isEmpty())
				return false;
			if (waitForElementToBeVisible(productDescriptions.get(i)).getText().isEmpty())
				return false;
			if (waitForElementToBeVisible(addToCartButtons.get(i)).getText().isEmpty())
				return false;
			if (waitForElementToBeVisible(productPrices.get(i)).getText().isEmpty())
					return false;
		}

		return true;
	}

	public void clickAddToCart(int index) {
		addToCartButtons.get(index).click();
		WebElement button = addToCartButtons.get(index);
		wait.until(ExpectedConditions.textToBePresentInElement(button, "Remove"));
	}

	public String getAddToCartButtonText(int index) {
		return addToCartButtons.get(index).getText();
	}

	public void selectSortOption(String visibleText) {
		org.openqa.selenium.support.ui.Select select = new org.openqa.selenium.support.ui.Select(sortDropdown);
		select.selectByVisibleText(visibleText);
	}

	public boolean isSortedByName(boolean ascending) {
		List<String> names = new ArrayList<>();
		for (WebElement name : productNames) {
			names.add(name.getText());
		}
		List<String> sortedNames = new ArrayList<>(names);
		sortedNames.sort(String::compareTo);
		if (!ascending)
			Collections.reverse(sortedNames);
		return names.equals(sortedNames);

	}

	public boolean isSortedByPrice(boolean ascending) {
		List<Double> prices = new ArrayList<>();
		for (WebElement price : productPrices) {
			prices.add(Double.parseDouble(price.getText().replace("$", "")));
		}
		List<Double> sortedPrices = new ArrayList<>(prices);
		sortedPrices.sort(Double::compareTo);
		if (!ascending)
			Collections.reverse(sortedPrices);
		return prices.equals(sortedPrices);

	}

	public ProductDetailPage clickProduct(int index) {
		waitForElementToBeClickable(productNames.get(index)).click();
		return new ProductDetailPage(driver);
	}

	// for overview
	public void clickCartIcon() {
		waitForElementToBeClickable(cartIcon).click();
	}
}
