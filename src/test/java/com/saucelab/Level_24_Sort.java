package com.saucelab;

import core.BaseTest;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.saucelab.LoginPO;
import pageObjects.saucelab.ProductsPO;

public class Level_24_Sort extends BaseTest {
    @Parameters({"appUrl", "browser"})
    @BeforeClass
    public void beforeClass(String appURL, String browserName) {
        driver = getBrowserDriver(appURL, browserName);
        loginPage = PageGenerator.getPage(LoginPO.class, driver);

        productsPage = loginPage.loginSauce("standard_user","secret_sauce");

    }

    @Test
    public void Sort_01_Name() {
        productsPage.sortBy("Name (A to Z)");
        verifyEquals(productsPage.getSelectedSortCriteria(), "Name (A to Z)");
        verifyTrue(productsPage.isProductNameSortByAsscending());

        productsPage.sortBy("Name (Z to A)");
        verifyEquals(productsPage.getSelectedSortCriteria(), "Name (Z to A)");
        verifyTrue(productsPage.isProductNameSortByDescending());
    }

    @Test

    public void Sort_02_Price() {
        productsPage.sortBy("Price (low to high)");
        verifyEquals(productsPage.getSelectedSortCriteria(), "Price (low to high)");
        verifyTrue(productsPage.isProducPriceSortByAsscending());

        productsPage.sortBy("Price (high to low)");
        verifyEquals(productsPage.getSelectedSortCriteria(), "Price (high to low)");

        verifyTrue(productsPage.isProductPriceSortByDescending());

    }


    @AfterClass (alwaysRun = true)
    public void afterClass() {
        closeBrowser(driver);
    }

    private WebDriver driver;
    private LoginPO loginPage;
    private ProductsPO productsPage;
}
