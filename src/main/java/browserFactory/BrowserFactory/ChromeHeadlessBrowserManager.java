package browserFactory.BrowserFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class ChromeHeadlessBrowserManager implements BrowserFactory{
    @Override
    public WebDriver getDriver() {
        ChromeOptions hcOptions = new FirefoxOptions();
        hcOptions.addArguments("-headless");
        hcOptions.addArguments("window-size=1920,1080");
        return new ChromeDriver(hcOptions);
    }
}
