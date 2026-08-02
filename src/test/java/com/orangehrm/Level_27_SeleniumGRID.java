package com.orangehrm;

import core.BaseTest;
import org.aeonbits.owner.ConfigFactory;
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
import utilitiles.IEnvironment;
import utilitiles.PropertiesConfig;

public class Level_27_SeleniumGRID extends BaseTest {
    IEnvironment environment;

    @Parameters({"server", "browser","osName","ipAddress","port"})
    @BeforeClass
    public void beforeClass(String server, String browserName,String osName,String ipAddress,String port) {
        ConfigFactory.setProperty("environment", server);

        environment = ConfigFactory.create(IEnvironment.class);
        driver = getBrowserDriver(environment.appUrl(), browserName);

        loginPage = PageGenerator.getPage(LoginPageObject.class, driver);
        excellConfig= ExcelConfig.getExcelData();
        excellConfig.switchToSheet("employee");

//        adminUser = "Admin";
//        adminPassword = "admin123";
        employeeID = String.valueOf(getRandomNumber());

        employeeUsername = excellConfig.getCellData("UserName",2)+getRandomNumber();
        employeePassword = excellConfig.getCellData("Password",2)+getRandomNumber()+"@gmail.com";

        loginPage.enterToTextboxByLabel(driver, "Username", environment.appUser());
        loginPage.enterToTextboxByLabel(driver, "Password", environment.appPassword());
        loginPage.clickToButtonByText(driver, "Login");
        dashboardPage = PageGenerator.getPage(DashboardPageObject.class, driver);

        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);

        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver,"Dashboard"));
    }

    @Test
    public void Employee_01_NewEmployee() {}
    @AfterClass (alwaysRun = true)
    public void afterClass() {
        closeBrowser(driver);
    }

    private WebDriver driver;
    private LoginPageObject loginPage;
    private DashboardPageObject dashboardPage;
    private String employeeID, adminPassword,adminUser;
    private String employeeUsername, employeePassword;
    private ExcelConfig excellConfig;
}
