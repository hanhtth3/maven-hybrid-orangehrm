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

public class Level_11_ByLocator extends BaseTest {
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

        Assert.assertTrue(employeeListPage.isLoadingSpinnerDisappear(driver));
        addEmployeePage = employeeListPage.clickToAddEmployeeButton();

        Assert.assertTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));
        addEmployeePage.enterToFirtNameTextbox(employeeFirstName);
        addEmployeePage.enterToLastNameTextbox(employeeLastName);
        employeeID = addEmployeePage.getEmployeeID();
        personalDetailPage = addEmployeePage.clickSaveButton();
      
        Assert.assertTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(2);

        Assert.assertEquals(personalDetailPage.getFirstNameTextboxValue(),employeeFirstName);
        Assert.assertEquals(personalDetailPage.getLastNameTextboxValue(),employeeLastName);
        Assert.assertEquals(personalDetailPage.getEmployeeIDTextboxValue(),employeeID);
    }
    @Test
    public void Employee_02_Dynammic_Locator(){

        contactDetailPage = (ContactDetailPageObject) personalDetailPage.openEditNavigatorByPageName("Contact Details");

        jobPage = (JobPageObject) contactDetailPage.openEditNavigatorByPageName("Job");

        dependentsPage = (DependentsPageObject) jobPage.openEditNavigatorByPageName("Dependents");

        personalDetailPage = (PersonalDetailPageObject) dependentsPage.openEditNavigatorByPageName("Personal Details");

        jobPage = (JobPageObject) personalDetailPage.openEditNavigatorByPageName("Job");
    }

    @Test
    public void Employee_03_Dynamic_Locator(){

        personalDetailPage.openEditNavigatorByName("Contact Details");
        contactDetailPage = PageGenerator.getPage(ContactDetailPageObject.class,driver);

        contactDetailPage.openEditNavigatorByName("Job");
        jobPage = PageGenerator.getPage(JobPageObject.class,driver);

        jobPage.openEditNavigatorByName("Dependents");
        dependentsPage = PageGenerator.getPage(DependentsPageObject.class,driver);

        dependentsPage.openEditNavigatorByName("Personal Details");
        personalDetailPage = PageGenerator.getPage(PersonalDetailPageObject.class,driver);

        personalDetailPage.openEditNavigatorByName("Job");
        jobPage = PageGenerator.getPage(JobPageObject.class,driver);
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
