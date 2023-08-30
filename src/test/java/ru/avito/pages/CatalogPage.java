package ru.avito.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

public class CatalogPage extends BasePage{
    @FindBy(xpath = "//strong[contains(@class, 'styles-module-root')]")
    public WebElement titleItemCard;
    @FindBys(@FindBy(xpath = "//strong[contains(@class, 'styles-module-root')]"))
    public List<WebElement> titleItemCards;
}