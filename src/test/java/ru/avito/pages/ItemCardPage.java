package ru.avito.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ItemCardPage extends BasePage {
    @FindBy(xpath = "(//button[@data-marker='item-view/favorite-button'])[2]")
    public WebElement favoriteButton;

    @FindBy(xpath = "//*[@data-icon='spinner']")
    public WebElement spinner;

    @FindBy(css = "[data-popper-placement='bottom-start']")
    public WebElement favoriteTooltip;

    @FindBy(xpath = "//*[@data-marker='item-view/title-info']")
    public WebElement titleItemCard;
}