package Tests;

import Pages.P01_HomePage;
import Pages.P05_ProductsPage;
import Pages.P06_searchPage;
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

public class TC06_SearchProductsTestCases {

    static SoftAssert softAssert;
    private static String ValidTypeOfSearch = DataUtils.getJsonData("products" ,"Sleeveless").toLowerCase();

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

    @Description("verify on clicking on products button will be navigated to products page url")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("beshoy")
    @Link(name = "Automation Exercise document", url = "https://automationexercise.com/products")
    @TmsLink("www.jira.com/zephyr/TC1")
    @Epic("Web Panel testing")
    @Feature("Products Feature")
    @Story("as a guest I want to click on products button so that could navigate products page and Title is displayed ")
    @Test
    public static void verifyOnNavigatingToProductsPage() throws IOException
    {
        new P01_HomePage(getDriver())
                .clickOnProductsButton();
        Assert.assertTrue(VerifyURL(getDriver() , getEnvironmentValue("environment" ,"PRODUCTS_URL")));
        Assert.assertTrue(new P05_ProductsPage(getDriver()).verifyOnTitleOfAllProductsIsDisplayed());
        Assert.assertTrue(new P05_ProductsPage(getDriver()).verifyOnAllProductsIsVisable());

    }

    @Test
    public static void VerifyOnSearchedProductNameMatchWithRelatingOfSearchedProducts() throws IOException
    {

       new P01_HomePage(getDriver())
               .clickOnProductsButton()
               .scrollingIntoSearchLabel()
                .enterSearchedProduct(ValidTypeOfSearch)
                .clickOnSearchButton();

        Assert.assertTrue(new P06_searchPage(getDriver()).comparingSearchProducts(ValidTypeOfSearch));
    }

    @AfterMethod
    public static void quit() {
        quitDriver();

    }
}





