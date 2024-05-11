package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Random;

import static Utilities.Utility.findWebElement;
import static Utilities.Utility.getText;

public class P05_ProductsPage {
    private static WebDriver driver;
    private final static By TitleOfProductsLocator = By.cssSelector("h2[class=\"title text-center\"]");
    private final static By viewAllProductsLocator = By.xpath("//div[@class=\"choose\"]");
    private static By selectedProdcutName = By.xpath("//div[@class=\"productinfo text-center\"]/p");
    private static By selectedProductPrice = By.xpath("//div[@class=\"productinfo text-center\"]//h2");
    private final static By getDetailNameProductLocator = By.cssSelector("div[class=\"product-information\"] h2");
    private final static By categoryLocator = By.xpath("//div[@class=\"product-information\"]/img/preceding-sibling::p");
    private final static By priceDetailsLocator = By.xpath("//div[@class=\"product-information\"]/span");
    private final static By availabilityLocator = By.xpath("//*[text()=\"Availability:\"]");
    private final static By conditionLocator = By.xpath("//*[text()=\"Condition:\"]");
    private final static By searchLabel = By.cssSelector("input[id=\"search_product\"]");
    private final static By addToCartButton = By.xpath("(//div[@class=\"productinfo text-center\"]//a[@data-product-id])");
    private final static By continueShoppingButton = By.xpath("//button[@data-dismiss=\"modal\"]");
    private final static By successMessageAddProduct = By.xpath("//p[text()=\"Your product has been added to cart.\"]");
    private final static By viewCartIcon = By.cssSelector("a[href=\"/view_cart\"]");
    private final static By quantityButton = By.id("quantity");
    private final static By addIntoCart = By.cssSelector("button[class=\"btn btn-default cart\"]");

    private static List<WebElement> allProducts;
    static int randomProduct = 0;
    static String ProductName;
    static String price;
    public static String getproductName = null;
    private static String successAddedProductMessage=null;
    private static List<WebElement> viewAllProductsWebList;


    public P05_ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean verifyOnTitleOfAllProductsIsDisplayed() {
        try {
            LogsUtils.info("Title products page is: " + getText(driver, TitleOfProductsLocator));
            return driver.findElement(TitleOfProductsLocator).isDisplayed();
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
            return false;
        }
    }

    public boolean verifyOnAllProductsIsVisable() {
        viewAllProductsWebList = driver.findElements(viewAllProductsLocator);
        LogsUtils.info("size of product: " + viewAllProductsWebList.size());
        for (int i = 1; i <= viewAllProductsWebList.size(); i++) {
            By viewAllProducts = By.xpath("(//div[@class=\"choose\"])[" + i + "]");
            Utility.verifyOnButtonIsDisplayed(driver, viewAllProducts);
        }
        return true;
    }

    /*public By selectedRandomProduct()
    {
        int countProducts = driver.findElements(viewAllProductsLocator).size(); //size of all products
        LogsUtils.info("size of products: " + countProducts);

        int randomProduct= new Random().nextInt(countProducts);  // random product from size of all products
        LogsUtils.info("random genearte is :"+ randomProduct);

        By selectedRandomProduct = By.xpath("(//div[@class=\"choose\"])[" + randomProduct + "]"); // pass random product in dynamic locator
        Utility.scrolling(driver , selectedRandomProduct); //scroll to generated random locator

         selectedProdcutName = By.xpath("(//div[@class=\"productinfo text-center\"]/p)["+randomProduct+"]");
        LogsUtils.info("selected product number: " + Utility.getText(driver , selectedProdcutName));
        ProductName = Utility.getText(driver , selectedProdcutName);

        return selectedRandomProduct;
    }*/

    public int generateRandomNumberOfProduct() {
        int countProducts = driver.findElements(viewAllProductsLocator).size(); //size of all products
        LogsUtils.info("size of products: " + countProducts);

        randomProduct = new Random().nextInt(countProducts);  // random product from size of all products
        LogsUtils.info("random genearte is :" + randomProduct);
        return randomProduct;
    }


    public P05_ProductsPage clickOnSelectedRandomProduct() {
        By selectedRandomProduct = By.xpath("(//div[@class=\"choose\"])[" + generateRandomNumberOfProduct() + "]"); // pass random product in dynamic locator
        Utility.scrolling(driver, selectedRandomProduct); //scroll to generated random locator

        selectedProdcutName = By.xpath("(//div[@class=\"productinfo text-center\"]/p)[" + randomProduct + "]");
        LogsUtils.info("selected product number: " + getText(driver, selectedProdcutName));
        ProductName = getText(driver, selectedProdcutName);

        selectedProductPrice = By.xpath("(//div[@class=\"productinfo text-center\"]//h2)[" + randomProduct + "]");
        price = getText(driver, selectedProductPrice);
        LogsUtils.info("selected product price: " + price);

        Utility.clickingOnElement(driver, selectedRandomProduct); // click on generated random locator
        return this;
    }

