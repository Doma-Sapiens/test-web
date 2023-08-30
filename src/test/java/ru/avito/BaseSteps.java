package ru.avito.steps;

import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.avito.pages.BasePage;
import static ru.avito.tests.BaseTest.*;

public class BaseSteps extends BasePage {

    public void assertElementPresent(boolean isElementPresent, String pageName, String elementName) {
        getSoftAssert().assertTrue(isElementPresent,
                pageName + " Элемент '" + elementName + "' должен отображаться, но его нет.");
    }

    public boolean verifyElementPresent(WebElement webElement, String elementName) {
        boolean present = true;
        try {
            changeTimeout(15);
            wait.until(ExpectedConditions.visibilityOf(webElement));
        } catch (Exception e) {
            present = false;
            Allure.getLifecycle().addAttachment("Скриншот при отсутствии элемента " + elementName, "image/png", "png",
                    ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
            getSoftAssert().fail(" Элемент '" + elementName + "' должен отображаться, но его нет.");
        }
        changeTimeoutToDefault();
        return present;
    }

    public void verifyElementText(WebElement webElement, String getElement, String pageName, String elementName) {
        boolean present = verifyElementPresent(webElement, elementName); //Проверка наличия на странице
        if (present) {
            String actual = webElement.getText();
            assertElementText(actual, getElement, pageName, elementName);
        }
    }

    public void assertElementText(String actualText, String expectedText, String pageName, String elementName) {
        getSoftAssert().assertEquals(actualText, expectedText,
                pageName + " Текст у элемента '" + elementName + "' не соответствует ожидаемому.");
    }
}