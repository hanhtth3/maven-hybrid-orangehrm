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

public class Level_21_Close_Browser extends BaseTest {
    @Parameters({"appUrl", "browser"})
    @BeforeClass
    public void beforeClass(String appURL, String browserName) {
        driver = getBrowserDriver(appURL, browserName);
        loginPage = PageGenerator.getPage(LoginPageObject.class, driver);
        adminUser = "Admin";
        adminPassword = "admin123";

        loginPage.enterToTextboxByLabel(driver, "Login", adminUser);
        loginPage.enterToTextboxByLabel(driver, "Password", adminPassword);
        loginPage.clickToButtonByText(driver, "Login");
        dashboardPage = PageGenerator.getPage(DashboardPageObject.class, driver);

        Assert.assertFalse(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);

        verifyFalse(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Admin"));
    }

    @Test
    public void Login_01_CreateNewEmployee() {}
    public void Login_02_ViewEmployee() {}
    public void Login_03_DeleteEmployee() {}

    @AfterClass (alwaysRun = true)
    public void afterClass() {
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
    private String employeeID, adminUser, adminPassword, employeeFirstName, employeeLastName;
    private String employeeUsername, employeePassword;
}
