package Tests;

import Pages.P01_HomePage;
import Pages.P05_ProductsPage;
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

public class TC05_ProductsTestCases {

    static SoftAssert softAssert;

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
    public static void VerifyOnSelectedRandomProductsNameMatchInDetailProducts() throws IOException
    {
            new P01_HomePage(getDriver())
                    .clickOnProductsButton()
                    .clickOnSelectedRandomProduct();

            softAssert  =new SoftAssert();
           softAssert.assertTrue(new P05_ProductsPage(getDriver()).comparingNameProductsThatAreSelected()); // assert on selected product name

           softAssert.assertTrue(new P05_ProductsPage(getDriver()).verifyOnCategoryDetailsISDisplayed()); //assert on category is displayed

            softAssert.assertTrue(new P05_ProductsPage(getDriver()).comparingOnPrice());   // assert on price
     softAssert.assertTrue(new P05_ProductsPage(getDriver()).verifyOnAvailabilityProductIsDisplayed());

     softAssert.assertTrue(new P05_ProductsPage(getDriver()).verifyOnConditionProductIsDisplayed());

             softAssert.assertAll();
}


    @Description("verify  clicking on add to cart for all products ")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("beshoy")
    @Link(name = "Automation Exercise document", url = "https://automationexercise.com/products")
    @TmsLink("www.jira.com/zephyr/TC1")
    @Epic("Web Panel testing")
    @Feature("Products Feature")
    @Story("as a guest I want to click on add to cart for all products so that success message is displayed")
    @Test
    public static void VerifyOnSuccessMessageForAddAllProductsIntoCart() throws IOException
    {
        new P01_HomePage(getDriver())
                .clickOnProductsButton()
                .addAllProductsIntoCart();

        Assert.assertTrue(new P05_ProductsPage(getDriver()).verifyOnSuccessAdded(DataUtils.getJsonData("products","addedMessage")));

    }

    @AfterMethod
    public static void quit() {
        quitDriver();

    }
}





