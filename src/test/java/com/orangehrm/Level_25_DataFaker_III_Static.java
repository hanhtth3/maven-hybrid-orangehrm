package com.orangehrm;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.orangeHRM.AddEmployeePageObject;
import pageObjects.orangeHRM.DashboardPageObject;
import pageObjects.orangeHRM.EmployeeListPageObject;
import pageObjects.orangeHRM.LoginPageObject;
import data.orangehrm.Employee;

public class Level_25_DataFaker_III_Static extends BaseTest {
    @Parameters({"appUrl", "browser"})
    @BeforeClass
    public void beforeClass(String appURL, String browserName) {
        driver = getBrowserDriver(appURL, browserName);
        loginPage = PageGenerator.getPage(LoginPageObject.class, driver);
        employeeID = String.valueOf(getRandomNumber());

        loginPage.enterToTextboxByLabel(driver, "Username", Employee.ADMIN_USERNAME);
        loginPage.enterToTextboxByLabel(driver, "Password", Employee.ADMIN_PASSWORD);
        loginPage.clickToButtonByText(driver, "Login");
        dashboardPage = PageGenerator.getPage(DashboardPageObject.class, driver);

        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);

        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Dashboard"));
    }

    @Test
    public void Employee_01_NewEmployee() {
        dashboardPage.clickToModuleByTextInMenuItem(driver, "PIM");
        employeeListPage = PageGenerator.getPage(EmployeeListPageObject.class, driver);
        verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

        employeeListPage.clickToButtonByText(driver, "Add");
        addEmployeePage = PageGenerator.getPage(AddEmployeePageObject.class, driver);
        verifyTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        addEmployeePage.enterToTextboxByName(driver, "firstName", Employee.FIRST_NAME);
        addEmployeePage.enterToTextboxByName(driver, "lastName", Employee.LAST_NAME);
        addEmployeePage.enterToTextboxByLabel(driver, "EmployeeID", employeeID);
        //employeeID = addEmployeePage.getTextboxValueByLabel(driver, "Employee Id");

        addEmployeePage.clickToCheckboxByLabel(driver,"Create Login Details");

        addEmployeePage.enterToTextboxByLabel(driver, "Username", Employee.USER_NAME +String.valueOf(employeeID));
        addEmployeePage.enterToTextboxByLabel(driver, "Password", Employee.PASSWORD);
        addEmployeePage.enterToTextboxByLabel(driver, "Confirm Password", Employee.PASSWORD);

        addEmployeePage.clickToButtonByText(driver, "Save");
        personalDetailPage = PageGenerator.getPage(pageObjects.orangeHRM.editNavigation.PersonalDetailPageObject.class, driver);

        verifyTrue(personalDetailPage.isToastMessageDisplayed(driver,"Successfully Saved"));

        verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(5);

        verifyEquals(personalDetailPage.getTextboxValueByName(driver, "firstName"), Employee.FIRST_NAME);
        verifyEquals(personalDetailPage.getTextboxValueByName(driver, "lastName"), Employee.LAST_NAME);
        verifyEquals(personalDetailPage.getTextboxValueByLabel(driver, "Employee Id"), employeeID);

    }
    @AfterClass (alwaysRun = true)
    public void afterClass() {
        closeBrowser(driver);
    }

    private WebDriver driver;
    private LoginPageObject loginPage;
    private DashboardPageObject dashboardPage;
    private EmployeeListPageObject employeeListPage;
    private AddEmployeePageObject addEmployeePage;
    private pageObjects.orangeHRM.editNavigation.PersonalDetailPageObject personalDetailPage;
    private String employeeID;
}
