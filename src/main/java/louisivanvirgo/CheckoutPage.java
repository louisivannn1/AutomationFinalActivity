package louisivanvirgo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import louisivanvirgo.AbstractComponents.BasePage;

public class CheckoutPage extends BasePage {
	private WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[@class='title']")
	private WebElement header;

	@FindBy(id = "first-name")
	private WebElement firstNameField;

	@FindBy(id = "last-name")
	private WebElement lastNameField;

	@FindBy(id = "postal-code")
	private WebElement zipField;

	@FindBy(id = "cancel")
	private WebElement cancelButton;

	@FindBy(id = "continue")
	private WebElement continueButton;

	@FindBy(css = "[data-test='error']")
	private WebElement errorMessage;

	public boolean isOnYourInfoPage() {
		return driver.getCurrentUrl().contains("checkout-step-one.html");
	}

	public boolean isFormDisplayed() {
		return waitForElementToBeVisible(header).getText().equals("Checkout: Your Information")
				&& waitForElementToBeVisible(firstNameField).isDisplayed()
				&& waitForElementToBeVisible(lastNameField).isDisplayed()
				&& waitForElementToBeVisible(zipField).isDisplayed()
				&& waitForElementToBeVisible(cancelButton).isDisplayed()
				&& waitForElementToBeVisible(continueButton).isDisplayed();
	}

	public void enterCheckoutInfo(String firstName, String lastName, String zip) {

		if (firstName != null)
			firstNameField.sendKeys(firstName);
		if (lastName != null)
			lastNameField.sendKeys(lastName);
		if (zip != null)
			zipField.sendKeys(zip);
	}

	public void clickContinue() {
		waitForElementToBeClickable(continueButton).click();
	}

	public void clickCancel() {
		waitForElementToBeClickable(cancelButton).click();
	}

	public boolean isErrorDisplayed() {
		return waitForElementToBeVisible(errorMessage).isDisplayed();
	}

	public String getErrorMessageText() {
		return waitForElementToBeVisible(errorMessage).getText();
	}

}
