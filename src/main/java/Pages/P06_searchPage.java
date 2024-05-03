package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class P06_searchPage {
    private static  WebDriver driver;

    private static By selectedProdcutName = By.xpath("//div[@class=\"productinfo text-center\"]/p");
    private static By searchLabel = By.cssSelector("input[id=\"search_product\"]");
    private static By searchButton = By.cssSelector("button[id=\"submit_search\"]");

    private static List<WebElement> nameProducts;




    public P06_searchPage(WebDriver driver) {
        this.driver =driver;
    }

    public static String getSearchedNameProduct()
    {
       nameProducts = driver.findElements(selectedProdcutName);
        String searchedName = null;
        for (int i = 1; i <=nameProducts.size() ; i++) {
            By selectedProdcutName = By.xpath("(//div[@class=\"productinfo text-center\"]/p)[" + i + "]");
            searchedName = Utility.getText(driver, selectedProdcutName).toLowerCase();
            LogsUtils.info(searchedName);
        }

        return searchedName;
    }

    public P06_searchPage enterSearchedProduct(String productName)
    {
        Utility.sendData(driver , searchLabel ,productName);
        return this;
    }


    public P06_searchPage clickOnSearchButton()
    {
        Utility.clickingOnElement(driver , searchButton);
        return this;
    }

    public boolean comparingSearchProducts(String productName) {

      return  getSearchedNameProduct().contains(productName.toLowerCase());

    }



}
