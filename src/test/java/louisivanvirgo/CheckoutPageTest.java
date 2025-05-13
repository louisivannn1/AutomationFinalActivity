package louisivanvirgo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import louisivanvirgo.AbstractComponents.BaseTest;
import utilities.CheckoutPageUtil;

public class CheckoutPageTest extends BaseTest {

    private CartFunc cartFunc;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private LoginPage login;

    @BeforeMethod
    public void setUpPages() {
     
        cartFunc = new CartFunc(driver);
        cartFunc.addItemToCartByIndex(0);

        cartPage = new CartPage(driver);
        cartPage.goToCartPage();
        cartPage.getCheckoutButton().click();

        checkoutPage = new CheckoutPage(driver);
    }

    @Test
    public void testAccessToCheckoutInfoPage() {
        Assert.assertTrue(checkoutPage.isOnYourInfoPage(), "User was not redirected to Information page");
    }

    @Test
    public void testFormElementsDisplayed() {
        Assert.assertTrue(checkoutPage.isFormDisplayed(), "Form elements are not displayed");
    }

    @Test
    public void testIfCancelRedirectsToCart() {
        checkoutPage.clickCancel();
        Assert.assertTrue(cartPage.isCartPageAccessible(), "Cancel button did not redirect to cart");
    }

    @Test
    public void testIfEmptyFieldsShowsError() {
        String[] data = CheckoutPageUtil.getCheckoutTestData("blankAll");
        checkoutPage.enterCheckoutInfo(data[0], data[1], data[2]);
        checkoutPage.clickContinue();
        Assert.assertTrue(checkoutPage.isErrorDisplayed(), "Error message was not displayed");
    }

    @Test
    public void testIfBlankFirstNameShowsError() {
        String[] data = CheckoutPageUtil.getCheckoutTestData("blankFirstName");
        checkoutPage.enterCheckoutInfo(data[0], data[1], data[2]);
        checkoutPage.clickContinue();
        Assert.assertTrue(checkoutPage.isErrorDisplayed(), "Error message was not displayed");
    }

    @Test
    public void testIfBlankLastNameShowsError() {
        String[] data = CheckoutPageUtil.getCheckoutTestData("blankLastName");
        checkoutPage.enterCheckoutInfo(data[0], data[1], data[2]);
        checkoutPage.clickContinue();
        Assert.assertTrue(checkoutPage.isErrorDisplayed(), "Error message was not displayed");
    }

    @Test
    public void testIfBlankZipShowsError() {
        String[] data = CheckoutPageUtil.getCheckoutTestData("blankZip");
        checkoutPage.enterCheckoutInfo(data[0], data[1], data[2]);
        checkoutPage.clickContinue();
        Assert.assertTrue(checkoutPage.isErrorDisplayed(), "Error message was not displayed");
    }

    @Test
    public void testFirstNameOnlyError() {
        String[] data = CheckoutPageUtil.getCheckoutTestData("firstNameOnly");
        checkoutPage.enterCheckoutInfo(data[0], data[1], data[2]);
        checkoutPage.clickContinue();
        Assert.assertTrue(checkoutPage.isErrorDisplayed(), "Error message was not displayed");
    }

    @Test
    public void testLastNameOnlyError() {
        String[] data = CheckoutPageUtil.getCheckoutTestData("lastNameOnly");
        checkoutPage.enterCheckoutInfo(data[0], data[1], data[2]);
        checkoutPage.clickContinue();
        Assert.assertTrue(checkoutPage.isErrorDisplayed(), "Error message was not displayed");
    }

    @Test
    public void testZipOnlyError() {
        String[] data = CheckoutPageUtil.getCheckoutTestData("zipOnly");
        checkoutPage.enterCheckoutInfo(data[0], data[1], data[2]);
        checkoutPage.clickContinue();
        Assert.assertTrue(checkoutPage.isErrorDisplayed(), "Error message was not displayed");
    }

    @Test
    public void testIfAllFieldsFilledProceeds() {
        String[] data = CheckoutPageUtil.getCheckoutTestData("validCheckout");
        checkoutPage.enterCheckoutInfo(data[0], data[1], data[2]);
        checkoutPage.clickContinue();
        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-two.html"),
                "User was unable to proceed to overview page");
    }
}