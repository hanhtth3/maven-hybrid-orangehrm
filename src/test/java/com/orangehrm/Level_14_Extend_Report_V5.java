package com.orangehrm;

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
import reportConfig.ExtentManager;

import java.lang.reflect.Method;

public class Level_14_Extend_Report_V5 extends BaseTest {
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
        ExtentManager.startTest(method.getName(), "Login_01_CreateNewEmployee");
       ExtentManager.getTest().log(Status.INFO, " Navigate to 'Register' page");

       ExtentManager.getTest().log(Status.INFO," Enter UserName and Password with info:" + adminUser+"|"+adminPassword);
        loginPage.enterToUserNameTextbox(adminUser);
        loginPage.enterToPasswordTextbox(adminPassword);

       ExtentManager.getTest().log(Status.INFO," Navigator to DashboardPage");
        dashboardPage = loginPage.clickToLoginButton();
        Assert.assertTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);

       ExtentManager.getTest().log(Status.INFO," Navigator to Employee Search page");
        employeeListPage = dashboardPage.clickToPIMModule();
        Assert.assertFalse(employeeListPage.isLoadingSpinnerDisappear(driver));
    }
    @Test
    public void Login_02_ViewNewEmployee(Method method){
        ExtentManager.startTest(method.getName(), "Login_02_ViewNewEmployee");
        ExtentManager.getTest().log(Status.INFO," Navigator to Add Employee page");
        addEmployeePage = employeeListPage.clickToAddEmployeeButton();
        Assert.assertTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        ExtentManager.getTest().log(Status.INFO," Enter FirstName and LastName with info: "+employeeFirstName+"|"+employeeLastName);
        addEmployeePage.enterToFirtNameTextbox(employeeFirstName);
        addEmployeePage.enterToLastNameTextbox(employeeLastName);
        employeeID = addEmployeePage.getEmployeeID();
    }
    @Test
    public void Login_03_EditNewEmployee(Method method){
        ExtentManager.startTest(method.getName(), "Login_03_EditNewEmployee");


        ExtentManager.getTest().log(Status.INFO," Navigator Personal Detail");
        personalDetailPage = addEmployeePage.clickSaveButton();
        Assert.assertTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(2);
    }
    @Test
    public void Login_04_DeleteNewEmployee(Method method){
        ExtentManager.startTest(method.getName(), "Login_04_DeleteNewEmployee");
        ExtentManager.getTest().log(Status.INFO,"Verify Employee LastName is display: "+employeeFirstName);
        Assert.assertEquals(personalDetailPage.getFirstNameTextboxValue(),employeeFirstName);

        ExtentManager.getTest().log(Status.INFO," Verify Employee FirstName is display: "+employeeLastName);
        Assert.assertEquals(personalDetailPage.getLastNameTextboxValue(),employeeLastName);

        ExtentManager.getTest().log(Status.INFO," Verify Employee ID is display: "+employeeID);
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
