package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P08_checkoutPage {
    private final WebDriver driver;
    private static final By LoginOrRegisterButton = By.cssSelector("p[class=\"text-center\"] [href=\"/login\"]");
    private static By AAddressDetails= By.xpath("//*[contains(text(),\"Address Details\")]");
    private static By ReviewOnOrder= By.xpath("//*[contains(text(),\"Review Your Order\")]");
    private static By TextBox= By.xpath("//textarea[@name=\"message\"]");
    private static By paymentButton= By.cssSelector("a[href=\"/payment\"]");
    private static By cardNameLabel= By.cssSelector("input[name=\"name_on_card\"]");
    private static By cardNumberLabel= By.cssSelector("input[name=\"card_number\"]");
    private static By cvcLabel= By.cssSelector("input[name=\"cvc\"]");
    private static By expireCardMonth= By.cssSelector("input[name=\"expiry_month\"]");
    private static By expireCardYear= By.cssSelector("input[name=\"expiry_year\"]");
    private static By submitOrderButton= By.id("submit");
    private static By continueButton= By.cssSelector("a[data-qa=\"continue-button\"]");
    private static By messageSuccessPlaceOrder= By.xpath("//h2[@data-qa=\"order-placed\"]/following-sibling::p");

    public P08_checkoutPage(WebDriver driver) {

        this.driver =driver;
    }

    public P02_signupPage clickOnLoginOrRegisterButton()
    {
        Utility.clickingOnElement(driver , LoginOrRegisterButton);
        return new P02_signupPage(driver);
    }
    public boolean verifyOnAddressIsDisplayed(){
        return driver.findElement(AAddressDetails).isDisplayed();
    }
    public boolean verifyOnReviewOrdersIsDisplayed()
    {
        return driver.findElement(ReviewOnOrder).isDisplayed();
    }

    public P08_checkoutPage EnterCommentInsideTextBox(String comment)
    {
        Utility.sendData(driver , TextBox , comment );
        return this;
    }
    public P08_checkoutPage clickOnPlaceOrderButton()
    {
        Utility.clickingOnElement(driver , paymentButton);
        return this;
    }

    public P08_checkoutPage enterCardName(String name)
    {
        Utility.sendData(driver, cardNameLabel , name );
        return  this;
    }
    public P08_checkoutPage enterCardNumber(String cardNumber)
    {
        Utility.sendData(driver, cardNumberLabel , cardNumber );
        return  this;
    }
    public P08_checkoutPage enterCardCVC(String cvc)
    {
        Utility.sendData(driver, cvcLabel , cvc);
        return  this;
    }
    public P08_checkoutPage enterExpireMonthlyOfCard(String month)
    {
        Utility.sendData(driver, expireCardMonth , month);
        return  this;
    }
    public P08_checkoutPage enterExpireYearOfCard(String year)
    {
        Utility.sendData(driver, expireCardYear , year);
        return  this;
    }

    public P08_checkoutPage enterInfoForPaymentCard(String namecard, String numberCard , String cvc , String expireMonth , String expireYear)
    {
        enterCardName(namecard)
                .enterCardNumber(numberCard)
                .enterCardCVC(cvc)
                .enterExpireMonthlyOfCard(expireMonth)
                .enterExpireYearOfCard(expireYear);
        return this;
    }

    public P08_checkoutPage clickOnConfirmOrder()
    {
        Utility.clickingOnElement(driver , submitOrderButton);
        return this;
    }
    public P01_HomePage clickOnContinue()
    {
        Utility.clickingOnElement(driver , continueButton);
        return new P01_HomePage(driver);
    }



    public boolean verifyPlaceOrderIsDisplayed()
    {
      return driver.findElement(messageSuccessPlaceOrder).isDisplayed();
    }

}
