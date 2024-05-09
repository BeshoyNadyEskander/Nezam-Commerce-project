package Tests;

import Pages.P01_HomePage;
import Pages.P05_ProductsPage;
import Pages.P06_searchPage;
import Pages.P07_ViewCartPage;
import Utilities.DataUtils;
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
import static Utilities.Utility.VerifyURL;

public class TC07_viewCartTestCases {

    static SoftAssert softAssert;

    @BeforeMethod
    public static void openBrowser() throws IOException {

        setupDriver(getEnvironmentValue("environment", "Browser"));
        LogsUtils.info("Browser is opened: " + getEnvironmentValue("environment", "Browser"));
        getDriver().navigate().to(getEnvironmentValue("environment", "BASE_URL"));
        LogsUtils.info("page is redirected to base URL: " + getEnvironmentValue("environment", "BASE_URL"));
        getDriver().manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));
    }

    @Description("selected products name from products page are matched with products in cart page")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("beshoy")
    @Link(name = "Automation Exercise document", url = "https://automationexercise.com/products")
    @TmsLink("www.jira.com/zephyr/TC1")
    @Epic("Web Panel testing")
    @Feature("cart Feature")
    @Story("as a guest I want to select products from products page so that could verify selected products name are displayed in cart page ")
    @Test
    public static void verifyOnSelectedProdutsNameInCartView() throws IOException
    {
        new P01_HomePage(getDriver())
                .clickOnProductsButton()
                .addAllProductsIntoCart()
                .clickOnCartIcaon()
                .getSelectedProductsName();
       Assert.assertTrue(new P07_ViewCartPage(getDriver()).verifyOnSelectedName());


    }



    @AfterMethod
    public static void quit() {
        quitDriver();

    }
}





