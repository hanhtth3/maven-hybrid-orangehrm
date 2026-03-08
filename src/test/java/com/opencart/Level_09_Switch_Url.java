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

public class Level_09_Switch_Url extends BaseTest {

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
    public void Opencart_01(){
    userLoginPage = userHomePage.clickToMyaccunt();
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

    adminLoginPage = userHomePage.openAdminSite(driver,adminURL);
    adminLoginPage.enterToUsername(adminUser);
    adminLoginPage.enterToPassword(adminPassword);
    adminDashboardPage = adminLoginPage.clickLoginToButton();

    adminCustomerPage = adminDashboardPage.openCustomerPage();
    adminLoginPage = adminCustomerPage.clickToLogoutLinkAtAdminSite(driver);

    userHomePage = adminCustomerPage.openUserSite(driver,userURL);

    userLoginPage = userHomePage.clickToMyaccunt();

    userLoginPage.enterToEmailAdressTextbox(userEmailAdress);
    userLoginPage.enterToPasswordTextbox(userPassword);
    userMyAccountPO = userLoginPage.clickLoginToButton();

    Assert.assertTrue(userMyAccountPO.isMyAccountPageDislpayed());

    adminLoginPage = userMyAccountPO.openAdminSite(driver,adminURL);
    }
    @Test
    public void Opencart_02(){


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
    private String userURL, adminURL;
    private String userFirstName, userLastName,userEmailAdress, userPassword;
}
