package com.opencart;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.openCart.admin.AdminCustomerPO;
import pageObjects.openCart.admin.AdminDashboardPO;
import pageObjects.openCart.admin.AdminLoginPO;
import pageObjects.openCart.user.UserHomePO;
import pageObjects.openCart.user.UserLoginPO;
import pageObjects.openCart.user.UserRegisterPO;

public class Level_09_Switch_Url extends BaseTest {
    private String userURL, adminURL;
    @Parameters ({"userUrl","adminUrl","browser"})
    @BeforeClass
    public void beforeClass(String userURL,String adminURL,String browserName){
        this.userURL = userURL;
        this.adminURL - userURL
        driver = getBrowserDriver(userURL, browserName);

    }
   @Test
    public void Opencart_01(){
    userRegisterPage.enterToFirstName("");
    userRegisterPage.enterToLastName("");
    userRegisterPage.enterToEmailAdress("");
    userRegisterPage.enterToLastName("");
    userRegisterPage.enterToPassword("");
    userRegisterPage.acceptPrivacyCheckboc();
    userRegisterPage.clickContinueButton();

    userRegisterPage.clickToLogoutLinkAtUserSite(driver);
    userHomePage = userRegisterPage.clickContinueButton();

    adminLoginPage = userHomePage.OpenAdminSite(driver,adminURL);
    adminLoginPage.enterToUsername("");
    adminLoginPage.enterToPassword("");
    adminDashboardPage = adminLoginPage.clickLoginToButton();

    adminCustomerPage = adminLoginPage.openCustomerPage(driver,adminURL);
    adminLoginPage = adminCustomerPage.clickToLogoutLinkAtAdminSite(driver);

    userHomePage = adminCustomerPage.openUserSite(driver,userURL);

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
    private AdminCustomerPO adminCustomerPage
    private UserHomePO userHomePage;
    private UserLoginPO userLoginPage;
    private UserRegisterPO userRegisterPage;
}
