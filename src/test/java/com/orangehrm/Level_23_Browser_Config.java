package com.orangehrm;

import core.BaseTest;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.orangeHRM.*;
import pageObjects.orangeHRM.EditNavigation.ContactDetailPageObject;
import pageObjects.orangeHRM.EditNavigation.DependentsPageObject;
import pageObjects.orangeHRM.EditNavigation.EditNavigatorPageObject;
import pageObjects.orangeHRM.EditNavigation.JobPageObject;

public class Level_23_Browser_Config extends BaseTest {
    @Parameters({"appUrl", "browser"})
    @BeforeClass
    public void beforeClass(String appURL, String browserName) {
        driver = getBrowserDriver(appURL, browserName);
        loginPage = PageGenerator.getPage(LoginPageObject.class, driver);
        //Selenium 3.x
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setBrowserName("chrome");
        desiredCapabilities.setVersion("144");
        desiredCapabilities.setPlatform(Platform.WIN10);

        //Selenium 4.x
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBrowserVersion("144");

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setEnableDownloads(true);

        adminUser = "Admin";
        adminPassword = "admin123";
        employeeFirstName = "Brown";
        employeeLastName = "Hana";
        employeeUsername = "Hana" + getRandomNumber();
        employeePassword = "Abc@123";

        loginPage.enterToTextboxByLabel(driver, "Username", adminUser);
        loginPage.enterToTextboxByLabel(driver, "Password", adminPassword);
        loginPage.clickToButtonByText(driver, "Login");
        dashboardPage = PageGenerator.getPage(DashboardPageObject.class, driver);
    }

    @Test
    public void Employee_01_NewEmployee() {}

    @Test
    public void Employee_02_Upload_Avatar() {

    }
    @Test
    public void Employee_03_EditPersonalDetails() {

    }

    @Test
    public void Employee_04_ContactDetais() {
       }

    @Test
    public void Employee_05_EmergencyDetais() {
         }

    @Test
    public void Employee_06_Dependents() {
           }

    @Test
    public void Employee_07_Jobs() {

    }

    @Test
    public void Employee_08_Salary() {

    }

    @Test
    public void Employee_09_Tax() {
    }

    @Test
    public void Employee_10_Qualifications() {

    }

    @Test
    public void Employee_11_Search() {

    }

    @AfterClass (alwaysRun = true)
    public void afterClass() {
        closeBrowser(driver);
    }

    private WebDriver driver;
    private EditNavigatorPageObject editNavigatorPage;
    private LoginPageObject loginPage;
    private DashboardPageObject dashboardPage;
    private EmployeeListPageObject employeeListPage;
    private AddEmployeePageObject addEmployeePage;
    private pageObjects.orangeHRM.editNavigation.PersonalDetailPageObject personalDetailPage;
    private ContactDetailPageObject contactDetailPage;
    private pageObjects.orangeHRM.editNavigation.EmergencyContactPageObject emergencyContactPage;
    private DependentsPageObject dependentsPage;
    private JobPageObject jobPage;
    private pageObjects.orangeHRM.editNavigation.SalaryPageObject salaryPage;
    private pageObjects.orangeHRM.editNavigation.QualificationsPageObject qualificationsPage;
    private EmployeeInformationPageObject employeeInformationPage;

    private String employeeID, adminUserName, adminPassword, employeeFirstName, employeeLastName,adminUser;
    private String employeeUsername, employeePassword, eNationality, eMaritalStatus, eBirth, eGender;
    private String contStreet, contCity, contProvince, contCountry, contHome, contMobile, contWork, contWorkEmail;
    private String emerName, emerRelationship, emerHomeTelephone, emerFileName;
    private String depName, depRelationship, depBirthValue, depFileName;
    private String jJoinDate, jJobTitle, jJobCategory, jSubUnit, jLocation, jEmploymentStatus, jContractStartDate, jContractDetails, jFileName;
    private String sSalaryComponent, sPayGrade, sPayFrequency, sCurrency, sAccountNumber, sAccountType, sFileName, sAmount, sDAmount, sRoutingNumber;
    private String qCompany, qJobTitle, qFrom, qTo, qLevel, qGPA, qYear;
}