    public String getProductDetailName() {
        LogsUtils.info("name of product in detail product page: " + getText(driver, getDetailNameProductLocator));
        return getText(driver, getDetailNameProductLocator);
    }

    public boolean comparingNameProductsThatAreSelected() {
        return getProductDetailName().equals(ProductName);
    }

    public String getCategory() {
        LogsUtils.info("category details: " + getText(driver, categoryLocator));
        return getText(driver, categoryLocator);
    }

    public boolean verifyOnCategoryDetailsISDisplayed() {
        try {
            getCategory().contains("Category:");
            return driver.findElement(categoryLocator).isDisplayed();
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
            getCategory().isEmpty();
            return false;
        }
    }

    public String getDetailProductPrice() {
        try {

            LogsUtils.info("price of product: " + getText(driver, priceDetailsLocator));
            return getText(driver, priceDetailsLocator);
        } catch (Exception e) {

            LogsUtils.error(e.getMessage());
            return "";
        }
    }

    public boolean comparingOnPrice() {

        return getDetailProductPrice().contains(price);
    }

    public String getAvailabilityProducts() {
        return Utility.getText(driver, availabilityLocator).toLowerCase();
    }

    public boolean verifyOnAvailabilityProductIsDisplayed() {
try {
        if (getAvailabilityProducts().contains("in stock")) {

            LogsUtils.info("availability of product : " + Utility.getText(driver, availabilityLocator));

        } else if (getAvailabilityProducts().contains("out stock")) {
            LogsUtils.info("availability of product : " + Utility.getText(driver, availabilityLocator));

        }

    }catch (Exception e){
        LogsUtils.error(e.getMessage());
        return false;
    }
            return true;
}

public boolean verifyOnConditionProductIsDisplayed()
{
    LogsUtils.info("condition is displayed: " + Utility.findWebElement(driver , conditionLocator).isDisplayed());
    return  Utility.generalWait(driver).until(ExpectedConditions.visibilityOfElementLocated(conditionLocator)).isDisplayed();
}

    public P06_searchPage scrollingIntoSearchLabel()
    {
        Utility.scrolling(driver , searchLabel);
        return new P06_searchPage(driver);
    }



    public String getSuccessAddedMessage()
    {
        try {

            LogsUtils.info("product is added successfully");
            return Utility.getText(driver , successMessageAddProduct);
        }catch (Exception e){
            LogsUtils.error(e.getMessage());
            return "";
        }


    }

    public String getProductsName()
    {

        return addAllProductsIntoCart().getproductName;

    }

    public P05_ProductsPage clickOnContinueShopping()
    {
        Utility.clickingOnElement(driver , continueShoppingButton);
        return this;
    }


    public P05_ProductsPage addAllProductsIntoCart()
    {
        allProducts = driver.findElements(addToCartButton);
        LogsUtils.info("size of products: " +allProducts.size());

        for (int i =1;  i<= allProducts.size(); i++) {

            //get selected product name
            //1- dynamic locator for selected name
            By selectedProdcutName = By.xpath("(//div[@class=\"productinfo text-center\"]/p)[" + i + "]");
            getproductName = Utility.getText(driver, selectedProdcutName);
            LogsUtils.info("product is: " +getproductName);


            //dynamic locator
           By addToCartButtonForAllProducts = By.xpath("(//div[@class=\"productinfo text-center\"]//a[@data-product-id])["+i+"]");
            Utility.clickingOnElement(driver , addToCartButtonForAllProducts);

            // call get message method to get text on message is displayed for each products
            successAddedProductMessage = getSuccessAddedMessage();
            //wait to continue shopping button
            clickOnContinueShopping();


        }
            return this;
    }



    public boolean verifyOnSuccessAdded(String message) {

       return successAddedProductMessage.equals(message);

    }

    public P07_ViewCartPage clickOnCartViewIcaon()
    {
        Utility.clickingOnElement(driver , viewCartIcon);

        return new P07_ViewCartPage(driver);
    }

    public P05_ProductsPage addQuantityForProduct(int numOfQuantity)
    {
        new Actions(driver).moveToElement(findWebElement(driver,quantityButton)).perform();
        driver.findElement(quantityButton).clear();
        driver.findElement(quantityButton).sendKeys(String.valueOf(numOfQuantity));
        return this;

    }

    public  P05_ProductsPage clickOnAddIntoCart()
    {
        Utility.clickingOnElement(driver , addIntoCart);
        return this;
    }


}
