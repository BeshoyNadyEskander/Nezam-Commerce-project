package Pages;

import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class P02_signupPage {
    private final WebDriver driver;

    private final static By NewUserSignUp = By.xpath("//div[@class=\"signup-form\"] /h2");
    private final static By nameLocator = By.cssSelector("input[data-qa=\"signup-name\"]");
    private final static By EmailLocator = By.cssSelector("form[action=\"/signup\"] [type=\"email\"]");
    private final static By signupButton = By.cssSelector("form[action=\"/signup\"] [type=\"submit\"]");
    private final static By accountInformationLocator = By.xpath("//h2[.=\"Enter Account Information\"]");
    private final static By genderLocator = By.cssSelector("input[value=\"Mr\"]");
    private final static By passwordLocator = By.id("password");
    private final static By daysLocator = By.id("days");
    private final static By monthLocator = By.id("months");
    private final static By yearLocator = By.id("years");
    private final static By checkBoxSignUpNewestLocator = By.id("newsletter");
    private final static By checkBoxSReceivespecialoffersLocator = By.id("optin");
    private final static By firstNameLocator = By.id("first_name");
    private final static By lastNameLocator = By.id("last_name");
    private final static By companyLocator = By.id("company");
    private final static By firstAddressLocator = By.id("address1");
    private final static By secondAddressLocator = By.id("address2");
    private final static By countryLocator = By.id("country");
    private final static By cityLocator = By.id("city");
    private final static By zipCodeLocator = By.id("zipcode");
    private final static By mobileNumberLocator = By.id("mobile_number");
    private final static By stateLocator = By.id("state");
    private final static By createAccountButton = By.cssSelector("button[data-qa=\"create-account\"]");

    public P02_signupPage(WebDriver driver) {
        this.driver =driver;
    }


    public String getNewUserSignUP()
    {
        LogsUtils.info("new user sign up exist");
        return Utility.getText(driver ,NewUserSignUp);
    }

    public boolean checkOnTextOfNewUserSignUp(String expectedValue)
    {
        return getNewUserSignUP().equals(expectedValue);
    }

    public P02_signupPage enterName(String name)
    {
        Utility.sendData(driver ,nameLocator ,name );
        return this;
    }
    public P02_signupPage enterEmail(String email) {
        Utility.sendData(driver, EmailLocator, email);
        return this;
    }
    public P02_signupPage clickOnSignUpButton()
    {
        Utility.clickingOnElement(driver , signupButton);
        return this;
    }

    public String getAccountInformationTitle()
    {
        Utility.generalWait(driver)
                .until(ExpectedConditions.visibilityOfElementLocated(accountInformationLocator));
        return Utility.getText(driver ,accountInformationLocator);
    }
    public  boolean checkOnTitleIsDisplayed()
    {
      return   Utility.verifyOnButtonIsDisplayed(driver , accountInformationLocator);
    }

    public P02_signupPage clickOnGender()
    {
        Utility.clickingOnElement(driver , genderLocator);
        return this;
    }

    public P02_signupPage enterPassword(String password)
    {
        Utility.sendData(driver , passwordLocator ,password);
        return this;
    }

    public P02_signupPage enterBirthOfDate(String optionDay,String optionMonth,String optionYear)
    {
        Utility.selectingFromDropDown(driver ,daysLocator ,optionDay);
        Utility.selectingFromDropDown(driver,monthLocator,optionMonth);
        Utility.selectingFromDropDown(driver,yearLocator,optionYear);
        LogsUtils.info("Date of Birth is: " +optionDay + "-" + optionMonth +"-" + optionYear);
        return this;
    }
    public P02_signupPage selectedCheckBoxs()
    {
        driver.findElement(checkBoxSignUpNewestLocator).isSelected();
        driver.findElement(checkBoxSReceivespecialoffersLocator).isSelected();
        LogsUtils.info("checkboxes are selected");
        return this;
    }
    public P02_signupPage enterFirstName(String fName)
    {
        Utility.sendData(driver ,firstNameLocator ,fName);
        LogsUtils.info("first name: " + fName);
        return this;
    }
    public P02_signupPage enterLastName(String lName)
    {
        Utility.sendData(driver ,lastNameLocator ,lName);
        LogsUtils.info("last name: " + lName);
        return this;
    }

    public P02_signupPage enterCompany(String company)
    {
        Utility.sendData(driver ,companyLocator ,company);
        LogsUtils.info("company name: " + company);
        return this;
    }
    public P02_signupPage enterAddress(String firstAddress , String secondAddress)
    {
        Utility.sendData(driver ,firstAddressLocator ,firstAddress);
        Utility.sendData(driver ,secondAddressLocator ,secondAddress);
        LogsUtils.info("first address: " + firstAddress + " -second address: " + secondAddress);
        return this;
    }
    public P02_signupPage selectCountry(String optin)
    {
        Utility.selectingFromDropDown(driver , countryLocator , optin);
        LogsUtils.info("country: " +optin);
        return this;
    }

    public P02_signupPage enterCity(String city)
    {
        Utility.sendData(driver , cityLocator , city);
        LogsUtils.info("city: " +city);
        return this;
    }
    public P02_signupPage enterzipCode(String zipCode)
    {
        Utility.sendData(driver , zipCodeLocator , zipCode);
        LogsUtils.info("zipcode: " + zipCode);
        return this;
    }
    public P02_signupPage enterPhoneNumber(String Phone)
    {
        Utility.sendData(driver , mobileNumberLocator , Phone);
        LogsUtils.info("phone: " + Phone);
        return this;
    }
    public P02_signupPage enterState(String state)
    {
        Utility.sendData(driver , stateLocator , state);
        LogsUtils.info("state: " + state);
        return this;
    }

    public P03_createdOrDeleteAccountPage clickONCreateAccount()
    {

        Utility.clickingOnElement(driver ,createAccountButton);
        return new P03_createdOrDeleteAccountPage(driver);
    }










}
