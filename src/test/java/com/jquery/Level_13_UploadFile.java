package com.jquery;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.jquery.HomePageObject;

import java.util.List;

public class Level_13_UploadFile extends BaseTest {

    @Parameters ({"url","browser"})
    @BeforeClass
    public void beforeClass(String url,String browserName){

        driver = getBrowserDriver(url, browserName);
        homePage = PageGenerator.getPage(HomePageObject.class,driver);
    }

    @Test
    public void Upload_01_Single() {
        homePage.uploadMultipleFiles(driver,colossuemFile);
        homePage.uploadMultipleFiles(driver,roomFile);
        homePage.uploadMultipleFiles(driver,togetherFile);

        Assert.assertTrue(homePage.isFileLoadedSuccess(colossuemFile));
        Assert.assertTrue(homePage.isFileLoadedSuccess(roomFile));
        Assert.assertTrue(homePage.isFileLoadedSuccess(togetherFile));

        homePage.clickStartUpload();

        Assert.assertTrue(homePage.isFileUploadedSuccess(colossuemFile));
        Assert.assertTrue(homePage.isFileUploadedSuccess(roomFile));
        Assert.assertTrue(homePage.isFileUploadedSuccess(togetherFile));
    }

    @Test
    public void Upload_02_Multiple() {
        homePage.refreshPage(driver);
        homePage.uploadMultipleFiles(driver,colossuemFile,roomFile,togetherFile);

        Assert.assertTrue(homePage.isFileLoadedSuccess(colossuemFile));
        Assert.assertTrue(homePage.isFileLoadedSuccess(roomFile));
        Assert.assertTrue(homePage.isFileLoadedSuccess(togetherFile));

        homePage.clickStartUpload();

        Assert.assertTrue(homePage.isFileUploadedSuccess(colossuemFile));
        Assert.assertTrue(homePage.isFileUploadedSuccess(roomFile));
        Assert.assertTrue(homePage.isFileUploadedSuccess(togetherFile));
    }
    @AfterClass
    public void  afterClass(){
        closeBrowser(driver);
    }
    private WebDriver driver;
    private HomePageObject homePage;
    String colossuemFile = "Colosseum.jpg";
    String roomFile = "Room.jpg";
    String togetherFile = "Together.jpg";
}
