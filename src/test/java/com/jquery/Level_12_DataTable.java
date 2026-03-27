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
import pageObjects.openCart.admin.AdminCustomerPO;
import pageObjects.openCart.admin.AdminDashboardPO;
import pageObjects.openCart.admin.AdminLoginPO;
import pageObjects.openCart.user.UserHomePO;
import pageObjects.openCart.user.UserLoginPO;
import pageObjects.openCart.user.UserMyAccountPO;
import pageObjects.openCart.user.UserRegisterPO;

import java.util.List;

public class Level_12_DataTable extends BaseTest {

    @Parameters ({"url","browser"})
    @BeforeClass
    public void beforeClass(String url,String browserName){

        driver = getBrowserDriver(url, browserName);
        homePage = PageGenerator.getPage(HomePageObject.class,driver);
    }

    @Test(enabled = false)
    public void Table_01_Paging() {
        //1 - Mở 1 trang bất kì dựa vào số trang
        homePage.openPageByNumber("3");
        Assert.assertTrue(homePage.isPageActiveByNumber("3"));

        homePage.openPageByNumber("10");
        Assert.assertTrue(homePage.isPageActiveByNumber("10"));

        homePage.openPageByNumber("21");
        Assert.assertTrue(homePage.isPageActiveByNumber("21"));
    }

    @Test (enabled = false)
    public void Table_02_Search() {
    //2 - Search tại một header textbox bất kì dựa vào tên cột
    //3- Verify thong tin hien thi
        homePage.enterToHeaderTextboxByName("Country","Afghanistan");
        homePage.sleepInSecond(3);
        Assert.assertTrue(homePage.IsPageInfoDisplay("384187","Afghanistan","407124","791312"));
        homePage.refreshPage(driver);


        homePage.enterToHeaderTextboxByName("Females","276880");
        homePage.sleepInSecond(3);
        Assert.assertTrue(homePage.IsPageInfoDisplay("276880","Angola","276472","553353"));
        homePage.refreshPage(driver);


        homePage.enterToHeaderTextboxByName("Males","25266");
        homePage.sleepInSecond(3);
        Assert.assertTrue(homePage.IsPageInfoDisplay("24128","Albania","25266","49397"));
        homePage.refreshPage(driver);
    }
    @Test(enabled = false)
    public void Table_03_Action() {
        homePage.enterToHeaderTextboxByName("Country","Aruba");
        homePage.sleepInSecond(3);
        //4- Xoa/edit mot dong tren table
        homePage.clickToActionByCountryName("Aruba","remove");
        homePage.refreshPage(driver);

        homePage.enterToHeaderTextboxByName("Country","Aruba");
        homePage.sleepInSecond(3);
        //4- Xoa/edit mot dong tren table
        homePage.clickToActionByCountryName("Aruba","edit");
        homePage.refreshPage(driver);
    }

    @Test (enabled = false)
    public void Table_04_Index() {
        homePage.openPageUrl(driver,"https://www.jqueryscript.net/demo/jQuery-Dynamic-Data-Grid-Plugin-appendGrid/");
        homePage.clickLoadDataButton();
        homePage.sleepInSecond(3);

        homePage.enterToTextboxByColumNameAndRowIndex("Company","3","Viet Nam");
        homePage.enterToTextboxByColumNameAndRowIndex("Contact Person","3","Nam");
        homePage.enterToTextboxByColumNameAndRowIndex("Order Placed","3","10");
        homePage.selectToDropdownByColumNameAndRowIndex("Country","3","Hong Kong");
        homePage.checkToCheckboxByColumNameAndRowIndex("NPO?","3");
        homePage.actionToRowByRowIndex("3","Move Up");
        homePage.sleepInSecond(3);

        homePage.enterToTextboxByColumNameAndRowIndex("Company","6","USA");
        homePage.enterToTextboxByColumNameAndRowIndex("Contact Person","6","Kelly");
        homePage.enterToTextboxByColumNameAndRowIndex("Order Placed","6","8");
        homePage.selectToDropdownByColumNameAndRowIndex("Country","6","Japan");
        homePage.checkToCheckboxByColumNameAndRowIndex("NPO?","6");
        homePage.actionToRowByRowIndex("6","Insert");
        homePage.sleepInSecond(3);
    }
    @Test
    public void Table_05_Get_All_Value() {
        List<String> countryActualValue = homePage.getColumAllValueByColumName("Country");
        System.out.println(countryActualValue.size());

    }
    @AfterClass
    public void  afterClass(){
        closeBrowser(driver);
    }
    private WebDriver driver;
    private HomePageObject homePage;
}
