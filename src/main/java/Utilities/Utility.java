package Utilities;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.processing.SupportedOptions;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class Utility {
    private static final String SCREENSHOTS_PATH = "test-outputs/Screenshots/";

    public static void clickingOnElement(WebDriver driver, By locator) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(7))
                    .until(ExpectedConditions.elementToBeClickable(locator));
           driver.findElement(locator).click();
           LogsUtils.info("button is clickable: "+ locator);
        }catch (Exception e)
        {
            LogsUtils.error(e.getMessage());
        }


    }

    public static void sendData(WebDriver driver, By locator, String data) {
       try {
           new WebDriverWait(driver, Duration.ofSeconds(5))
                   .until(ExpectedConditions.visibilityOfElementLocated(locator));
           driver.findElement(locator).sendKeys(data);
           LogsUtils.info("sending data is: " + data);
       }catch (Exception e)
       {
           LogsUtils.error(e.getMessage());
       }

    }

    public static String getText(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();

    }

    public static WebDriverWait generalWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public static void scrolling(WebDriver driver, By locator) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView();", findWebElement(driver, locator));
    }



    public static WebElement findWebElement(WebDriver driver, By locator) {
        return driver.findElement(locator);
    }
    public static List<WebElement> findWebElements(WebDriver driver, By locator) {
        return driver.findElements(locator);
    }

    public static void selectingFromDropDown(WebDriver driver, By locator, String option) {
        new Select(findWebElement(driver, locator)).selectByVisibleText(option);
    }

    public static String getTimeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd-hh-mm-ssa").format(new Date());
    }

    public static void takeScreenShot(WebDriver driver, String screenshotName) {
        try {
            // Capture screenshot using TakesScreenshot
            File screenshotSrc = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Save screenshot to a file if needed
            File screenshotFile = new File(SCREENSHOTS_PATH + screenshotName + "-" + getTimeStamp() + ".png");
            FileUtils.copyFile(screenshotSrc, screenshotFile);

            // Attach the screenshot to Allure
            Allure.addAttachment(screenshotName, Files.newInputStream(Path.of(screenshotFile.getPath())));
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
        }
    }

    public static void takeFullScreenshot(WebDriver driver, By locator) {
        try {
            Shutterbug.shootPage(driver, Capture.FULL_SCROLL)
                    .highlight(findWebElement(driver, locator))
                    .save(SCREENSHOTS_PATH);
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
        }

    }

    public static int generateRandomNumber(int upperBound) { //0 >> upper-1  > 5
        return new Random().nextInt(upperBound) + 1;
    }

    //Set >> unique >> 1,2,3,4,3 > condition
    public static Set<Integer> generateUniqueNumber(int numberOfProductsNeeded, int totalNumberOfProducts) //5 >> 50
    {
        Set<Integer> generatedNumbers = new HashSet<>();
        while (generatedNumbers.size() < numberOfProductsNeeded) { //11111 > 1 2 10 5 7
            int randomNumber = generateRandomNumber(totalNumberOfProducts);
            generatedNumbers.add(randomNumber);
        }
        return generatedNumbers;
    }

    public static boolean VerifyURL(WebDriver driver, String expectedURL) {
        try {
            generalWait(driver).until(ExpectedConditions.urlToBe(expectedURL));
            LogsUtils.info("Actual URL: " +driver.getCurrentUrl());
            LogsUtils.info("Expected URL: " + expectedURL);

        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean verifyOnButtonIsDisplayed(WebDriver driver , By locator)
    {
        try {
            generalWait(driver).until(ExpectedConditions
                    .visibilityOf(findWebElement(driver,locator)));
            findWebElement(driver , locator).isDisplayed();
        }catch (Exception e)
        {
            LogsUtils.error(e.getMessage());
            return false;
        }
       LogsUtils.info("button is displayed on DOM");
        return true;

    }


    // handle cookie and inject cookie
    public static Set<Cookie> getAllCookies(WebDriver driver) {
        return driver.manage().getCookies();
    }

    public static void restoreSession(WebDriver driver, Set<Cookie> cookies) {
        for (Cookie cookie : cookies)
            driver.manage().addCookie(cookie);
    }

    public static File getLatestFile(String folderPath) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        assert files != null;
        if (files.length == 0)
            return null;
        Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());

        return files[0];
    }
}
