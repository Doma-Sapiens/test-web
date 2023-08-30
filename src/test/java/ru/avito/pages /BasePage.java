package ru.avito.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.avito.tests.BaseTest;
import java.time.Duration;
import static ru.avito.tests.BaseTest.*;

public abstract class BasePage {

    public WebDriver driver;
    public WebDriverWait wait;

    public BasePage() {
        this.driver = BaseTest.getDriver();
        PageFactory.initElements(driver, this);
        this.wait = BaseTest.getWait();
    }

    // Возврат к таймауту по умолчанию
    public void changeTimeoutToDefault() {
        driver.manage().timeouts().implicitlyWait(timeoutForImplicitlyWait);
        wait = new WebDriverWait(driver, timeoutForExplicitlyWait);
    }

    public void changeTimeout(int seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }
}
