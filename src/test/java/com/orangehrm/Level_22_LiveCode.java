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

public class Level_22_LiveCode extends BaseTest {
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

        loginPage.enterToTextboxByLabel(driver, "Login", adminUser);
        loginPage.enterToTextboxByLabel(driver, "Password", adminPassword);
        loginPage.clickToButtonByText(driver, "Login");
        dashboardPage = PageGenerator.getPage(DashboardPageObject.class, driver);

        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);

        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Dashboard"));
    }

    @Test
    public void Employee_01_NewEmployee() {
        dashboardPage.clickToModuleByNameInMenuItem(driver, "PIM");
        employeeListPage = PageGenerator.getPage(EmployeeListPageObject.class, driver);
        verifyTrue(employeeListPage.isLoadingSpinnerDisappear(driver));

        addEmployeePage.clickToButtonByText(driver, "Add");
        employeeListPage = PageGenerator.getPage(EmployeeListPageObject.class, driver);
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
        verifyEquals(personalDetailPage.getTextboxValueByLabel(driver, "employee ID"), employeeID);

        //Logout

        loginPage = personalDetailPage.clickLogoutOnTopMenu(driver);

        //Login bang quyen Employee vua tao ra
        loginPage.enterToTextboxByLabel(driver,"Username",employeeUsername);
        loginPage.enterToTextboxByLabel(driver,"Password",employeePassword);
        loginPage.clickToButtonByText(driver,"Login");
        dashboardPage = PageGenerator.getPage(DashboardPageObject.class,driver);

        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);
        //Đến màn Dashboard
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"My Info"));
        dashboardPage.clickToModuleByNameInMenuItem(driver,"My Info");
        personalDetailPage = PageGenerator.getPage(PersonalDetailPageObject.class, driver);

        verifyEquals(personalDetailPage.getTextboxValueByName(driver, "firstName"), employeeFirstName);
        verifyEquals(personalDetailPage.getTextboxValueByName(driver, "lastName"), employeeFirstName);
        verifyEquals(personalDetailPage.getTextboxValueByLabel(driver, "employee ID"), employeeID);



        //Tạo mới Enable/Disable
        //Tạo mới không có thông tin login
        //Login với employee vưà tạo
        //Xóa employee với role Admin
        //Login lại với employee đã xóa
    }
    @Test
    public void Employee_02_Upload_Avatar() {
        //File type
        //Maximum size
        //Maximun Dimension
    }
    @Test
    public void Employee_03_Edit_PersonalDetails() {
        //Edit với role Admin
        //View với role Employee
        //Edit với role Employee
        //View với role Admin
    }
    @Test
    public void Employee_04_Contact_Details() {}
    @Test
    public void Employee_05_Emmergency_Details() {}
    @Test
    public void Employee_06_Assigned_Dependents() {}
    @Test
    public void Employee_07_Edit_View_Jobs() {}
    @Test
    public void Employee_08_Edit_View_Salary() {}
    @Test
    public void Employee_09_Edit_View_Tax() {}
    @Test
    public void Employee_10_Qualifications() {}
    @Test
    public void Employee_11_Search_Employeee() {}

    @AfterClass (alwaysRun = true)
    public void afterClass() {
        closeBrowser(driver);
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
