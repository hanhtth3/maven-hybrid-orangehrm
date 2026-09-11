package browserFactory.BrowserFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxHeadlessBrowserManager implements BrowserFactory{
    @Override
    public WebDriver getDriver() {
        FirefoxOptions hfOptions = new FirefoxOptions();
        hfOptions.addArguments("-headless");
        hfOptions.addArguments("window-size=1920,1080");
        return new FirefoxDriver(hfOptions);
    }
}
