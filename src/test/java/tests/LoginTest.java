package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.User;

import static enums.TitleNaming.PRODUCTS;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.*;

@Epic("Авторизация и доступ к системе")
@Feature("Авторизация пользователя")
@Owner("Beskurnikova Natalia bskurnikova@yandex.ru")
public class LoginTest extends BaseTest {

    @Story("Проверка сценариев входа в систему")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("Saucedemo29")
    @Issue("AiAgentBBS")
    @Test(description = "Проверка корректной авторизации", priority = 1)
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

    @Story("Обработка ошибок при некорректной авторизации")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("Saucedemo29")
    @Issue("AiAgentBBS")
    @Test(dataProvider = "incorrectLoginData", priority = 2, enabled = true)
    public void checkIncorrectLogin(User user, String errorMessage) {
        loginPage.open();
        loginPage.login(user);

        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(loginPage.getErrorText(), errorMessage);
    }
}
