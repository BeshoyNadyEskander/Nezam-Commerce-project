package Tests;

import Pages.P01_HomePage;
import Pages.P03_createdOrDeleteAccountPage;
import Utilities.DataUtils;
import Utilities.LogsUtils;
import com.github.javafaker.Faker;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;
import static Utilities.DataUtils.getEnvironmentValue;
import static Utilities.Utility.VerifyURL;
import static Utilities.Utility.getTimeStamp;

public class TC03_CreatedOrDeletedAccountTestCase {

    private static String VALIDUSERNAME = DataUtils.getJsonData("signup" ,"validName");
    private static String VALIDEmail= DataUtils.getJsonData("signup" ,"validEmail")+ getTimeStamp();
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
    private static SoftAssert softAssert;


    @BeforeMethod
    public static void openBrowser() throws IOException {
// handled key browser that we can sent that by mvn command line -Dbrowser=edge
        String browser = System.getProperty("browser") !=null ? System.getProperty("browser")
                : getEnvironmentValue("environment", "Browser");
        LogsUtils.info("Browser is: " + System.getProperty("browser"));
        setupDriver(browser);
        //setupDriver(getEnvironmentValue("environment", "Browser"));
        LogsUtils.info("Browser is opened: " + getEnvironmentValue("environment", "Browser"));
        getDriver().navigate().to(getEnvironmentValue("environment", "BASE_URL"));
        LogsUtils.info("page is redirected to base URL: " + getEnvironmentValue("environment", "BASE_URL"));
        getDriver().manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));
    }

    @Description("username' is visible after filling steps of sign up successfully ")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("beshoy")
    @Link(name = "Automation Exercise document", url = "https://automationexercise.com/signup")
    @TmsLink("www.jira.com/zephyr/TC1")
    @Epic("Web Panel testing")
    @Feature("signupFeature")
    @Story("as a guest I want to continue registration steps so that user name is visible and logged in ")
    @Test
    public static void verifyOnUserNameIsLoggedAfterSignUp() throws IOException
    {
        new P01_HomePage(getDriver())
                .clickOnSignupButton()
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
                .clickOnContinueButton();
        softAssert= new SoftAssert();
        softAssert.assertTrue(VerifyURL(getDriver() , getEnvironmentValue("environment" , "BASE_URL")));
        softAssert.assertTrue(new P01_HomePage(getDriver()).comparingLoggedNameWithUsername(VALIDUSERNAME));
        softAssert.assertAll();
    }


    @Description("user could delete his account successfully after sign up ")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("beshoy")
    @Link(name = "Automation Exercise document", url = "https://automationexercise.com/")
    @TmsLink("www.jira.com/zephyr/TC1")
    @Epic("Web Panel testing")
    @Feature("signupFeature")
    @Story("as a logged user I want to delete the account so that go back home url ")
    @Test
    public static void verifyOnUserNameCouldDeleteHisAccountAfterSignUp() throws IOException
    {
        new P01_HomePage(getDriver())
                .clickOnSignupButton()
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
                .clickOnDeleteAccout();

        softAssert= new SoftAssert();
        softAssert.assertTrue(VerifyURL(getDriver() , getEnvironmentValue("environment" , "DELETEDACCOUNT_URL")));
        softAssert.assertTrue(new P03_createdOrDeleteAccountPage(getDriver()).checkOnSuccessfullyMessageOfDeletingIsDisplayed());


        //click on continue button to navigate base URL (home page)
        new P03_createdOrDeleteAccountPage(getDriver()).clickOnContinueButton();
        softAssert.assertTrue(VerifyURL(getDriver(),getEnvironmentValue("environment" , "BASE_URL")));
        softAssert.assertAll();
    }




    @AfterMethod
    public static void quit() {
        quitDriver();

    }
}
