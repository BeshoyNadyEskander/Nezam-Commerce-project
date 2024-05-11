package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class P07_ViewCartPage {
    private final WebDriver driver;
    private static final By selectedProducts = By.xpath("//td[@class=\"cart_description\"]//a[@href]");
    private static final By numOfQuantity = By.cssSelector("td[class=\"cart_quantity\"] [class=\"disabled\"]");
    private static List<WebElement> SelectedProducts;
    private String name;

   public P07_ViewCartPage(WebDriver driver)
   {
       this.driver = driver;
   }

    public P07_ViewCartPage getSelectedProductsName() {



    SelectedProducts = driver.findElements(selectedProducts);
    for (int i = 1; i <= SelectedProducts.size(); i++) {

        By productsName = By.xpath("(//td[@class=\"cart_description\"]//a[@href])[" + i + "]");
        LogsUtils.info("selected products name in cart page: " + Utility.getText(driver, productsName));
        name= Utility.getText(driver, productsName);
    }



        return this;
    }

    public String getNameProductFromCart()
    {
        try {

           return getSelectedProductsName().name;

        }catch (Exception e){

            LogsUtils.error(e.getMessage());
            return "";
        }

    }


    public boolean verifyOnSelectedName()
    {

        return  getNameProductFromCart().equals(new P05_ProductsPage(driver).getProductsName());
    }

    public String getQuantityOfProducts()
    {
        try {
            LogsUtils.info("number of quantity is: " +  Utility.getText(driver , numOfQuantity) );
            return Utility.getText(driver , numOfQuantity);
        }catch (Exception e){
            LogsUtils.error(e.getMessage());
            return "list is empty";
        }

    }




}
