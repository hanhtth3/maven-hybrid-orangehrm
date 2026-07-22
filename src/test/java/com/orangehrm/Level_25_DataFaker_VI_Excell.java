package com.orangehrm;

import core.BaseTest;
import data.model.Employee;
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
import utilitiles.ExcelConfig;

public class Level_25_DataFaker_VI_Excell extends BaseTest {
    @Parameters({"appUrl", "browser"})
    @BeforeClass
    public void beforeClass(String appURL, String browserName) {
        driver = getBrowserDriver(appURL, browserName);
        loginPage = PageGenerator.getPage(LoginPageObject.class, driver);
        excellConfig= ExcelConfig.getExcelData();
        excellConfig.switchToSheet("employee");

        adminUser = "Admin";
        adminPassword = "admin123";
        employeeID = String.valueOf(getRandomNumber());

        employeeUsername = excellConfig.getCellData("UserName",2)+getRandomNumber();
        employeePassword = excellConfig.getCellData("Password",2)+getRandomNumber()+"@gmail.com";

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

        addEmployeePage.enterToTextboxByName(driver, "firstName", excellConfig.getCellData("FirstName",2));
        addEmployeePage.enterToTextboxByName(driver, "lastName", excellConfig.getCellData("LastName",2));

        employeeID = addEmployeePage.getTextboxValueByLabel(driver, "Employee Id");

        addEmployeePage.clickToCheckboxByLabel(driver,"Create Login Details");

        addEmployeePage.enterToTextboxByLabel(driver, "Username", excellConfig.getCellData("Username",2));
        addEmployeePage.enterToTextboxByLabel(driver, "Password", excellConfig.getCellData("Password",2));
        addEmployeePage.enterToTextboxByLabel(driver, "Confirm Password", excellConfig.getCellData("Confirm Password",2));

        addEmployeePage.clickToButtonByText(driver, "Save");
        personalDetailPage = PageGenerator.getPage(pageObjects.orangeHRM.editNavigation.PersonalDetailPageObject.class, driver);

        verifyTrue(personalDetailPage.isToastMessageDisplayed(driver,"Successfully Saved"));

        verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(5);

        verifyEquals(personalDetailPage.getTextboxValueByName(driver, "firstName"), excellConfig.getCellData("FirstName",2));
        verifyEquals(personalDetailPage.getTextboxValueByName(driver, "lastName"), excellConfig.getCellData("LastName",2));
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
    private String employeeID, adminPassword,adminUser;
    private String employeeUsername, employeePassword;
    private ExcelConfig excellConfig;

}
