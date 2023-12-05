import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.savinov.api.User;
import ru.savinov.page.*;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class MainPageTest extends BasedTest{
    List<User> users = new ArrayList<>();
    private User user;

    @Before
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
    @DisplayName("Проверка перехода из 'Личный кабинет' в конструктор")
    public void checkIntoConstructor() {
        page(HeaderPage.class).clickButtonProfilePage();
        page(ProfilePage.class).clickButtonConstructor();
        page(MainPage.class).checkOpenMainPage();
        String currentURL = webdriver().driver().url();
        Assert.assertEquals("Переход в конструктор не выполнился", MainPage.MAIN_PAGE_URL, currentURL);
    }

    @Test
    @DisplayName("Проверка перехода по клику на лого главной страницы в конструктор")
    public void checkClickOnHeaderLogo() {
        page(HeaderPage.class).clickButtonProfilePage();
        page(HeaderPage.class).clickHeaderLogo();
        page(MainPage.class).checkOpenMainPage();
        String currentURL = webdriver().driver().url();
        Assert.assertEquals("Переход в на главную страницу не выполнился", MainPage.MAIN_PAGE_URL, currentURL);
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
