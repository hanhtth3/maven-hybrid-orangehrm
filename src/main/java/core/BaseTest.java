package core;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.Random;

import static org.openqa.selenium.remote.Browser.*;

public class BaseTest {
    private WebDriver driver;
    protected WebDriver getBrowserDriver(String appURL, String browserName) {
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
        Path path =null;
        File extendsionFilePath;
        switch (browserList) {
            case HEAD_FIREFOX:
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("-headless");
                options.addArguments("-window-size=1920,1080");
                driver = new FirefoxDriver(options);
                break;
            case HEAD_CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("window-size=1920,1080");
                driver = new ChromeDriver(chromeOptions);
                break;
            case HEAD_EDGE:
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--headless");
                edgeOptions.addArguments("--window-size=1920,1080");
                driver = new EdgeDriver(edgeOptions);
                break;
            case SAFARI:
                driver = new SafariDriver();
                break;
            case CHROME:
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case EDGE:
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("Browser name is not valid");
        }

        driver.get(appURL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        return driver;
    }

    protected WebDriver getBrowserDriver(String browserName, String url, String osName, String ipAddress, String portNumber) {
        DesiredCapabilities capability = new DesiredCapabilities();
        Platform platform = null;

        if (osName.toLowerCase().contains("windows")) {
            platform = Platform.WINDOWS;
        } else {
            platform = Platform.MAC;
        }

        switch (browserName) {
            case "firefox":
                capability.setBrowserName("firefox");
                capability.setPlatform(platform);

                FirefoxOptions fOptions = new FirefoxOptions();
                fOptions.merge(capability);
                break;
            case "chrome":
                capability.setBrowserName("chrome");
                capability.setPlatform(platform);

                ChromeOptions cOptions = new ChromeOptions();
                cOptions.merge(capability);
                break;
            case "edge":
                capability.setBrowserName("MicrosoftEdge");
                capability.setPlatform(platform);

                EdgeOptions eOptions = new EdgeOptions();
                eOptions.merge(capability);
                break;
            default:
                throw new RuntimeException("Browser is not valid!");
        }

        try {
            driver = new RemoteWebDriver(new URL(String.format("http://%s:%s/", ipAddress, portNumber)), capability);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
        driver.get(url);
        return driver;
    }protected WebDriver getBrowserDriver(String browserName, String url, String osName, String ipAddress, String portNumber) {
        DesiredCapabilities capability = new DesiredCapabilities();
        Platform platform = null;

        if (osName.toLowerCase().contains("windows")) {
            platform = Platform.WINDOWS;
        } else {
            platform = Platform.MAC;
        }

        switch (browserName) {
            case "firefox":
                capability.setBrowserName("firefox");
                capability.setPlatform(platform);

                FirefoxOptions fOptions = new FirefoxOptions();
                fOptions.merge(capability);
                break;
            case "chrome":
                capability.setBrowserName("chrome");
                capability.setPlatform(platform);

                ChromeOptions cOptions = new ChromeOptions();
                cOptions.merge(capability);
                break;
            case "edge":
                capability.setBrowserName("MicrosoftEdge");
                capability.setPlatform(platform);

                EdgeOptions eOptions = new EdgeOptions();
                eOptions.merge(capability);
                break;
            default:
                throw new RuntimeException("Browser is not valid!");
        }

        try {
            driver = new RemoteWebDriver(new URL(String.format("http://%s:%s/", ipAddress, portNumber)), capability);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
        driver.get(url);
        return driver;
    }
    //Cloud: BrowserStack
    protected WebDriver getBrowserDriverBrowserStack(String url,String browserName, String osName, String browserVersion, String osVersion) {
        MutableCapabilities capabilities = new MutableCapabilities();
        HashMap<String, Object> bstackOptions = new HashMap<String, Object>();

        capabilities.setCapability("browserName", browserName);

        bstackOptions.put("os", osName);
        bstackOptions.put("osVersion", browserVersion);
        bstackOptions.put("browserVersion", "148.0");
        bstackOptions.put("userName", GlobalConstants.BROWSER_STACK_USERNAME);
        bstackOptions.put("accessKey", GlobalConstants.BROWSER_STACK_AUTOMATE_KEY);
        bstackOptions.put("consoleLogs", "info");
        bstackOptions.put("seleniumVersion", "4.29.0");
        capabilities.setCapability("bstack:options", bstackOptions);

        try {
            driver = new RemoteWebDriver(new URL(GlobalConstants.BROWSER_STACK_URL)), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
        driver.get(url);
        return driver;
    }
    protected WebDriver getBrowserDriverSaucelab(String url, String osName, String browserName, String browserVersion) {
        MutableCapabilities capability = null;

        switch (browserName) {
            case "firefox":
                FirefoxOptions fOptions = new FirefoxOptions();
                fOptions.setPlatformName(osName);
                fOptions.setBrowserVersion(browserVersion);
                capability = fOptions;
                break;
            case "chrome":
                ChromeOptions cOptions = new ChromeOptions();
                cOptions.setPlatformName(osName);
                cOptions.setBrowserVersion(browserVersion);
                capability = cOptions;
                break;
            case "edge":
                EdgeOptions eOptions = new EdgeOptions();
                eOptions.setPlatformName(osName);
                eOptions.setBrowserVersion(browserVersion);
                capability = eOptions;
                break;
            case "safari":
                SafariOptions sOptions = new SafariOptions();
                sOptions.setPlatformName(osName);
                sOptions.setBrowserVersion(browserVersion);
                capability = sOptions;
                break;
            default:
                throw new RuntimeException("Browser is not valid!");
        }

        HashMap<String, String> sauceOptions = new HashMap<String, String>();
        sauceOptions.put("username", GlobalConstants.SAUCE_USERNAME);
        sauceOptions.put("accessKey", GlobalConstants.SAUCE_AUTOMATE_KEY);
        sauceOptions.put("build", "automation-fc-build");
        sauceOptions.put("name", "Run on " + osName + " | " + browserName + " | " + browserVersion);

        capability.setCapability("sauce:options", sauceOptions);

        try {
            driver = new RemoteWebDriver(new URL(GlobalConstants.SAUCE_URL), capability);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        driver.manage().window().maximize();
        driver.get(url);
        return driver;
    }
    protected void closeBrowserDriver() {
        String cmd = null;
        try {
            String osName = GlobalConstants.OS_NAME.toLowerCase();
            String driverInstanceName = driver.toString().toLowerCase();
            String browserDriverName = null;

            if (driverInstanceName.contains("chrome")) {
                browserDriverName = "chromedriver";
            } else if (driverInstanceName.contains("firefox")) {
                browserDriverName = "geckodriver";
            } else if (driverInstanceName.contains("edge")) {
                browserDriverName = "msedgedriver";
            } else {
                throw new RuntimeException("Driver Instance is not support");
            }

            if (osName.contains("window")) {
                cmd = "taskkill /F /FI \"IMAGENAME eq " + browserDriverName + "*\"";
            } else {
                cmd = "pkill " + browserDriverName;
            }

            if (driver != null) {
                driver.manage().deleteAllCookies();
                driver.quit();
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                Process process = Runtime.getRuntime().exec(cmd);
                process.waitFor();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected void closeBrowser(WebDriver dirver) {
        if(null != driver){driver.quit();}
    }
    protected int getRandomNumber() {
        return new Random().nextInt(999999);
    }

    protected boolean verifyTrue(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertTrue(condition);
        } catch (Throwable e) {
            pass = false;

            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyFalse(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertFalse(condition);
        } catch (Throwable e) {
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyEquals(Object actual, Object expected) {
        boolean pass = true;
        try {
            Assert.assertEquals(actual, expected);
        } catch (Throwable e) {
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyNotEquals(Object actual, Object expected) {
        boolean pass = true;
        try {
            Assert.assertNotEquals(actual, expected);
        } catch (Throwable e) {
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    private String getEnvironmentUrl (String environmentName) {
        String envUrl = null;
        switch (environmentName) {
            case "Dev":
                envUrl ="https://opensource-demo.orangehrmlive.com";
                break;
            case "Test":
                envUrl ="https://test.orangehrmlive.com";
                break;
            case "Stagging":
                envUrl ="https://stagging.orangehrmlive.com/web/index.php/auth/login";
                break;
            case "Rrod":
                envUrl ="https://.orangehrmlive.com/web/index.php/auth/login";
                break;
        }
        return envUrl;
    }
    @BeforeSuite
    public void deleteFileInReport() {
        // Remove all file in ReportNG screenshot (image)
        //deleteAllFileInFolder("reportNGImage");

        // Remove all file in Allure attachment (json file)
        //deleteAllFileInFolder("htmlAllure");
    }

    public void deleteAllFileInFolder(String folderName) {
        try {
            String pathFolderDownload = GlobalConstants.PROJECT_PATH + File.separator + folderName;
            File file = new File(pathFolderDownload);
            File[] listOfFiles = file.listFiles();
            if (listOfFiles.length != 0) {
                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].isFile() && !listOfFiles[i].getName().equals("environment.properties")) {
                        new File(listOfFiles[i].toString()).delete();
                    }
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
}
