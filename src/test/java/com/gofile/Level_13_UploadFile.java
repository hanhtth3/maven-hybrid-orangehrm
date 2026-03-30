package com.gofile;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.gofile.HomePageObject;

public class Level_13_UploadFile extends BaseTest {

    @Parameters ({"url","browser"})
    @BeforeClass
    public void beforeClass(String url,String browserName){

        driver = getBrowserDriver(url, browserName);
        homePage = PageGenerator.getPage(HomePageObject.class,driver);
    }

    @Test
    public void Upload_01_Multiple() {
        Assert.assertTrue(homePage.isLoadingIconDisappear());

        homePage.uploadMultipleFiles(driver,colossuemFile,roomFile,togetherFile);

        Assert.assertTrue(homePage.isProgessBarIconDisappear());

        String successUrl = homePage.getSuccessLink();

        homePage.openPageUrl(driver,successUrl);

        Assert.assertTrue(homePage.isLoadingIconDisappear());
        homePage.sleepInSecond(2);

        Assert.assertTrue(homePage.isfileUploadedSuccess(colossuemFile));
        Assert.assertTrue(homePage.isfileUploadedSuccess(roomFile));
        Assert.assertTrue(homePage.isfileUploadedSuccess(togetherFile));
    }
    @AfterClass
    public void  afterClass(){
        closeBrowser(driver);
    }
    private WebDriver driver;
    private pageObjects.gofile.HomePageObject homePage;
    String colossuemFile = "Colosseum.jpg";
    String roomFile = "Room.jpg";
    String togetherFile = "Together.jpg";
}
