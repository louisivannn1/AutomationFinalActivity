package louisivanvirgo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import louisivanvirgo.AbstractComponents.BasePage;

public class OrderCompPage extends BasePage {
	private WebDriver driver;

	public OrderCompPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(className = "complete-header")
	private WebElement thankYouMessage;

	@FindBy(id = "back-to-products")
	private WebElement backHomeButton;

	public boolean isOnOrderCompletionPage() {
		return driver.getCurrentUrl().contains("checkout-complete.html");
	}

	public boolean isThankYouMessageDisplayed() {
		return waitForElementToBeVisible(thankYouMessage).isDisplayed();
	}

	public boolean isBackHomeButtonDisplayed() {
		return waitForElementToBeVisible(backHomeButton).isDisplayed();
	}

	public void clickBackHome() {
		waitForElementToBeClickable(backHomeButton).click();

	}

	public boolean isRedirectedToProductPage() {
		return driver.getCurrentUrl().contains("inventory.html");
	}

	public boolean areOrderCompletionDetailsDisplayed() {
		return isThankYouMessageDisplayed() && isBackHomeButtonDisplayed();
	}
}
