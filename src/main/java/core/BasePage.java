package core;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class BasePage {
    public void openPageUrl(WebDriver driver, String pageUrl){
        driver.get(pageUrl);
    }
    public String getPageTitle(WebDriver driver){
        return driver.getTitle();
    }
    public String getPageUrl(WebDriver driver){
       return driver.getCurrentUrl();
    }
    public String getPageSource(WebDriver driver){
       return driver.getPageSource();
    }
    public void backToPage(WebDriver driver){
        driver.navigate().back();
    }
    public void forwardToPage(WebDriver driver){
        driver.navigate().forward();
    }
    public void refreshPage(WebDriver driver){
        driver.navigate().refresh();
    }
    private Alert waitAlertPresence (WebDriver driver){
        return new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.alertIsPresent());
    }
    public void acceptToAlert (WebDriver driver){
        waitAlertPresence(driver).accept();
    }
    public void cancelToAlert (WebDriver driver){
        waitAlertPresence(driver).dismiss();
    }
    public void sendkeyToAlert (WebDriver driver, String keyToSend){
        waitAlertPresence(driver).sendKeys(keyToSend);
    }
    public String getAlertText (WebDriver driver){
        return waitAlertPresence(driver).getText();
    }
    private void sleepInsSecond (long timeInSecond){
        try{
            Thread.sleep(timeInSecond*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }
    private  void switchToWindowByID(WebDriver driver,String windowID){
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id: allWindowIDs){
            if(!id.equals(windowID)){
                driver.switchTo().window(id);
                break;
            }
        }
    }
    private void switchToWindowByTitle(WebDriver driver,String expectedTitle){
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id: allWindowIDs){
            driver.switchTo().window(id);
            sleepInsSecond(2);
            if(driver.getTitle().contains(expectedTitle)){
                break;
            }
        sleepInsSecond(2);
        }
    }
    private  void closeAllWindowWithoutParent(WebDriver driver,String windowID){
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id: allWindowIDs){
            if(!id.equals(windowID)){
                driver.switchTo().window(id);
                driver.close();
            }
        sleepInsSecond(2);
        }
        driver.switchTo().window(windowID);
    }
}
