package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P03_createdOrDeleteAccountPage {
    private final WebDriver driver;
    private final static By createdAccountMessage = By.cssSelector("h2[class=\"title text-center\"]");
    private final static By deletedAccountMessage = By.cssSelector("h2[class=\"title text-center\"]");
    private final static By continueButton = By.cssSelector("a[data-qa=\"continue-button\"]");

    public P03_createdOrDeleteAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getSuccessfullyMessage() {
        return Utility.getText(driver, createdAccountMessage);

    }

    public boolean checkOnSuccessfullyMessageIsDisplayed() {
        driver.findElement(createdAccountMessage).isDisplayed();
        LogsUtils.info("message of creating account: " + getSuccessfullyMessage());
        return true;
    }

    public P01_HomePage clickOnContinueButton()
    {
        Utility.clickingOnElement(driver , continueButton);
        return new P01_HomePage(driver);
    }
    public String getSuccessfullyMessageOfDeletingAccount() {
        return Utility.getText(driver, deletedAccountMessage);

    }

    public boolean checkOnSuccessfullyMessageOfDeletingIsDisplayed() {
        driver.findElement(deletedAccountMessage).isDisplayed();
        LogsUtils.info("message of creating account: " + getSuccessfullyMessageOfDeletingAccount());
        return true;
    }

}
