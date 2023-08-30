package ru.avito.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import ru.avito.steps.BaseSteps;
import ru.avito.steps.CatalogSteps;
import ru.avito.steps.ItemCardSteps;

import java.time.Duration;

import java.util.logging.Level;

public abstract class BaseTest {

    protected static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
    protected static ThreadLocal<WebDriverWait> threadLocalWait = new ThreadLocal<>();
    protected static ThreadLocal<SoftAssert> threadLocalSoftAssert = new ThreadLocal<>();
    public static WebDriver driver;
    public static WebDriverWait wait;
    protected BaseSteps baseSteps;
    protected ItemCardSteps itemCardSteps;
    protected CatalogSteps catalogSteps;
    public static Duration timeoutForImplicitlyWait = Duration.ofSeconds(10);
    public static Duration timeoutForExplicitlyWait = Duration.ofSeconds(30);

    @BeforeSuite
    @Parameters("type")
    public BaseTest setUpSuite(String type) {
        if (type.equalsIgnoreCase("consistent")) {
            driver = doBrowserSetup("chrome");
            threadLocalDriver.set(driver);
            wait = new WebDriverWait(driver, timeoutForExplicitlyWait);
            threadLocalWait.set(wait);
        }
        return this;
    }

    @BeforeClass
    @Parameters("type")
    public void setUpClass(String type){
        if (type.equalsIgnoreCase("consistent")) {
            baseSteps = new BaseSteps();
            itemCardSteps = new ItemCardSteps();
            catalogSteps = new CatalogSteps();
        }
    }

    @BeforeMethod ()
    @Parameters("type")
    public BaseTest setUpMethod(String type) {

        SoftAssert softAssert = new SoftAssert();
        threadLocalSoftAssert.set(softAssert);
        return this;
    }

    @AfterMethod
    @Parameters("type")
    public BaseTest shutDownMethod(String type) {
        threadLocalSoftAssert.remove();
        return this;
    }

    @AfterSuite
    @Parameters("type")
    public BaseTest shutDownSuite(String type) {
        if (type.equalsIgnoreCase("consistent")) {
            driver.quit();
        }
        return this;
    }

    public static WebDriver getDriver(){
        return threadLocalDriver.get();
    }
    public static WebDriverWait getWait(){
        return threadLocalWait.get();
    }
    public static SoftAssert getSoftAssert(){
        return threadLocalSoftAssert.get();
    }

    public static WebDriver doBrowserSetup(String browserName) {

        WebDriver driver = null;
        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1800,1000");
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--disable-gpu");

            LoggingPreferences logPrefs = new LoggingPreferences();
            logPrefs.enable(LogType.PERFORMANCE, Level.ALL);

            options.setCapability("goog:loggingPrefs", logPrefs);

            DriverListener listener = new DriverListener();
            EventFiringDecorator<WebDriver> decorator = new EventFiringDecorator<>(listener);
            driver = decorator.decorate(new ChromeDriver(options));
            driver.manage().timeouts().implicitlyWait(timeoutForImplicitlyWait);
        }
        return driver;
    }
}
