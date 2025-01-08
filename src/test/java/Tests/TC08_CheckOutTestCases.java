package Tests;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import Pages.P01_HomePage;
import Pages.P04_LoginPage;
import Pages.P07_ViewCartPage;
import Pages.P08_checkoutPage;
import Utilities.DataUtils;
import Utilities.LogsUtils;
import com.github.javafaker.Faker;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;
import static Utilities.DataUtils.getEnvironmentValue;
import static Utilities.DataUtils.getJsonData;
import static Utilities.Utility.VerifyURL;
import static Utilities.Utility.getTimeStamp;

@Listeners({IInvokedMethodListenerClass.class, ITestResultListenerClass.class})
public class TC08_CheckOutTestCases {



    private static String VALIDUSERNAME = DataUtils.getJsonData("signup" ,"validName")+getTimeStamp() ;
    private static String VALIDEmail= DataUtils.getJsonData("signup" ,"validEmail")+ getTimeStamp()+ "@example.com";
    private static String VALIDPASSWORD= DataUtils.getJsonData("signup" ,"ValidPassword");
    private static String DAY= DataUtils.getJsonData("signup" ,"Day");
    private static String MONTH= DataUtils.getJsonData("signup" ,"Month");
    private static String YEAR= DataUtils.getJsonData("signup" ,"Year");
    private static String FIRRSTNAME= DataUtils.getJsonData("signup" ,"firstName");
    private static String LASTNAME= DataUtils.getJsonData("signup" ,"LastName");
    private static String COUNTRY= DataUtils.getJsonData("signup" ,"country");
    private static String CITY= DataUtils.getJsonData("signup" ,"city");
    private static String STATE= DataUtils.getJsonData("signup" ,"state");
    private static String COMPANY= new Faker().company().name();
    private static String FIRSTADDRESS= new Faker().address().fullAddress();
    private static String SECONDADDRESS= new Faker().address().secondaryAddress();
    private static String ZIPCODE= new Faker().number().digits(5);
    private static String PHONENUMBER= new Faker().phoneNumber().phoneNumber();
    private static String comment= DataUtils.getJsonData("checkout" , "comment");
    private static String getcardName= DataUtils.getJsonData("checkout" ,"cardName");
    private static String cardNumber= new Faker().business().creditCardNumber();
    private static String cvc= DataUtils.getJsonData("checkout" ,"cvc");
    private static String expireMonth= DataUtils.getJsonData("checkout" ,"expireMonthly");
    private static String expireYear= DataUtils.getJsonData("checkout" ,"expireYear");

