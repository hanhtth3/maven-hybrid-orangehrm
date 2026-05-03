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
import pageObjects.orangeHRM.EditNavigation.ContactDetailPageObject;
import pageObjects.orangeHRM.EditNavigation.DependentsPageObject;
import pageObjects.orangeHRM.EditNavigation.JobPageObject;
import pageObjects.orangeHRM.EditNavigation.PersonalDetailPageObject;
import pageObjects.orangeHRM.EmployeeListPageObject;
import pageObjects.orangeHRM.LoginPageObject;

public class Level_19_Element_Undisplayed extends BaseTest {
    @Parameters({"appUrl", "browser"})
    @BeforeClass
    public void beforeClass(String appURL, String browserName) {
        driver = getBrowserDriver(appURL, browserName);
        loginPage = PageGenerator.getPage(LoginPageObject.class, driver);
        adminUser = "Admin";
        adminPassword = "admin123";
        employeeFirstName = "Brown";
        employeeLastName = "Hana";
        employeeUsername = "Hana" + getRandomNumber();
        employeePassword = "Abc@123";
    }

    @Test
    public void Login_01_CreateNewEmployee() {

        loginPage.enterToTextboxByLabel(driver, "Login", adminUser);
        loginPage.enterToTextboxByLabel(driver, "Password", adminPassword);
        loginPage.clickToButtonByText(driver, "Login");
        dashboardPage = PageGenerator(DashboardPageObject.class, driver);

        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);

        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Admin"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"PIM"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Leave"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Time"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Recruitment"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"My Info"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Performance"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Dashboard"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Directory"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Maintenance"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Claim"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Buzz"));

        dashboardPage.clickToModuleByNameInMenuItem(driver, "PIM");
        employeeListPage = PageGenerator.getPage(EmployeeListPageObject.class, driver);
        verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

        addEmployeePage.clickToButtonByText(driver, "Add");
        employeeListPage = PageGenerator.getPage(AddEmployeePageObject.class, driver);
        verifyTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        addEmployeePage.enterToTextboxByName(driver, "firstName", employeeFirstName);
        addEmployeePage.enterToTextboxByName(driver, "lastName", employeeLastName);

        employeeID = addEmployeePage.getTextboxValueByLabel(driver, "Employee ID");
        employeeListPage.clickToCheckboxByLabel(driver,"Create Login Details");

        loginPage.enterToTextboxByLabel(driver, "Username", employeeUsername);
        loginPage.enterToTextboxByLabel(driver, "Password", employeePassword);
        loginPage.enterToTextboxByLabel(driver, "Confirm Password", employeePassword);

        addEmployeePage.clickToButtonByText(driver, "Save");
        personalDetailPage = PageGenerator.getPage(PersonalDetailPageObject.class, driver);

        verifyTrue(personalDetailPage.isToastMessageDisplayed(driver,"Successfully Saved"));

        verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(2);

        verifyEquals(personalDetailPage.getTextboxValueByName(driver, "firstName"), employeeFirstName);
        verifyEquals(personalDetailPage.getTextboxValueByName(driver, "lastName"), employeeFirstName);
        verifyEquals(personalDetailPage.getTextboxValueByLabel(driver, "employee ID", employeeID);

        //Logout

        loginPage = personalDetailPage.clickLogoutOnTopMenu(driver);

        //Login bang quyen Employee vua tao ra
        loginPage.enterToTextboxByLabel(driver,"Username",employeeUsername);
        loginPage.enterToTextboxByLabel(driver,"Password",employeePassword);
        loginPage.clickToButtonByText(driver,"Login");
        dashboardPage = PageGenerator.getPage(DashboardPageObject.class,driver);

        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);

        //Den man hinh DashBoard
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Leave"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Time"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"My Info"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Performance"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Dashboard"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Directory"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Claim"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Buzz"));

        //Case 3: element khong hien thi tren UI va khong co trong DOM
        verifyTrue(dashboardPage.isModuleByTextInMenuItemUnDisplayed(driver,"Admin"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemUnDisplayed(driver,"PIM"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemUnDisplayed(driver,"Recruitment"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemUnDisplayed(driver,"Maintenance"));

    }

    @AfterClass
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
