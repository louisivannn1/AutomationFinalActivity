package louisivanvirgo;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import louisivanvirgo.AbstractComponents.BasePage;
import louisivanvirgo.AbstractComponents.BaseTest;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this); // Initializes @FindBy annotations
	}

	@FindBy(id = "user-name")
	private WebElement usernameInput;

	@FindBy(id = "password")
	private WebElement passwordInput;

	@FindBy(id = "login-button")
	private WebElement loginButton;

	@FindBy(css = "[data-test='error']")
	private WebElement errorMessage;

	public void loginAs(String username, String password) {
		usernameInput.sendKeys(username);
		passwordInput.sendKeys(password);
		loginButton.click();
	}

	public boolean isUsernameInputDisplayed() {
		return usernameInput.isDisplayed();
	}

	public boolean isPasswordInputDisplayed() {
		return passwordInput.isDisplayed();
	}

	public boolean isLoginButtonDisplayed() {
		return loginButton.isDisplayed();
	}

	public String getErrorMessage() {
		return errorMessage.getText();
	}

	public boolean isErrorDisplayed() {
		try {
			return errorMessage.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

}
