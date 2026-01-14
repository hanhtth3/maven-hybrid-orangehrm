package ui.orangehrm.users;

import core.BasePage;
import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class Login_03_Multiple_Browser extends BaseTest {
    private WebDriver driver;
    private BasePage basePage;
    private String appUrl ;
    @Parameters ({"appUrl", "browser"})
    @BeforeClass
    public void beforeClass(String appURL,String browserName){
        appUrl = appURL;
        basePage = BasePage.getInstance();
        driver = getBrowserDriver(appURL, browserName);
    }
    @Test
    public void Login_01_Empty(){
        basePage.openPageUrl(driver, appUrl);

        basePage.sendKeyToElement(driver,"//input[@name ='username']","");
        basePage.sendKeyToElement(driver,"//input[@name ='password']","");
        basePage.clickToElement(driver,"//button[contains(@class,'oxd-button oxd-button--medium oxd-button--main orangehrm-login-button')]");

        Assert.assertEquals(basePage.getElementText(driver,"//input[@name='username']/parent::div/following-sibling::span"),"Required");
        Assert.assertEquals(basePage.getElementText(driver,"//input[@name='password']/parent::div/following-sibling::span"),"Required");
    }
   // @Test
    public void Login_02_Invalid_User(){
        basePage.openPageUrl(driver, appUrl);

        basePage.sendKeyToElement(driver,"//input[@name ='username']","hanhtth3@gmail.com");
        basePage.sendKeyToElement(driver,"//input[@name ='password']","123456");
        basePage.clickToElement(driver,"//button[contains(@class,'oxd-button oxd-button--medium oxd-button--main orangehrm-login-button')]");

        Assert.assertEquals(basePage.getElementText(driver,"//div[@class = 'oxd-alert-content oxd-alert-content--error']/p[contains(@class,'content-text')]"),"Invalid credentials");
    }
    //@Test
    public void Login_03_Invalid_Password(){
        basePage.openPageUrl(driver, appUrl);

        basePage.sendKeyToElement(driver,"//input[@name ='username']","Admin");
        basePage.sendKeyToElement(driver,"//input[@name ='password']","admin123@!#");
        basePage.clickToElement(driver,"//button[contains(@class,'oxd-button oxd-button--medium oxd-button--main orangehrm-login-button')]");

        Assert.assertEquals(basePage.getElementText(driver,"//div[@class = 'oxd-alert-content oxd-alert-content--error']/p[contains(@class,'content-text')]"),"Invalid credentials");
    }
    //@Test
    public void Login_04_Valid_User_Password(){
        basePage.openPageUrl(driver, appUrl);

        basePage.sendKeyToElement(driver,"//input[@name ='username']","Admin");
        basePage.sendKeyToElement(driver,"//input[@name ='password']","admin123");
        basePage.clickToElement(driver,"//button[contains(@class,'oxd-button oxd-button--medium oxd-button--main orangehrm-login-button')]");

        Assert.assertTrue(isAllLoadingSpinnerInvisible());
        Assert.assertEquals(basePage.getElementText(driver,"//div[@class = 'oxd-topbar-header-title']//h6"),"Dashboard");
    }
    public boolean isAllLoadingSpinnerInvisible(){
        return basePage.waitListElementInvisible(driver,"//div[@class='oxd-loading-spinner']");
    }
    @AfterClass
    public void  afterClass(){
        driver.quit();
    }

}
