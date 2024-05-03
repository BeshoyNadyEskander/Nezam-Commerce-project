package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static Utilities.Utility.getText;

public class P01_HomePage {

    private final WebDriver driver;
    private final static By signupLocator = By.cssSelector("a[href=\"/login\"]");
    private final static By loginLocator = By.cssSelector("a[href=\"/login\"]");
    private final static By LoggedUserNameLocator = By.xpath("//a//b");

    private final static By deleteAccountButton = By.cssSelector("a[href=\"/delete_account\"]");
    private final static By LogoutButton = By.cssSelector("a[href=\"/logout\"]");
    private final static By ProductsButton = By.cssSelector("a[href=\"/products\"]");

    public P01_HomePage(WebDriver driver)
    {
        this.driver =driver;
    }

    public  P02_signupPage clickOnSignupButton()
    {
        Utility.clickingOnElement(driver , signupLocator);
        return new P02_signupPage(driver);
    }


    public boolean checkOnSignUpButtonIsDisplayed()
    {
        return Utility.verifyOnButtonIsDisplayed(driver , signupLocator);
    }

    public String getLoggedInUserName()
    {
        try {
            LogsUtils.info("user name is: "+ getText(driver , LoggedUserNameLocator));
            return getText(driver , LoggedUserNameLocator);

        }catch (Exception e)
        {
            LogsUtils.error(e.getMessage());
            return "didn't logged in username";
        }


    }
    public boolean comparingLoggedNameWithUsername(String userName)
    {

        return getLoggedInUserName().equals(userName);
    }

    public P03_createdOrDeleteAccountPage clickOnDeleteAccout()
    {
        Utility.clickingOnElement(driver , deleteAccountButton);
        return new P03_createdOrDeleteAccountPage(driver);
    }

    public  P04_LoginPage clickOnLoginButton()
    {
        Utility.clickingOnElement(driver , loginLocator);
        return new P04_LoginPage(driver);
    }

    public P04_LoginPage clickOnLogOutButton()
    {
        Utility.clickingOnElement(driver , LogoutButton);
        return new P04_LoginPage(driver);
    }

    public P05_ProductsPage clickOnProductsButton()
    {
        Utility.clickingOnElement(driver,ProductsButton);
        return new P05_ProductsPage(driver);
    }


}
