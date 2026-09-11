package browserFactory.BrowserFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxBrowserManager implements BrowserFactory{
    @Override
    public WebDriver getDriver() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        //Config Capabilities
        return new FirefoxDriver(firefoxOptions);
    }
}
