package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class P04_LoginPage {

    private final WebDriver driver;

    private final static By login_to_account_text = By.cssSelector("div[class=\"login-form\"] h2");
    private final static By loginEmailLocator = By.cssSelector("input[data-qa=\"login-email\"]");
    private final static By loginPasswordLocator = By.cssSelector("input[data-qa=\"login-password\"]");
    private final static By loginButtonLocator = By.cssSelector("button[data-qa=\"login-button\"]");
    private final static By InvalidDataMessageLocator = By.xpath("//p[.=\"Your email or password is incorrect!\"]");

    public P04_LoginPage(WebDriver driver)
    {
        this.driver = driver;
    }

    public String getTitleOfLoginTOAccount()
    {
        LogsUtils.info("title is displayed:" + driver.findElement(login_to_account_text).isDisplayed());
        return Utility.getText(driver , login_to_account_text);
    }
        public boolean checkOnLogginNameIsEmpty()
        {
            return Utility.generalWait(driver).until(ExpectedConditions.invisibilityOfElementLocated(login_to_account_text));
        }
        public boolean verifyTitleOfLoginIsDisplayed(String title)
    {

            LogsUtils.info("title is displayed:" + Utility.getText(driver , login_to_account_text) );
            return getTitleOfLoginTOAccount().equals(title);

    }

    public P04_LoginPage enterLoginEmail(String email)
    {
        Utility.sendData(driver ,loginEmailLocator ,email);
        return this;
    }

    public P04_LoginPage enterLoginPassword(String password)
    {
        Utility.sendData(driver ,loginPasswordLocator ,password);
        return this;
    }
    public P01_HomePage clickOnLoginButton()
    {
        Utility.clickingOnElement(driver , loginButtonLocator);
        return new P01_HomePage(driver);
    }

    public String getErrorMessageOfInvalidDataLogin()
    {
        LogsUtils.info("Error Message is displayed:" + driver.findElement(InvalidDataMessageLocator).isDisplayed());
        return Utility.getText(driver , InvalidDataMessageLocator);
    }

    public boolean verifyErrorMessageOfInvalidLoginIsDisplayed(String errorMessage)
    {

        LogsUtils.info("Error Message is:" + Utility.getText(driver , InvalidDataMessageLocator) );
        return getErrorMessageOfInvalidDataLogin().equals(errorMessage);

    }


}
