package com.opencart;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.openCart.admin.AdminCustomerPO;
import pageObjects.openCart.admin.AdminDashboardPO;
import pageObjects.openCart.admin.AdminLoginPO;
import pageObjects.openCart.user.UserHomePO;
import pageObjects.openCart.user.UserLoginPO;
import pageObjects.openCart.user.UserMyAccountPO;
import pageObjects.openCart.user.UserRegisterPO;

public class Level_09_Switch_Url_Role extends BaseTest {

    @Parameters ({"userUrl","adminUrl","browser"})
    @BeforeClass
    public void beforeClass(String userURL,String adminURL,String browserName){
        this.userURL = userURL;
        this.adminURL = userURL;

        adminUser = "automationfc";
        adminPassword = "Abc@123#$%";
        userFirstName ="Jonh";
        userLastName = "Terry";
        userEmailAdress = "jonh.terry" +getRandomNumber()+"@gmail.com";
        userPassword = "Auto111@@@";

        driver = getBrowserDriver(userURL, browserName);
        userHomePage = PageGenerator.getPage(UserHomePO.class,driver);
    }
   @Test
    public void Opencart_01_Login_And_Logout(){
    userHomePage.clickToMyaccuntAtFooter();
    userLoginPage = PageGenerator.getPage(UserLoginPO.class,driver);

    userRegisterPage = userLoginPage.clickToContinueButton();
    userRegisterPage.enterToFirstName(userFirstName);
    userRegisterPage.enterToLastName(userLastName);
    userRegisterPage.enterToEmailAdress(userEmailAdress);
    userRegisterPage.enterToPassword(userPassword);

    userRegisterPage.acceptPrivacyCheckbox();
    userRegisterPage.clickContinueButton();

    Assert.assertTrue(userRegisterPage.isSuccesMesageDisplay());

    userRegisterPage.clickToLogoutLinkAtUserSite(driver);
    userHomePage = userRegisterPage.clickContinueButton();

    userHomePage.openAdminSite(driver,adminURL);
    adminLoginPage = PageGenerator.getPage(AdminLoginPO.class,driver);

    adminLoginPage.enterToUsername(adminUser);
    adminLoginPage.enterToPassword(adminPassword);
    adminDashboardPage = adminLoginPage.clickLoginToButton();

    adminCustomerPage = adminDashboardPage.openCustomerPage();
    adminLoginPage = adminCustomerPage.clickToLogoutLinkAtAdminSite(driver);

    userHomePage = adminCustomerPage.openUserSite(driver,userURL);

     userHomePage.clickToMyaccuntAtFooter();
     userLoginPage = PageGenerator.getPage(UserLoginPO.class,driver);

    userLoginPage.enterToEmailAdressTextbox(userEmailAdress);
    userLoginPage.enterToPasswordTextbox(userPassword);
    userMyAccountPO = userLoginPage.clickLoginToButton();

    Assert.assertTrue(userMyAccountPO.isMyAccountPageDislpayed());

    userMyAccountPO.openAdminSite(driver,adminURL);
    adminLoginPage = PageGenerator.getPage(AdminLoginPO.class,driver);
    }
    @Test
    public void Opencart_02_Login_Without_Logout(){
        userHomePage.clickToMyaccuntAtFooter();
        userLoginPage = PageGenerator.getPage(UserLoginPO.class,driver);

        userRegisterPage = userLoginPage.clickToContinueButton();

        userRegisterPage.enterToFirstName(userFirstName);
        userRegisterPage.enterToLastName(userLastName);
        userRegisterPage.enterToEmailAdress(userEmailAdress);
        userRegisterPage.enterToPassword(userPassword);

        userRegisterPage.acceptPrivacyCheckbox();
        userRegisterPage.clickContinueButton();

        Assert.assertTrue(userRegisterPage.isSuccesMesageDisplay());

        userHomePage.openAdminSite(driver,adminURL);
        adminLoginPage = PageGenerator.getPage(AdminLoginPO.class,driver);

        adminLoginPage.enterToUsername(adminUser);
        adminLoginPage.enterToPassword(adminPassword);
        adminDashboardPage = adminLoginPage.clickLoginToButton();

        adminCustomerPage = adminDashboardPage.openCustomerPage();

        userHomePage = adminCustomerPage.openUserSite(driver,userURL);

        adminLoginPage = userMyAccountPO.openAdminSite(driver,adminURL);
        userMyAccountPO = PageGenerator.getPage(UserMyAccountPO.class,driver);

        Assert.assertTrue(userMyAccountPO.isMyAccountPageDislpayed());

        userMyAccountPO.openAdminSite(driver,adminURL);
        adminDashboardPage = PageGenerator.getPage(AdminDashboardPO.class,driver);

        Assert.assertTrue(adminDashboardPage.isDashboardPageDisplay());

    }

    @Test
    public void Opencart_03_Multiple_Tab(){
        userHomePage.clickToMyaccuntAtFooter();
        userLoginPage = PageGenerator.getPage(UserLoginPO.class,driver);

        userRegisterPage = userLoginPage.clickToContinueButton();

        userRegisterPage.enterToFirstName(userFirstName);
        userRegisterPage.enterToLastName(userLastName);
        userRegisterPage.enterToEmailAdress(userEmailAdress);
        userRegisterPage.enterToPassword(userPassword);

        userRegisterPage.acceptPrivacyCheckbox();
        userRegisterPage.clickContinueButton();

        Assert.assertTrue(userRegisterPage.isSuccesMesageDisplay());

        userRegisterPage.openUrlByNewTab(driver,adminURL);
        userWindowID = userRegisterPage.getCurrentWindowID(driver);

        userHomePage.openAdminSite(driver,adminURL);
        adminLoginPage = PageGenerator.getPage(AdminLoginPO.class,driver);

        adminLoginPage.enterToUsername(adminUser);
        adminLoginPage.enterToPassword(adminPassword);
        adminDashboardPage = adminLoginPage.clickLoginToButton();

        adminCustomerPage = adminDashboardPage.openCustomerPage();
        adminCustomerPage.switchToWindowByID(driver,adminWindowID);

        userRegisterPage = PageGenerator.getPage(UserRegisterPO.class,driver);

        userHomePage = userRegisterPage.openHomeLogo(driver);

        userHomePage.clickToMyaccuntAtFooter();
        userMyAccountPO = PageGenerator.getPage(UserMyAccountPO.class,driver);

        Assert.assertTrue(userMyAccountPO.isMyAccountPageDislpayed());

        userMyAccountPO.switchToWindowByID(driver,userURL);
        adminCustomerPage = PageGenerator.getPage(AdminCustomerPO.class,driver);

        Assert.assertTrue(adminCustomerPage.isCustomerPageDisplay());
    }
    @AfterClass
    public void  afterClass(){
        closeBrowser();
    }
    private WebDriver driver;
    private AdminLoginPO adminLoginPage;
    private AdminDashboardPO adminDashboardPage;
    private AdminCustomerPO adminCustomerPage;
    private UserHomePO userHomePage;
    private UserLoginPO userLoginPage;
    private UserRegisterPO userRegisterPage;
    private UserMyAccountPO userMyAccountPO;
    private String adminUser, adminPassword;
    private String userURL, adminURL, userWindowID,adminWindowID;
    private String userFirstName, userLastName,userEmailAdress, userPassword;
}
