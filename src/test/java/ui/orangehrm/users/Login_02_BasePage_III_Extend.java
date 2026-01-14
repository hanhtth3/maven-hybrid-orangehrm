package ui.orangehrm.users;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Login_02_BasePage_III_Extend extends BasePage{
    private WebDriver driver;
    private String appUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
    @Test
    public void Login_01_Empty(){
        openPageUrl(driver, appUrl);

        sendKeyToElement(driver,"//input[@name ='username']","");
        clickToElement(driver,"//button[contains(@class,'oxd-button oxd-button--medium oxd-button--main orangehrm-login-button')]");

        Assert.assertEquals(getElementText(driver,"//input[@name='username']/parent::div/following-sibling::span"),"Required");
        Assert.assertEquals(getElementText(driver,"//input[@name='password']/parent::div/following-sibling::span"),"Required");
    }
    @Test
    public void Login_02_Invalid_User(){
         openPageUrl(driver, appUrl);

        sendKeyToElement(driver,"//input[@name ='username']","hanhtth3@gmail.com");
        sendKeyToElement(driver,"//input[@name ='password']","123456");
        clickToElement(driver,"//button[contains(@class,'oxd-button oxd-button--medium oxd-button--main orangehrm-login-button')]");

        Assert.assertEquals(getElementText(driver,"//div[@class = 'oxd-alert-content oxd-alert-content--error']/p[contains(@class,'content-text')]"),"Invalid credentials");
    }
    @Test
    public void Login_03_Invalid_Password(){
      openPageUrl(driver, appUrl);

      sendKeyToElement(driver,"//input[@name ='username']","Admin");
      sendKeyToElement(driver,"//input[@name ='password']","admin123@!#");
      clickToElement(driver,"//button[contains(@class,'oxd-button oxd-button--medium oxd-button--main orangehrm-login-button')]");

        Assert.assertEquals(getElementText(driver,"//div[@class = 'oxd-alert-content oxd-alert-content--error']/p[contains(@class,'content-text')]"),"Invalid credentials");
    }
    @Test
    public void Login_04_Valid_User_Password(){
        openPageUrl(driver, appUrl);

        sendKeyToElement(driver,"//input[@name ='username']","Admin");
        sendKeyToElement(driver,"//input[@name ='password']","admin123");
        clickToElement(driver,"//button[contains(@class,'oxd-button oxd-button--medium oxd-button--main orangehrm-login-button')]");

        Assert.assertTrue(isAllLoadingSpinnerInvisible());
        Assert.assertEquals(getElementText(driver,"//div[@class = 'oxd-topbar-header-title']//h6"),"Dashboard");
     }
    public boolean isAllLoadingSpinnerInvisible(){
        return waitListElementInvisible(driver,"//div[@class='oxd-loading-spinner']");
    }
    @AfterClass
    public void  afterClass(){
        driver.quit();
    }

}
