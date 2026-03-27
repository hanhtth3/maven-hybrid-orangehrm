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

public class Level_10_Multiple_Driver extends BaseTest {

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

        userDriver = getBrowserDriver(userURL, browserName);
        userHomePage = PageGenerator.getPage(UserHomePO.class,userDriver);

        adminDriver = getBrowserDriver(adminURL, browserName);
        adminLoginPage = PageGenerator.getPage(AdminLoginPO.class, adminDriver);
    }

    @Test
    public void Opencart_02_Login_Without_Logout(){
        userHomePage.clickToMyaccuntAtFooter();
        userLoginPage = PageGenerator.getPage(UserLoginPO.class,userDriver);

        userRegisterPage = userLoginPage.clickToContinueButton();

        userRegisterPage.enterToFirstName(userFirstName);
        userRegisterPage.enterToLastName(userLastName);
        userRegisterPage.enterToEmailAdress(userEmailAdress);
        userRegisterPage.enterToPassword(userPassword);

        userRegisterPage.acceptPrivacyCheckbox();
        userRegisterPage.clickContinueButton();

        Assert.assertTrue(userRegisterPage.isSuccesMesageDisplay());

        adminLoginPage.enterToUsername(adminUser);
        adminLoginPage.enterToPassword(adminPassword);
        adminDashboardPage = adminLoginPage.clickLoginToButton();

        Assert.assertTrue(adminDashboardPage.isDashboardPageDisplay());

        adminCustomerPage = adminDashboardPage.openCustomerPage();

        userHomePage.clickToMyaccuntAtFooter();

        userMyAccountPO = PageGenerator.getPage(UserMyAccountPO.class,userDriver);

        Assert.assertTrue(userMyAccountPO.isMyAccountPageDislpayed());

        userMyAccountPO.openAdminSite(adminDriver,adminURL);
        adminDashboardPage = PageGenerator.getPage(AdminDashboardPO.class,adminDriver);

        Assert.assertTrue(adminDashboardPage.isDashboardPageDisplay());

    }

    @Test
    public void Opencart_03_Multiple_Tab(){
        userHomePage.clickToMyaccuntAtFooter();
        userLoginPage = PageGenerator.getPage(UserLoginPO.class,userDriver);

        userRegisterPage = userLoginPage.clickToContinueButton();

        userRegisterPage.enterToFirstName(userFirstName);
        userRegisterPage.enterToLastName(userLastName);
        userRegisterPage.enterToEmailAdress(userEmailAdress);
        userRegisterPage.enterToPassword(userPassword);

        userRegisterPage.acceptPrivacyCheckbox();
        userRegisterPage.clickContinueButton();

        Assert.assertTrue(userRegisterPage.isSuccesMesageDisplay());

        userRegisterPage.openUrlByNewTab(adminDriver,adminURL);
        userWindowID = userRegisterPage.getCurrentWindowID(userDriver);

        userHomePage.openAdminSite(adminDriver,adminURL);
        adminLoginPage = PageGenerator.getPage(AdminLoginPO.class,adminDriver);

        adminLoginPage.enterToUsername(adminUser);
        adminLoginPage.enterToPassword(adminPassword);
        adminDashboardPage = adminLoginPage.clickLoginToButton();

        adminCustomerPage = adminDashboardPage.openCustomerPage();
        adminCustomerPage.switchToWindowByID(adminDriver,adminWindowID);

        userRegisterPage = PageGenerator.getPage(UserRegisterPO.class,userDriver);

        userHomePage = userRegisterPage.openHomeLogo(userDriver);

        userHomePage.clickToMyaccuntAtFooter();
        userMyAccountPO = PageGenerator.getPage(UserMyAccountPO.class,userDriver);

        Assert.assertTrue(userMyAccountPO.isMyAccountPageDislpayed());

        userMyAccountPO.switchToWindowByID(userDriver,userURL);
        adminCustomerPage = PageGenerator.getPage(AdminCustomerPO.class,adminDriver);

        Assert.assertTrue(adminCustomerPage.isCustomerPageDisplay());
    }
    @AfterClass
    public void  afterClass(){
        closeBrowser();
    }
    private WebDriver userDriver, adminDriver;
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
