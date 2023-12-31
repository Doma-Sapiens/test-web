package ru.avito.tests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.util.List;

import static ru.avito.tests.BaseTest.getDriver;

public class TestListener implements ITestListener{

    @Override
    public void onTestFailure(ITestResult result) {
        Allure.getLifecycle().addAttachment("Скриншот на месте падения теста", "image/png", "png",
                ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES));
        List<LogEntry> logList = getDriver().manage().logs().get(LogType.BROWSER).getAll();
        StringBuilder sb = new StringBuilder();

        for(Object line : logList) {
            sb.append(line);
            sb.append("\n");
        }

        Allure.addAttachment("Логи после падения теста: ", String.valueOf(sb));

        List<LogEntry> logs = getDriver().manage().logs().get(LogType.PERFORMANCE).getAll();
        StringBuilder logsBrowser = new StringBuilder();

        for (LogEntry le : logs) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonParser jp = new JsonParser();
            JsonElement je = jp.parse(le.getMessage());
            if (gson.toJson(je).contains("\"statusCode\": 404")) logsBrowser.append(gson.toJson(je));
        }
        Allure.addAttachment("Запросы после падения теста1: ", logsBrowser.toString());
    }
    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        Allure.getLifecycle().addAttachment("Скриншот на месте падения теста", "image/png", "png",
                ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES));
        Allure.addAttachment("Логи после падения теста: ",
                String.valueOf(getDriver().manage().logs().get(LogType.BROWSER).getAll()));
    }
    @Override
    public void onTestSuccess(ITestResult result) {
        Allure.getLifecycle().addAttachment("Скриншот после успешного прохождения теста", "image/png", "png",
                ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES));
        Allure.addAttachment("Логи после успешного прохождения теста: ",
                String.valueOf(getDriver().manage().logs().get(LogType.BROWSER).getAll()));
    }
}