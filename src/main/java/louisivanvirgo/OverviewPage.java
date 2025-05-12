package louisivanvirgo;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import louisivanvirgo.AbstractComponents.BasePage;

public class OverviewPage extends BasePage {
	private WebDriver driver;

	public OverviewPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".cart_item")
	private List<WebElement> productList;

	@FindBy(css = ".summary_info > div:nth-child(1)")
	private WebElement paymentInfo;

	@FindBy(css = ".summary_info > div:nth-child(2)")
	private WebElement shippingInfo;

	@FindBy(css = ".summary_subtotal_label")
	private WebElement itemTotal;

	@FindBy(css = ".summary_tax_label")
	private WebElement tax;

	@FindBy(css = ".summary_total_label")
	private WebElement total;

	@FindBy(id = "cancel")
	private WebElement cancelButton;

	@FindBy(xpath = "//button[@id='finish']")
	private WebElement finishButton;

	// Validation methods
	public boolean isOnOverviewPage() {
		return driver.getCurrentUrl().contains("checkout-step-two.html");
	}

	public boolean isProductListDisplayed() {
		return !productList.isEmpty();
	}

	public boolean isPaymentInfoDisplayed() {
		return paymentInfo.isDisplayed();
	}

	public boolean isShippingInfoDisplayed() {
		return shippingInfo.isDisplayed();
	}

	public boolean isPriceTotalDisplayed() {
		return itemTotal.isDisplayed();
	}

	public boolean isTaxDisplayed() {
		return tax.isDisplayed();
	}

	public boolean isFinalTotalDisplayed() {
		return total.isDisplayed();
	}

	public boolean isFinishButtonDisplayed() {
		return finishButton.isDisplayed();
	}

	public boolean isCancelButtonDisplayed() {
		return cancelButton.isDisplayed();
	}

	public boolean areOverviewDetailsDisplayed() {
		return isProductListDisplayed() && isPaymentInfoDisplayed() && isShippingInfoDisplayed()
				&& isPriceTotalDisplayed() && isTaxDisplayed() && isFinalTotalDisplayed() && isFinishButtonDisplayed()
				&& isCancelButtonDisplayed();
	}

	public double getItemTotal() {
		return Double.parseDouble(itemTotal.getText().replace("Item total: $", ""));
	}

	public double getTax() {
		return Double.parseDouble(tax.getText().replace("Tax: $", ""));
	}

	public double getTotal() {
		return Double.parseDouble(total.getText().replace("Total: $", ""));
	}

	public void clickCancel() {
		waitForElementToBeClickable(cancelButton).click();
	}

	public void clickFinish() {
		waitForElementToBeClickable(finishButton).click();
	}
}
