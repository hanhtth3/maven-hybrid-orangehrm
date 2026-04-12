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

public class Level_12_Asert_Verify extends BaseTest {
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

        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);
        employeeListPage = dashboardPage.clickToPIMModule();
        verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));
        addEmployeePage = employeeListPage.clickToAddEmployeeButton();

        verifyTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));
        addEmployeePage.enterToFirtNameTextbox(employeeFirstName);
        addEmployeePage.enterToLastNameTextbox(employeeLastName);
        employeeID = addEmployeePage.getEmployeeID();
        personalDetailPage = addEmployeePage.clickSaveButton();
      
       verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(2);

        verifyEquals(personalDetailPage.getFirstNameTextboxValue(),employeeLastName);
        verifyEquals(personalDetailPage.getLastNameTextboxValue(),employeeFirstName);
        verifyEquals(personalDetailPage.getEmployeeIDTextboxValue(),employeeID);
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
