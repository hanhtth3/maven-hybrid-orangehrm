package core;

import java.io.File;

public class GlobalConstants {
    // System Infor
    public static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final String OS_NAME = System.getProperty("os.name");
    public static final String JAVA_VERSION = System.getProperty("java.version");

    // App Infor User
    public static final String DEV_USER_URL = "http://dev.techpanda.org/";
    public static final String STAGING_USER_URL = "http://staging.techpanda.org/";
    public static final String LIVE_USER_URL = "http://live.techpanda.org/";

    // App Infor Admin
    public static final String DEV_ADMIN_URL = "http://dev.techpanda.org/index.php/backendlogin";
    public static final String STAGING_ADMIN_URL = "http://staging.techpanda.org/index.php/backendlogin";
    public static final String LIVE_ADMIN_URL = "http://live.techpanda.org/index.php/backendlogin";

    public static final String ADMIN_USERNAME = "user01";
    public static final String ADMIN_PASSWORD = "guru99com";

    // Wait Infor
    public static final int SHORT_TIMEOUT = 10;
    public static final int LONG_TIMEOUT = 30;

    // Download/ Upload file
    public static final String UPLOAD_PATH = PROJECT_PATH + File.separator+"uploadFiles"+File.separator;
    public static final String DOWNLOAD_PATH = PROJECT_PATH + File.separator + "downloadFiles" +File.separator;

    // Retry Case Failed
    public static final int RETRY_NUMBER = 3;

    // Browser Logs/ Extension
    public static final String BROWSER_LOG_PATH = PROJECT_PATH + File.separator+ "browserLogs"+ File.separator;
    public static final String BROWSER_EXTENSION_PATH = PROJECT_PATH + File.separator+ "browserExtensions"+ File.separator;

    // HTML Report Folder
    public static final String REPORTNG_PATH = PROJECT_PATH + File.separator+ "htmlReportNG"+ File.separator;
    public static final String EXTENT_PATH = PROJECT_PATH+ File.separator + "htmlExtent"+ File.separator;
    public static final String ALLURE_PATH = PROJECT_PATH + File.separator+ "htmlAllure"+ File.separator;

    // Data Test/ Environment
    public static final String DATA_TEST_PATH = PROJECT_PATH + File.separator+ "dataTest"+ File.separator;
    public static final String ENVIRONMENT_CONFIG_PATH = PROJECT_PATH + File.separator+ "environmentConfig"+ File.separator;

    public static final String JIRA_SITE_URL = "https://hanhtth3.atlassian.net/";

    public static final String JIRA_USERNAME ="hanhtth3@gmail.com" ;

    public static final String JIRA_API_KEY = "ATATT3xFfGF0QbgVfIto-r4hUiZKTYnBf2tRfIYMT52q__r6I-Det5-RkHbZ0hbgZAekXi8l7aDxsvL-qX0K6s7Tgr_FBYhcr217hbR88YbFd07SmY1hZa_Ft786FSGDgC3J524BkaNfsZeHq3HFR92GQShOz8SIhZdQANgbu9b9ct5V7x4CpTw=4E2D9582";

    public static String JIRA_PROJECT_KEY="AUTOMATION" ;
}