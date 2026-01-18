package ui.orangehrm.users;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.*;

public class Login_04_Page_Object extends BaseTest {
    @Parameters ({"appUrl", "browser"})
    @BeforeClass
    public void beforeClass(String appURL,String browserName){
        appUrl = appURL;
        driver = getBrowserDriver(appURL, browserName);
    }
    @Test
    public void Login_01_CreateNewEmployee(){
        //Action of Login
        loginPage = new LoginPageObject();
        loginPage.enterToUserNameTextbox("Admin");
        loginPage.enterToPasswordTextbox("admin123");
        loginPage.clickToLoginButton();
        //Action of Dashboard
        dashboardPage = new DashboardPageObject();
        dashboardPage.clickToPIMModule();
        //Action of Employee List
        employeeListPage = new EmployeeListPageObject();
        employeeListPage.clickToAddEmployeeButton();
        //Action of Add employee
        addEmployeePage = new AddEmployeePageObject();
        addEmployeePage.enterToFirtNameTextbox("");
        addEmployeePage.enterToLastNameTextbox("");
        employeeID = addEmployeePage.getEmployeeID();
        addEmployeePage.clickSaveButton();
        //Action of Personal Detail
        personalDetailPage = new PersonalDetailPageObject();
        Assert.assertEquals(personalDetailPage.getFirstNameTextboxValue(),"");
        Assert.assertEquals(personalDetailPage.getLastNameTextboxValue(),"");
        Assert.assertEquals(personalDetailPage.getEmployeeIDTextboxValue(),employeeID);
    }
    @Test
    public void Login_02_EditEmployee(){

    }

    @AfterClass
    public void  afterClass(){
        driver.quit();
    }
    private WebDriver driver;
    private AddEmployeePageObject addEmployeePage;
    private DashboardPageObject dashboardPage;
    private EmployeeListPageObject employeeListPage;
    private LoginPageObject loginPage;
    private PersonalDetailPageObject personalDetailPage;
    private String employeeID;
}
