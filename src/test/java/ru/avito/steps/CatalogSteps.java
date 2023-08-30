package ru.avito.steps;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.avito.objects.ItemCard;
import ru.avito.pages.CatalogPage;

public class CatalogSteps extends BaseSteps{
    CatalogPage catalogPage = new CatalogPage();

    public void verifyFavoriteListContainsItemCard(ItemCard itemCard) {

        wait.until(ExpectedConditions.visibilityOf(catalogPage.titleItemCard));
        boolean isItemCardFound = false;
        for (WebElement item : catalogPage.titleItemCards) {
            String actualItemCardTitle = item.getText();
            if (actualItemCardTitle.contains(itemCard.getTitle() + "а")) {
                isItemCardFound = true;
                break;
            }
        }
        assertElementPresent(isItemCardFound, "Список объявлений", "Название объявления");
    }
}
