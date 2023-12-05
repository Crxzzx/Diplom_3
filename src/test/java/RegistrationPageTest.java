import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import ru.savinov.api.User;
import ru.savinov.page.AuthorizationPage;
import ru.savinov.page.RegistrationPage;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;

public class RegistrationPageTest extends BasedTest {
    List<User> users = new ArrayList<>();
    private User user;

    @Test
    @DisplayName("Регистрация с валидными данными")
    public void registrationValidData() {
        user = User.generateRandomUserValidData();
        open(RegistrationPage.REGISTRATION_URL, RegistrationPage.class)
                .setNameInput(user.getName())
                .setEmailInput(user.getEmail())
                .setPasswordInput(user.getPassword())
                .clickButtonRegistration()
                .checkClosingPageRegistration();
        users.add(user);
        String currentURL = webdriver().driver().url();
        assertEquals("Пользователь не создался", AuthorizationPage.AUTHORIZATION_URL, currentURL);
    }

    @Test
    @DisplayName("Регистрация с невалидными данными")
    public void registrationWithIncorrectPassword() {
        user = User.generateRandomUserValidData();
        user.setPassword(User.generateBrokePassword());
        boolean isErrorMessageDisplayed =
                open(RegistrationPage.REGISTRATION_URL, RegistrationPage.class)
                        .setNameInput(user.getName())
                        .setEmailInput(user.getEmail())
                        .setPasswordInput(user.getPassword())
                        .clickButtonRegistration()
                        .checkPasswordError();
        users.add(user);
        Assert.assertTrue("Ошибка не отобразилась", isErrorMessageDisplayed);
    }

    @After
    public void cleanData() {
        for (User user : users) {
            if (user != null) {
                user.deleteUser();
            }
            clearBrowserCookies();
            clearBrowserLocalStorage();
        }

    }
}

