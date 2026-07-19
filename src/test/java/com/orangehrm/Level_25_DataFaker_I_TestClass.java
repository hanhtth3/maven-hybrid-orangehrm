package com.orangehrm;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.orangeHRM.*;
import utilitiles.DataConfigNet;

public class Level_25_DataFaker_I_TestClass extends BaseTest {
    @Parameters({"appUrl", "browser"})
    @BeforeClass
    public void beforeClass(String appURL, String browserName) {
        driver = getBrowserDriver(appURL, browserName);
        loginPage = PageGenerator.getPage(LoginPageObject.class, driver);
        dataConfig = DataConfigNet.getData();

        adminUser = "Admin";
        adminPassword = "admin123";
        employeeID = String.valueOf(getRandomNumber());

        employeeFirstName = dataConfig.getFirstName();
        employeeLastName = dataConfig.getLastName();
        employeeUsername = dataConfig.getUsername();
        employeePassword = dataConfig.getPassword();

        editEmployeeFirstName = dataConfig.getFirstName();
        editEditEmployeeLastName= dataConfig.getLastName();
        employeeDOB="1998-09-04";
        employeeGenfer="Female";

        loginPage.enterToTextboxByLabel(driver, "Username", adminUser);
        loginPage.enterToTextboxByLabel(driver, "Password", adminPassword);
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

        addEmployeePage.enterToTextboxByName(driver, "firstName", employeeFirstName);
        addEmployeePage.enterToTextboxByName(driver, "lastName", employeeLastName);

        employeeID = addEmployeePage.getTextboxValueByLabel(driver, "Employee Id");

        addEmployeePage.clickToCheckboxByLabel(driver,"Create Login Details");

        addEmployeePage.enterToTextboxByLabel(driver, "Username", employeeUsername);
        addEmployeePage.enterToTextboxByLabel(driver, "Password", employeePassword);
        addEmployeePage.enterToTextboxByLabel(driver, "Confirm Password", employeePassword);

        addEmployeePage.clickToButtonByText(driver, "Save");
        personalDetailPage = PageGenerator.getPage(pageObjects.orangeHRM.editNavigation.PersonalDetailPageObject.class, driver);

        verifyTrue(personalDetailPage.isToastMessageDisplayed(driver,"Successfully Saved"));

        verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(5);

        verifyEquals(personalDetailPage.getTextboxValueByName(driver, "firstName"), employeeFirstName);
        verifyEquals(personalDetailPage.getTextboxValueByName(driver, "lastName"), employeeFirstName);
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
    private DataConfigNet dataConfig;
    private String employeeID, adminUserName, adminPassword, employeeFirstName, employeeLastName,adminUser;
    private String employeeUsername, employeePassword, editEmployeeFirstName,editEditEmployeeLastName,employeeDOB,employeeGenfer;

}
