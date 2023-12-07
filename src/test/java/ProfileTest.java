import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.savinov.api.User;
import ru.savinov.page.AuthorizationPage;
import ru.savinov.page.HeaderPage;
import ru.savinov.page.ProfilePage;
import ru.savinov.page.RegistrationPage;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class ProfileTest extends BasedTest {
    List<User> users = new ArrayList<>();
    private User user;

    @Before
    @DisplayName("Создание случайного пользователя и авторизация")
    public void registrationUser() {
        user = User.generateRandomUserValidData();
        open(RegistrationPage.REGISTRATION_URL, RegistrationPage.class)
                .setNameInput(user.getName())
                .setEmailInput(user.getEmail())
                .setPasswordInput(user.getPassword())
                .clickButtonRegistration()
                .checkClosingPageRegistration();
        users.add(user);
        open(AuthorizationPage.AUTHORIZATION_URL, AuthorizationPage.class).setEmailInput(user.getEmail()).setPasswordInput(user.getPassword()).clickButtonLogin().checkClosedAuthorizationPage();
    }

    @Test
    @DisplayName("Проверка перехода в 'Личный кабинет'")
    public void checkIntoProfilePage() {
        page(HeaderPage.class).clickButtonProfilePage();
        page(ProfilePage.class).checkOpenProfilePage();
        String currentURL = webdriver().driver().url();
        Assert.assertEquals("Переход в Личный кабинет не выполнился", ProfilePage.PROFILE_URL, currentURL);
    }

    @After
    @DisplayName("Удаление пользователя и очистка cookies")
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
