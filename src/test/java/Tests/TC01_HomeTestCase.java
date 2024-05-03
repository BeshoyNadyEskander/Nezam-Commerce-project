package Tests;

import Pages.P01_HomePage;
import Utilities.DataUtils;
import Utilities.LogsUtils;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;
import static Utilities.DataUtils.getEnvironmentValue;
import static Utilities.DataUtils.getJsonData;
import static Utilities.Utility.VerifyURL;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;

@Listeners({IInvokedMethodListenerClass.class, ITestResultListenerClass.class})
public class TC01_HomeTestCase {

    private static String VALIDEMAIL= getJsonData("validLogin" ,"ValidEmail");
    private static String VALIDPASSWORD= getJsonData("validLogin" ,"ValidPassword");
    @BeforeMethod
    public static void openBrowser() throws IOException {

        setupDriver(getEnvironmentValue("environment", "Browser"));
        LogsUtils.info("Browser is opened: " + getEnvironmentValue("environment", "Browser"));
        getDriver().navigate().to(getEnvironmentValue("environment", "BASE_URL"));
        LogsUtils.info("page is redirected to base URL: " + getEnvironmentValue("environment", "BASE_URL"));
        getDriver().manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));
    }

    @Description("Verify that is visible on home page successfully")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("beshoy")
    @Link(name = "Automation Exercise document", url = "https://automationexercise.com/")
    @TmsLink("www.jira.com/zephyr/TC1")
    @Epic("Web Panel testing")
    @Feature("Home page Feature")
    @Story("as a guest I want to navigate home page so that home page could be visible successfully ")
    @Test
    public static void verifyingOnHomePageUrlTC() throws IOException {

        LogsUtils.info("Actual Result: "+ getDriver().getCurrentUrl());
        Assert.assertTrue(VerifyURL(getDriver() , DataUtils.getEnvironmentValue("environment" ,"BASE_URL")));
        Assert.assertTrue(new P01_HomePage(getDriver()).checkOnSignUpButtonIsDisplayed()); //assert on signup button
    }






    @AfterMethod
    public static void quit() {
        quitDriver();

    }
}
