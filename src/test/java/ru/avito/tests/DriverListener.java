package ru.avito.tests;

import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class DriverListener extends BaseTest implements WebDriverListener {

    private static Logger logger = LoggerFactory.getLogger(WebDriver.class);

    @Override
    public void beforeClick (WebElement element) {
        Allure.step("Клик на " + element.getText());
    }

    @Override
    public void beforeSendKeys (WebElement element, CharSequence... keysToSend) {
        Allure.step("Вставляем текст " + Arrays.toString(keysToSend));
    }

}
