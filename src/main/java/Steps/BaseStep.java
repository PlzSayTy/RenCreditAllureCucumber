package Steps;

import Utils.TestProperties;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseStep {
    protected static WebDriver driver;
    protected static String url;
    public static Properties properties = TestProperties.getINSTANCE().getProperties();
    public static Properties propertiesOfBrowser = new Properties();

    public static WebDriver getDriver(){
        return driver;
    }

    @Before
    public static void setUp() throws Exception{
        propertiesOfBrowser.load(new FileInputStream(new File("./target/test-classes/" + System.getProperty("browser", "browser") + ".properties")));
        switch (propertiesOfBrowser.getProperty("browser")){
            case "firefox":
                System.setProperty("webdriver.gecko.driver", properties.getProperty("webdriver.gecko.driver"));
                driver = new FirefoxDriver();
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome.driver"));
                driver = new ChromeDriver();
                break;
            default:
                System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome.driver"));
                driver = new ChromeDriver();
                break;
        }
        url = properties.getProperty("app.url");
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(url);
    }
    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] saveScreenshot() {
        return ((TakesScreenshot) BaseStep.getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @After
    public static void tearDown(Scenario sc) throws Exception {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(sc.isFailed()) saveScreenshot();
        driver.quit();
    }
}
