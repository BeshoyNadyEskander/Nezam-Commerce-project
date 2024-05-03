package Tests;

import Pages.P01_HomePage;
import Pages.P03_createdOrDeleteAccountPage;
import Pages.P04_LoginPage;
import Utilities.LogsUtils;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;
import static Utilities.DataUtils.getEnvironmentValue;
import static Utilities.DataUtils.getJsonData;
import static Utilities.Utility.VerifyURL;

public class TC04_LoginTestCase {

    private static SoftAssert softAssert;
    private static String TITLE_LOGIN= getJsonData("validLogin" ,"Title_Login");
    private static String VALIDEMAIL= getJsonData("validLogin" ,"ValidEmail");
    private static String VALIDPASSWORD= getJsonData("validLogin" ,"ValidPassword");
    private static String LOGGEDUSERNAME= getJsonData("validLogin" ,"UserNameLogged");
    private static String INVALIDEMAIL= getJsonData("InvalidLogin" ,"InValidEmail");
    private static String INVALIDPASWWOD= getJsonData("InvalidLogin" ,"ValidPassword");
    private static String ERRORMESSAGE= getJsonData("InvalidLogin" ,"MessageOfInvalidLogin");
    @BeforeMethod(alwaysRun = true)
    public static void openBrowser() throws IOException {

        String browser = System.getProperty("browser") !=null ? System.getProperty("browser")
                : getEnvironmentValue("environment", "Browser");
        LogsUtils.info("Browser is: " + System.getProperty("browser"));
        setupDriver(browser);
      //  setupDriver(getEnvironmentValue("environment", "Browser"));
        LogsUtils.info("Browser is opened: " + getEnvironmentValue("environment", "Browser"));
        getDriver().navigate().to(getEnvironmentValue("environment", "BASE_URL"));
        LogsUtils.info("page is redirected to base URL: " + getEnvironmentValue("environment", "BASE_URL"));
        getDriver().manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));
    }

    @Description("verify that login page is displayed after clicking on signup/login button")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("beshoy")
    @Link(name = "Automation Exercise document", url = "https://automationexercise.com/login")
    @TmsLink("www.jira.com/zephyr/TC1")
    @Epic("Web Panel testing")
    @Feature("Login Feature")
    @Story("as a guest I want to click on login button so that could navigate login page ")
    @Test
    public static void verifyOnUserNameIsLoggedAfterSignUp() throws IOException
    {
        new P01_HomePage(getDriver())
                .clickOnLoginButton();
        softAssert = new SoftAssert();
        softAssert.assertTrue(VerifyURL(getDriver(), getEnvironmentValue("environment" ,"LOGIN_URL")));
        softAssert.assertTrue(new P04_LoginPage(getDriver()).verifyTitleOfLoginIsDisplayed(TITLE_LOGIN));
        softAssert.assertAll();
    }

    @Description("user could login his account with valid data")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("beshoy")
    @Link(name = "Automation Exercise document", url = "https://automationexercise.com/login")
    @TmsLink("www.jira.com/zephyr/TC1")
    @Epic("Web Panel testing")
    @Feature("Login Feature")
    @Story("as a user I want to login with valid data so that could open his account as logged user ")
    @Test
    public static void VerifyOnUserCouldLoginSuccessfullyWithValidData() throws IOException
    {

        new P01_HomePage(getDriver())
                .clickOnLoginButton()
                .enterLoginEmail(VALIDEMAIL)
                .enterLoginPassword(VALIDPASSWORD)
                .clickOnLoginButton();
        Assert.assertTrue(new P01_HomePage(getDriver()).comparingLoggedNameWithUsername(LOGGEDUSERNAME));

    }

    @Description("user could delete his account successfully after Login ")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("beshoy")
    @Link(name = "Automation Exercise document", url = "https://automationexercise.com/")
    @TmsLink("www.jira.com/zephyr/TC1")
    @Epic("Web Panel testing")
    @Feature("Login Feature")
    @Story("as a logged user I want to delete the account so that go back home url ")
    @Test
    public static void verifyOnUserNameCouldDeleteHisAccountAfterLogin() throws IOException
    {

        new P01_HomePage(getDriver())
                .clickOnLoginButton()
                .enterLoginEmail(VALIDEMAIL)
                .enterLoginPassword(VALIDPASSWORD)
                .clickOnLoginButton()
                .clickOnDeleteAccout();

        softAssert= new SoftAssert();
        softAssert.assertTrue(VerifyURL(getDriver() , getEnvironmentValue("environment" , "DELETEDACCOUNT_URL")));
        softAssert.assertTrue(new P03_createdOrDeleteAccountPage(getDriver()).checkOnSuccessfullyMessageOfDeletingIsDisplayed());


        //click on continue button to navigate base URL (home page)
        new P03_createdOrDeleteAccountPage(getDriver()).clickOnContinueButton();
        softAssert.assertTrue(VerifyURL(getDriver(),getEnvironmentValue("environment" , "BASE_URL")));
        softAssert.assertAll();
    }

    @Description("user could not login his account with Invalid data")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("beshoy")
    @Link(name = "Automation Exercise document", url = "https://automationexercise.com/login")
    @TmsLink("www.jira.com/zephyr/TC1")
    @Epic("Web Panel testing")
    @Feature("Login Feature")
    @Story("as a user I want to login with Invalid data so that login error message is displayed ")
    @Test
    public static void VerifyOnUserCouldNotLoginWithInValidData() throws IOException
    {
        new P01_HomePage(getDriver())
                .clickOnLoginButton()
                .enterLoginEmail(INVALIDEMAIL)
                .enterLoginPassword(INVALIDPASWWOD)
                .clickOnLoginButton();
        softAssert = new SoftAssert();
        softAssert.assertTrue(VerifyURL(getDriver(), getEnvironmentValue("environment" ,"LOGIN_URL")));
        softAssert.assertTrue(new P04_LoginPage(getDriver()).verifyErrorMessageOfInvalidLoginIsDisplayed(ERRORMESSAGE));


    }



    @AfterMethod(alwaysRun = true)
    public static void quit() {
        quitDriver();

    }
}
