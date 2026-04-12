package com.orangehrm;

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
import reportConfig.ExtentManager;

import java.lang.reflect.Method;

public class Level_14_Extend_Report extends BaseTest {
    @Parameters ({"appUrl", "browser"})
    @BeforeClass
    public void beforeClass(String appURL,String browserName){
        driver = getBrowserDriver(appURL, browserName);
        loginPage = PageGenerator.getPage(LoginPageObject.class, driver);
        adminUser = "Admin";
        adminPassword = "admin123";
        employeeFirstName ="Brown";
        employeeLastName = "Hana";
    }
   @Test
    public void Login_01_CreateNewEmployee(Method method){
//        ExtentManager.startTest(method.getName(),"Login_01_CreateNewEmployee");
//        ExtentManager.getTest().log(LogStatus.INFO,"Login_01_CreateNewEmployee");
//
//        ExtentManager.getTest().log(LogStatus.INFO,"NewEmployee - STEP 01: Enter UserName and Password with info:" + adminUser+"|"+adminPassword);
//        loginPage.enterToUserNameTextbox(adminUser);
//        loginPage.enterToPasswordTextbox(adminPassword);
//
//        ExtentManager.getTest().log(LogStatus.INFO,"NewEmployee - STEP 02: Navigator to DashboardPage");
//        dashboardPage = loginPage.clickToLoginButton();
//        Assert.assertTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
//        dashboardPage.sleepInSecond(2);
//
//        ExtentManager.getTest().log(LogStatus.INFO,"NewEmployee - STEP 03: Navigator to Employee Search page");
//        employeeListPage = dashboardPage.clickToPIMModule();
//        Assert.assertFalse(employeeListPage.isLoadingSpinnerDisappear(driver));
//
//        ExtentManager.getTest().log(LogStatus.INFO,"NewEmployee - STEP 04: Navigator to Add Employee page");
//        addEmployeePage = employeeListPage.clickToAddEmployeeButton();
//        Assert.assertTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));
//
//        ExtentManager.getTest().log(LogStatus.INFO,"NewEmployee - STEP 05: Enter FirstName and LastName with info: "+employeeFirstName+"|"+employeeLastName);
//        addEmployeePage.enterToFirtNameTextbox(employeeFirstName);
//        addEmployeePage.enterToLastNameTextbox(employeeLastName);
//        employeeID = addEmployeePage.getEmployeeID();
//
//        ExtentManager.getTest().log(LogStatus.INFO,"NewEmployee - STEP 06: Navigator Personal Detail");
//        personalDetailPage = addEmployeePage.clickSaveButton();
//        Assert.assertTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
//        personalDetailPage.sleepInSecond(2);
//
//        ExtentManager.getTest().log(LogStatus.INFO,"NewEmployee - STEP 07: Verify Employee LastName is display: "+employeeFirstName);
//       Assert.assertEquals(personalDetailPage.getFirstNameTextboxValue(),employeeLastName);
//
//        ExtentManager.getTest().log(LogStatus.INFO,"NewEmployee - STEP 08: Verify Employee FirstName is display: "+employeeLastName);
//       Assert.assertEquals(personalDetailPage.getLastNameTextboxValue(),employeeFirstName);
//
//        ExtentManager.getTest().log(LogStatus.INFO,"NewEmployee - STEP 09: Verify Employee ID is display: "+employeeID);
//        Assert.assertEquals(personalDetailPage.getEmployeeIDTextboxValue(),employeeID);
//
//        ExtentManager.endTest();
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
