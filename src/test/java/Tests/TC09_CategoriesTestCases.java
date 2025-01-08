package Tests;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import Pages.P01_HomePage;
import Pages.P04_LoginPage;
import Pages.P08_checkoutPage;
import Utilities.DataUtils;
import Utilities.LogsUtils;
import com.github.javafaker.Faker;
import io.qameta.allure.*;
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
import static Utilities.Utility.VerifyURL;
import static Utilities.Utility.getTimeStamp;

@Listeners({IInvokedMethodListenerClass.class, ITestResultListenerClass.class})
public class TC09_CategoriesTestCases {




    private static SoftAssert softAssert;
    @BeforeMethod
    public static void openBrowser() throws IOException {

        setupDriver(getEnvironmentValue("environment", "Browser"));
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
    public static void clickOnRandomMainCategory() throws IOException, InterruptedException {
      new P01_HomePage(getDriver()).clickOnRandomMainCategory().selectedRandomSub();

    }



    @AfterMethod
    public static void quit() {
//quitDriver();

    }
}





