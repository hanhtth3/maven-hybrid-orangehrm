package com.techpanda.share;

import core.BaseTest;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.techpanda.HomePO;
import pageObjects.techpanda.LoginPO;
import pageObjects.techpanda.MyAccountPO;
import pageObjects.techpanda.RegisterPO;

import java.util.Set;

public class Register extends BaseTest {
    @Parameters({"appUrl", "browser"})
    @BeforeClass
    public void beforeClass(String appURL,String browserName){
        driver = getBrowserDriver(appURL, browserName);

        homePage = PageGenerator.getPage(HomePO.class, driver);

        loginPage = homePage.openLoginPage()
        registerPage = loginPage.clickCreatAnAccountLink();

        registerPage.enterToFirstName("Automation");
        registerPage.enterToLastName("FC");
        registerPage.enterToEmail("hanh"+getRandomNumber()+"gmail.com");
        registerPage.enterToPassword("123456");
        registerPage.enterToConfirmPassword("123456");
        registerPage.clickToRegisterButton();

        myAccountPage = registerPage.clickToContinueAlert();
        verifyEquals(myAccountPage.getSuccessMsg(),"Thank you registering with Main Website Store.")

        cookies = myAccountPage.getPageCookies(driver);

    closeBrowser();
    }
    private WebDriver driver;
    private HomePO homePage;
    private LoginPO  loginPage;

    private RegisterPO registerPage;
    private MyAccountPO myAccountPage;
    public static Set<Cookie> cookies;

}
