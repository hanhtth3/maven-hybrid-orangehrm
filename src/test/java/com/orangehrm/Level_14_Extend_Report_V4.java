package com.orangehrm;

//import com.relevantcodes.extentreports.LogStatus;
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

public class Level_14_Extend_Report_V4 extends BaseTest {
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
    public void Login_01_CreateNewEmployee(){

        loginPage.enterToUserNameTextbox(adminUser);
        loginPage.enterToPasswordTextbox(adminPassword);

        dashboardPage = loginPage.clickToLoginButton();
        Assert.assertTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);

        employeeListPage = dashboardPage.clickToPIMModule();
        Assert.assertFalse(employeeListPage.isLoadingSpinnerDisappear(driver));
    }
    @Test
    public void Login_02_ViewNewEmployee(){

        addEmployeePage = employeeListPage.clickToAddEmployeeButton();
        Assert.assertTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        addEmployeePage.enterToFirtNameTextbox(employeeFirstName);
        addEmployeePage.enterToLastNameTextbox(employeeLastName);
        employeeID = addEmployeePage.getEmployeeID();
    }
    @Test
    public void Login_03_EditNewEmployee(){

        personalDetailPage = addEmployeePage.clickSaveButton();
        Assert.assertTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(2);
    }
    @Test
    public void Login_04_DeleteNewEmployee(){

        Assert.assertEquals(personalDetailPage.getFirstNameTextboxValue(),employeeFirstName);

        Assert.assertEquals(personalDetailPage.getLastNameTextboxValue(),employeeLastName);

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
