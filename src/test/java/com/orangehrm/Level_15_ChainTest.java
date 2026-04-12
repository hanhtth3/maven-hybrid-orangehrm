package com.orangehrm;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.aventstack.extentreports.Status;
import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.orangeHRM.AddEmployeePageObject;
import pageObjects.orangeHRM.DashboardPageObject;
import pageObjects.orangeHRM.EditNavigation.ContactDetailPageObject;
import pageObjects.orangeHRM.EditNavigation.DependentsPageObject;
import pageObjects.orangeHRM.EditNavigation.JobPageObject;
import pageObjects.orangeHRM.EditNavigation.PersonalDetailPageObject;
import pageObjects.orangeHRM.EmployeeListPageObject;
import pageObjects.orangeHRM.LoginPageObject;

public class Level_15_ChainTest extends BaseTest {
    private String browserName;

    @Parameters ({"appUrl", "browser"})
    @BeforeClass
    public void beforeClass(String appURL,String browserName){
        this.browserName = browserName.toUpperCase();
        driver = getBrowserDriver(appURL, browserName);
        loginPage = PageGenerator.getPage(LoginPageObject.class, driver);
        adminUser = "Admin";
        adminPassword = "admin123";
        employeeFirstName ="Brown";
        employeeLastName = "Hana";
    }
   @Test
    public void Login_01_CreateNewEmployee( ){
       ChainTestListener.log(" Run on browser - " + browserName);
       ChainTestListener.log(" Navigate to 'Register' page");

       ChainTestListener.log(" Enter UserName and Password with info:" + adminUser+"|"+adminPassword);
       loginPage.enterToUserNameTextbox(adminUser);
       loginPage.enterToPasswordTextbox(adminPassword);

       ChainTestListener.log(" Navigator to DashboardPage");
       dashboardPage = loginPage.clickToLoginButton();
       Assert.assertTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
       dashboardPage.sleepInSecond(2);

       ChainTestListener.log(" Navigator to Employee Search page");
       employeeListPage = dashboardPage.clickToPIMModule();
       Assert.assertFalse(employeeListPage.isLoadingSpinnerDisappear(driver));
    }
    @Test
    public void Login_02_ViewNewEmployee( ){
        ChainTestListener.log(" Navigator to Add Employee page");
        addEmployeePage = employeeListPage.clickToAddEmployeeButton();
        Assert.assertTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        ChainTestListener.log(" Enter FirstName and LastName with info: "+employeeFirstName+"|"+employeeLastName);
        addEmployeePage.enterToFirtNameTextbox(employeeFirstName);
        addEmployeePage.enterToLastNameTextbox(employeeLastName);
        employeeID = addEmployeePage.getEmployeeID();
    }
    @Test
    public void Login_03_EditNewEmployee( ){

        ChainTestListener.log(" Navigator Personal Detail");
        personalDetailPage = addEmployeePage.clickSaveButton();
        Assert.assertTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(2);
    }
    @Test
    public void Login_04_DeleteNewEmployee( ){
        ChainTestListener.log("Verify Employee LastName is display: "+employeeFirstName);
        Assert.assertEquals(personalDetailPage.getFirstNameTextboxValue(),employeeFirstName);

        ChainTestListener.log(" Verify Employee FirstName is display: "+employeeLastName);
        Assert.assertEquals(personalDetailPage.getLastNameTextboxValue(),employeeLastName);

        ChainTestListener.log(" Verify Employee ID is display: "+employeeID);
        Assert.assertEquals(personalDetailPage.getEmployeeIDTextboxValue(),employeeLastName);
    }

    @AfterClass
    public void  afterClass(){
        closeBrowser();
    }
    private WebDriver driver;
    private AddEmployeePageObject addEmployeePage;
    private DashboardPageObject dashboardPage;
    private EmployeeListPageObject employeeListPage;
    private LoginPageObject loginPage;
    private PersonalDetailPageObject personalDetailPage;
    private ContactDetailPageObject contactDetailPage;
    private JobPageObject jobPage;
    private DependentsPageObject dependentsPage;
    private String employeeID,adminUser, adminPassword, employeeFirstName, employeeLastName; 
}
