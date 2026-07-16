package tests;

import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class ProductsTest extends BaseTest {

    @Test
    public void checkGoodsAdded() {
        List<String> goodsList =
                List.of(
                        "Sauce Labs Bolt T-Shirt",
                        "Sauce Labs Bike Light",
                        "Test.allTheThings() T-Shirt (Red)");

        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        assertEquals(productsPage.getTitle(), "Products", "Заголовок страницы не соответствует");

        for (String goods : goodsList) {
            productsPage.addGoodsToCart(goods);
        }

        assertEquals(productsPage.checkCounterColor(), "rgba(226, 35, 26, 1)");
        assertEquals(productsPage.getCounterValue(), "3");
        assertEquals(productsPage.isCartCounterDisplayed(), true);

        productsPage.switchToBasket();
        assertTrue(basketPage.getProductsNames().equals(goodsList));
        assertTrue(basketPage.getProductsNames().contains("Sauce Labs Bolt T-Shirt"));
    }

    @Test
    public void checkBasketContentsAfterAddingGoods() {
        List<String> goodsList2 = List.of(
                "Sauce Labs Backpack",
                "Sauce Labs Onesie",
                "Sauce Labs Fleece Jacket"
        );

        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        assertFalse(basketPage.isPageLoaded());
        assertEquals(productsPage.getTitle(), "Products", "Заголовок страницы не соответствует");

        for (String goods2 : goodsList2) {
            productsPage.addGoodsToCart(goods2);
        }

        productsPage.switchToBasket();

        assertEquals(basketPage.getProductsNames(), goodsList2,
                "Список товаров в корзине не совпадает с добавленными");

        assertTrue("Товар 'Sauce Labs Onesie' отсутствует в корзине",
                basketPage.getProductsNames().contains("Sauce Labs Onesie"));
    }
}
