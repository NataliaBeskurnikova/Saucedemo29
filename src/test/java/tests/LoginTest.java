package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static enums.TitleNaming.PRODUCTS;
import static org.testng.Assert.assertEquals;
import static user.UserFactory.*;

public class LoginTest extends BaseTest {

    @Test
    public void checkLogin() {
        System.out.println("LoginTest.checkLogin is running in Thread: "
                + Thread.currentThread().getId());
        loginPage
                .open()
                .login(withAdminPermission());

        assertEquals(productsPage.getTitle(), PRODUCTS.getDisplayName(),
                "Заголовок страницы не соответствует");
    }

    @DataProvider(name = "incorrectLoginData")
    public Object[][] loginData() {
        return new Object[][]{
                {withEmptyLoginPermission(), "Epic sadface: Username is required"},
                {withEmptyPasswordPermission(), "Epic sadface: Password is required"},
                {withLockedPermission(), "Epic sadface: Sorry, this user has been locked out."},
                {withInvalidLoginPermission(), "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    /*@Test(dataProvider = "incorrectLoginData")
    public void checkIncorrectLogin(String user, String password, String errorMessage) {
        loginPage.open();
        loginPage.login();

        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(loginPage.getErrorText(), errorMessage);
    }*/
}
