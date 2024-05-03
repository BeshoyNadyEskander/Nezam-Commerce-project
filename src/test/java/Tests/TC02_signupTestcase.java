package Tests;
import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import Pages.P01_HomePage;
import Pages.P02_signupPage;

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
public class TC02_signupTestcase {

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

        setupDriver(getEnvironmentValue("environment", "Browser"));
        LogsUtils.info("Browser is opened: " + getEnvironmentValue("environment", "Browser"));
        getDriver().navigate().to(getEnvironmentValue("environment", "BASE_URL"));
        LogsUtils.info("page is redirected to base URL: " + getEnvironmentValue("environment", "BASE_URL"));
        getDriver().manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));
    }

    @Description("enter valid name and valid email to navigate information page to complete signup steps")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("beshoy")
    @Link(name = "Automation Exercise document", url = "https://automationexercise.com/signup")
    @TmsLink("www.jira.com/zephyr/TC1")
    @Epic("Web Panel testing")
    @Feature("signupFeature")
    @Story("as a guest I want to enter valid name and email so that navigate to information page to complete signup steps ")
    @Test
    public static void enterValidNameandEmailToGetAccountInformation() throws IOException
    {
       new P01_HomePage(getDriver())
               .clickOnSignupButton()
               .enterName(VALIDUSERNAME)
               .enterEmail(VALIDEmail)
               .clickOnSignUpButton();

     Assert.assertTrue(VerifyURL(getDriver(), DataUtils.getEnvironmentValue("environment" ,"ACCOUNTINFO_URL")));

    }
    @Description("enter valid name and valid email to navigate information page to complete signup steps")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("beshoy")
    @Link(name = "Automation Exercise document", url = "https://automationexercise.com/signup")
    @TmsLink("www.jira.com/zephyr/TC1")
    @Epic("Web Panel testing")
    @Feature("signupFeature")
    @Story("as a guest I want to enter valid name and email so that title of Account Information is displayed ")
    @Test
    public static void verifyOnAccountInformationTitleIsDisplayed() throws IOException
    {
        new P01_HomePage(getDriver())
                .clickOnSignupButton()
                .enterName(VALIDUSERNAME)
                .enterEmail(VALIDEmail)
                .clickOnSignUpButton();
        Assert.assertTrue(new P02_signupPage(getDriver()).checkOnTitleIsDisplayed());
    }






    @AfterMethod
    public static void quit() {
        quitDriver();

    }
}
