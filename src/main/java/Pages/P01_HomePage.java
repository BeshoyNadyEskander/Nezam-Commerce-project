package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

import static Utilities.Utility.getText;

public class P01_HomePage {

    private final WebDriver driver;
    private final static By signupLocator = By.cssSelector("a[href=\"/login\"]");
    private final static By loginLocator = By.cssSelector("a[href=\"/login\"]");
    private final static By LoggedUserNameLocator = By.xpath("//a//b");

    private final static By deleteAccountButton = By.cssSelector("a[href=\"/delete_account\"]");
    private final static By LogoutButton = By.cssSelector("a[href=\"/logout\"]");
    private final static By ProductsButton = By.cssSelector("a[href=\"/products\"]");
    private final static By cartButton = By.cssSelector("a[href=\"/view_cart\"]");
    private final static By MainCategories = By.xpath("//a[@data-parent=\"#accordian\"]");
    private final static By subCategories = By.xpath("//div[@class=\"panel-body\"]/ul/li");
     private static List<WebElement> mainCategory;
    private static List<WebElement> subCategory;
    private static int  randomCategory;
    private static int  selectedSubCategory;
    private static String subCategoryName;


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

    public P07_ViewCartPage clickOnCartButtonFromHomePage()
    {
        Utility.clickingOnElement(driver , cartButton);
        return new P07_ViewCartPage(driver);
    }

//    public int generateRandomMainCategory()
//    {
//
//    }
    public P01_HomePage clickOnRandomMainCategory()
    {
        //1- save list of web element of main category and pass locator
        //  mainCategory = driver.findElements(MainCategories);
        mainCategory = Utility.findWebElements(driver , MainCategories);

        //2- count size of main category
        int sizeOfmainCat = mainCategory.size();
        LogsUtils.info("size of main Category:: "+ sizeOfmainCat );

        //3-create random category from list of main cat
        randomCategory = new Random().nextInt(sizeOfmainCat);
        LogsUtils.info("random of main categories : "+ randomCategory );
        LogsUtils.info("random of main categories : "+ mainCategory.get(randomCategory).getText() );


        //1-scroll into element
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView();", mainCategory.get(randomCategory));

        //click on selecting random main category
        mainCategory.get(randomCategory).click();

        return this;

    }

    public int genrateRandomSubCategory(int randomMainCategory) {
        randomMainCategory = randomMainCategory + 1;
        subCategory = driver.findElements(By.xpath("(//div[@class=\"panel-body\"]/ul/li)[" + randomMainCategory + "]"));
        LogsUtils.info("selected number of sub category: " + subCategory);

       // 1 - count of size for elements of sub categories
            int countSubCat =subCategory.size();
            LogsUtils.info("size of sub cat : " + countSubCat);
            //1-2 select random sub category from them
            int selectedSubCategory = new Random().nextInt(countSubCat);
            LogsUtils.info("selected number of sub categories " + selectedSubCategory );
            return selectedSubCategory;
    }

    public List<WebElement> randomSubCat(int randomSubCategory) {
        randomSubCategory = randomSubCategory + 1;
        //return subCategory = driver.findElements(By.xpath("(//div[@class=\"panel-body\"]/ul/li)["+randomSubCategory+"]"));
       return subCategory = driver.findElements(By.xpath("(//div[@class=\"panel-body\"]/ul)[" + randomSubCategory + "]/li"));
        //return subCategory = driver.findElements(By.xpath("((//div[@class=\"panel-body\"]/ul)[" + randomCategory + "]/li)[" + randomSubCategory + "]"));

    }
    public P01_HomePage selectedRandomSub() throws InterruptedException {

       subCategory= randomSubCat(randomCategory) ;

        if (!randomSubCat(randomCategory).isEmpty()) {
            //1 - count of size for elements of sub categories
            int countSubCat = subCategory.size();
            LogsUtils.info("size of sub cat : " + countSubCat);
            //1-2 select random sub category from them

             selectedSubCategory = new Random().nextInt(countSubCat);

                LogsUtils.info("sub categories is selected number " + selectedSubCategory);

                //1-3 get text for sub category
                this.subCategoryName = subCategory.get(selectedSubCategory).getText().trim();
                LogsUtils.info("sub categories name" + subCategoryName);
                subCategory.get(selectedSubCategory).click();

            /*    //1-4 click on sub categor
                 By subCat = By.xpath("((//div[@class=\"panel-body\"]/ul)[" + randomCategory + "]/li)[" + selectedSubCategory + "]");
                //By subCat = By.xpath("(//div[@class=\"panel-body\"]/ul/li)["+selectedSubCategory+"]");
               Utility.clickingOnElement(driver, subCat);*/

            }


        else {
          //    mainCategory.get(randomCategory).click();
                   LogsUtils.error("error");
        }
        return this;
    }



}
