package com.orangehrm;

import core.BaseTest;
import core.GlobalConstants;
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
import testdata.orangehrm.Employee;
import testdata.orangehrm.Employee_Data;

public class Level_25_DataFaker_IV_POJO extends BaseTest {
    @Parameters({"appUrl", "browser"})
    @BeforeClass
    public void beforeClass(String appURL, String browserName) {
        driver = getBrowserDriver(appURL, browserName);
        loginPage = PageGenerator.getPage(LoginPageObject.class, driver);

        empoyeeData = Employee_Data.getEmployeeData();
        employeeID = String.valueOf(getRandomNumber());
        empoyeeData.setFirstName("Hanh");
        empoyeeData.setLastName("Nguyen");
        empoyeeData.setPassword("Abc@123");
        empoyeeData.setUserName("hanh.nguyen");

        loginPage.enterToTextboxByLabel(driver, "Username", GlobalConstants.ADMIN_ORANGE_USERNAME);
        loginPage.enterToTextboxByLabel(driver, "Password", GlobalConstants.ADMIN_ORANGE_PASSWORD);
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

        addEmployeePage.enterToTextboxByName(driver, "firstName", empoyeeData.getFirstName());
        addEmployeePage.enterToTextboxByName(driver, "lastName", empoyeeData.getLastName());
        addEmployeePage.enterToTextboxByLabel(driver, "EmployeeID", employeeID);
        //employeeID = addEmployeePage.getTextboxValueByLabel(driver, "Employee Id");

        addEmployeePage.clickToCheckboxByLabel(driver,"Create Login Details");

        addEmployeePage.enterToTextboxByLabel(driver, "Username", empoyeeData.getUserName());
        addEmployeePage.enterToTextboxByLabel(driver, "Password", empoyeeData.getPassword());
        addEmployeePage.enterToTextboxByLabel(driver, "Confirm Password", empoyeeData.getPassword());

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
    private Employee_Data empoyeeData;
    private DashboardPageObject dashboardPage;
    private EmployeeListPageObject employeeListPage;
    private AddEmployeePageObject addEmployeePage;
    private pageObjects.orangeHRM.editNavigation.PersonalDetailPageObject personalDetailPage;
    private String employeeID,
}
