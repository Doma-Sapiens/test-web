package ru.avito.steps;

import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.avito.objects.ItemCard;
import ru.avito.pages.ItemCardPage;

public class ItemCardSteps extends BaseSteps {
    ItemCardPage itemCardPage = new ItemCardPage();
    public void addToFavorites(ItemCard itemCard) {

        wait.until(ExpectedConditions.visibilityOf(itemCardPage.titleItemCard));
        itemCard.setTitle(itemCardPage.titleItemCard.getText());
        wait.until(ExpectedConditions.elementToBeClickable(itemCardPage.favoriteButton));
        itemCardPage.favoriteButton.click();
        wait.until(ExpectedConditions.invisibilityOf(itemCardPage.spinner));
        verifyElementPresent(itemCardPage.favoriteTooltip, "Тултип добавления в Избранное");
        verifyElementText(itemCardPage.favoriteButton, "В избранном", "Карточка объявления", "Название кнопки");
    }

}