    private static SoftAssert softAssert;
    @BeforeMethod
    public static void openBrowser() throws IOException {

        // handled key browser that we can sent that by mvn command line -Dbrowser=edge
        String browser = System.getProperty("browser") !=null ? System.getProperty("browser")
                : getEnvironmentValue("environment", "Browser");
        LogsUtils.info("Browser is: " + System.getProperty("browser"));
        setupDriver(browser);
       // setupDriver(getEnvironmentValue("environment", "Browser"));
        LogsUtils.info("Browser is opened: " + getEnvironmentValue("environment", "Browser"));
        getDriver().navigate().to(getEnvironmentValue("environment", "BASE_URL"));
        LogsUtils.info("page is redirected to base URL: " + getEnvironmentValue("environment", "BASE_URL"));
        getDriver().manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));
    }

    @Description("Register while checkout process")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("beshoy")
    @Link(name = "Automation Exercise document", url = "https://automationexercise.com/products")
    @TmsLink("www.jira.com/zephyr/TC1")
    @Epic("Web Panel testing")
    @Feature("cart Feature")
    @Story("as a guest I want to click on checkout so that could register while navigating checkout page ")
    @Test
    public static void verifyOnRegisterWhileNavigatingCheckoutProcees() throws IOException
    {
        new P01_HomePage(getDriver())
                .clickOnProductsButton()
                .clickOnSelectedRandomProduct()
                .clickOnAddIntoCart()
                .clickOnContinueShopping()
                .clickOnCartViewIcaon()
                .clickOnProceedToCheckoutButton()
                .clickOnLoginOrRegisterButton()
                .enterName(VALIDUSERNAME)
                .enterEmail(VALIDEmail)
                .clickOnSignUpButton()
                .clickOnGender()
                .enterPassword(VALIDPASSWORD)
                .enterBirthOfDate(DAY , MONTH , YEAR)
                .selectedCheckBoxs()
                .enterFirstName(FIRRSTNAME)
                .enterLastName(LASTNAME)
                .enterCompany(COMPANY)
                .enterAddress(FIRSTADDRESS ,SECONDADDRESS)
                .selectCountry(COUNTRY)
                .enterState(STATE)
                .enterCity(CITY)
                .enterzipCode(ZIPCODE)
                .enterPhoneNumber(PHONENUMBER)
                .clickONCreateAccount()
                .clickOnContinueButton()
                .clickOnCartButtonFromHomePage()
                .clickOnProceedToCheckoutButton();
        softAssert= new SoftAssert();
        softAssert.assertTrue(VerifyURL(getDriver() , getEnvironmentValue("environment" , "BASE_URL")));
        softAssert.assertTrue(new P01_HomePage(getDriver()).comparingLoggedNameWithUsername(VALIDUSERNAME));
        softAssert.assertTrue(new P08_checkoutPage(getDriver()).verifyOnAddressIsDisplayed());
        softAssert.assertTrue(new P08_checkoutPage(getDriver()).verifyOnReviewOrdersIsDisplayed());

        softAssert.assertAll();

    }

    @Description("submit order after registration")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("beshoy")
    @Link(name = "Automation Exercise document", url = "https://automationexercise.com/products")
    @TmsLink("www.jira.com/zephyr/TC1")
    @Epic("Web Panel testing")
    @Feature("cart Feature")
    @Story("as a registered user I want to submit orders so that could pay for his order ")
    @Test
    public static void verifyOnsumbittingOrderSuccessfully() throws IOException
    {
        new P01_HomePage(getDriver())
                .clickOnProductsButton()
                .clickOnSelectedRandomProduct()
                .clickOnAddIntoCart()
                .clickOnContinueShopping()
                .clickOnCartViewIcaon()
                .clickOnProceedToCheckoutButton()
                .clickOnLoginOrRegisterButton()
                .enterName(VALIDUSERNAME)
                .enterEmail(VALIDEmail)
                .clickOnSignUpButton()
                .clickOnGender()
                .enterPassword(VALIDPASSWORD)
                .enterBirthOfDate(DAY , MONTH , YEAR)
                .selectedCheckBoxs()
                .enterFirstName(FIRRSTNAME)
                .enterLastName(LASTNAME)
                .enterCompany(COMPANY)
                .enterAddress(FIRSTADDRESS ,SECONDADDRESS)
                .selectCountry(COUNTRY)
                .enterState(STATE)
                .enterCity(CITY)
                .enterzipCode(ZIPCODE)
                .enterPhoneNumber(PHONENUMBER)
                .clickONCreateAccount()
                .clickOnContinueButton()
                .clickOnCartButtonFromHomePage()
                .clickOnProceedToCheckoutButton()
                .EnterCommentInsideTextBox(comment)
                .clickOnPlaceOrderButton()
                .enterInfoForPaymentCard(getcardName , cardNumber,cvc , expireMonth ,expireYear )
                .clickOnConfirmOrder();
            Assert.assertTrue(new P08_checkoutPage(getDriver()).verifyPlaceOrderIsDisplayed());


    }

    @Description("delete an account after submitting order")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("beshoy")
    @Link(name = "Automation Exercise document", url = "https://automationexercise.com/products")
    @TmsLink("www.jira.com/zephyr/TC1")
    @Epic("Web Panel testing")
    @Feature("cart Feature")
    @Story("as a registered user I want to delete account after submitted order ")
    @Test
    public static void verifyOnDeleteaccountafterSubmitOrder() throws IOException
    {
        new P01_HomePage(getDriver())
                .clickOnProductsButton()
                .clickOnSelectedRandomProduct()
                .clickOnAddIntoCart()
                .clickOnContinueShopping()
                .clickOnCartViewIcaon()
                .clickOnProceedToCheckoutButton()
                .clickOnLoginOrRegisterButton()
                .enterName(VALIDUSERNAME)
                .enterEmail(VALIDEmail)
                .clickOnSignUpButton()
                .clickOnGender()
                .enterPassword(VALIDPASSWORD)
                .enterBirthOfDate(DAY , MONTH , YEAR)
                .selectedCheckBoxs()
                .enterFirstName(FIRRSTNAME)
                .enterLastName(LASTNAME)
                .enterCompany(COMPANY)
                .enterAddress(FIRSTADDRESS ,SECONDADDRESS)
                .selectCountry(COUNTRY)
                .enterState(STATE)
                .enterCity(CITY)
                .enterzipCode(ZIPCODE)
                .enterPhoneNumber(PHONENUMBER)
                .clickONCreateAccount()
                .clickOnContinueButton()
                .clickOnCartButtonFromHomePage()
                .clickOnProceedToCheckoutButton()
                .EnterCommentInsideTextBox(comment)
                .clickOnPlaceOrderButton()
                .enterInfoForPaymentCard(getcardName , cardNumber,cvc , expireMonth ,expireYear )
                .clickOnConfirmOrder()
                .clickOnContinue()
                .clickOnDeleteAccout()
                .clickOnContinueButton();

        Assert.assertTrue(new P04_LoginPage(getDriver()).checkOnLogginNameIsEmpty());



    }


    @AfterMethod
    public static void quit() {
        quitDriver();

    }
}





