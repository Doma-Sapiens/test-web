package ru.avito.tests;

import org.testng.annotations.Test;
import ru.avito.objects.ItemCard;

public class AddToFavoritesTest extends BaseTest {

    ItemCard itemCard = new ItemCard();
    @Test
    public void testAddToFavorites() {
        driver.get("https://www.avito.ru/nikel/knigi_i_zhurnaly/domain-driven_design_distilled_vaughn_vernon_2639542363");
        itemCardSteps.addToFavorites(itemCard);
        driver.get("https://www.avito.ru/favorites");
        catalogSteps.verifyFavoriteListContainsItemCard(itemCard);
        getSoftAssert().assertAll();
    }
}
