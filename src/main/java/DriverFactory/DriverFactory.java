package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;

public class DriverFactory {
        private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

        public static void setupDriver(String browser) //Edge edge EDGE
        {
            switch (browser.toLowerCase())
            {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--start-maximized");
                   chromeOptions.addExtensions(new File("./Extensions/AdBlock.crx"));
                    driverThreadLocal.set(new ChromeDriver(chromeOptions));

                    break;
                case "firefox":
                    driverThreadLocal.set(new FirefoxDriver());
                    break;
                default:
                    EdgeOptions options = new EdgeOptions();
                    options.addArguments("--start-maximized");
                    options.addExtensions(new File("./Extensions/AdBlock.crx"));
                    driverThreadLocal.set(new EdgeDriver(options));
            }

        }
        public static WebDriver getDriver()
        {
          return driverThreadLocal.get();
        }
        public static void quitDriver()
        {

            getDriver().quit();
            driverThreadLocal.remove();
        }
}
