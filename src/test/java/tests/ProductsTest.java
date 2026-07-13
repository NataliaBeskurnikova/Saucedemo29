package tests;

import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class ProductsTest extends BaseTest {
    @Test
    public void checkGoodsAdded() {

        List<String> goddsList =
                List.of("Sauce Labs Bolt T-Shirt",
                        "Sauce Labs Bike Light",
                        "Test.allTheThings() T-Shirt (Red)");

        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        assertEquals(productsPage.getTitle(), "Products", "Заголовок страницы не соответствует");
        productsPage.addGoodsToCart(4);

        for (String goods : goddsList) {
            productsPage.addGoodsToCart(goods);
        }


        assertEquals(productsPage.checkCounterValue(), "rgba(226, 35, 26, 1)");
        assertEquals(productsPage.isCartCounterDisplayed(), true);
        assertEquals(productsPage.getCounterValue(), "2");
        assertEquals(productsPage.checkCounterValue(), "4");
    }
}
