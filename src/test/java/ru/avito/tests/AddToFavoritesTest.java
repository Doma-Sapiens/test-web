package ru.avito.tests;

import org.testng.annotations.Test;
import ru.avito.objects.ItemCard;

public class AddToFavoritesTest extends BaseTest {
    private static final String ITEM_PAGE_URL = "https://www.avito.ru/nikel/knigi_i_zhurnaly/domain-driven_design_distilled_vaughn_vernon_2639542363";
    private static final String FAVORITES_URL = "https://www.avito.ru/favorites";
    ItemCard itemCard = new ItemCard();
    @Test
    public void testAddToFavorites() {
        driver.get(ITEM_PAGE_URL);
        itemCardSteps.addToFavorites(itemCard);
        driver.get(FAVORITES_URL);
        catalogSteps.verifyFavoriteListContainsItemCard(itemCard);
        getSoftAssert().assertAll();
    }
}
