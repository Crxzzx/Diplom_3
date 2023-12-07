import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.savinov.api.User;
import ru.savinov.page.*;

import static com.codeborne.selenide.Selenide.*;

public class AuthorizationPageTest extends BasedTest {
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
    }

    public void checkLoginWithValidData() {
        page(AuthorizationPage.class)
                .setEmailInput(user.getEmail())
                .setPasswordInput(user.getPassword())
                .clickButtonLogin()
                .checkClosedAuthorizationPage()
                .checkOpenMainPage();
        String currentURL = webdriver().driver().url();
        Assert.assertEquals("Авторизация не прошла", MainPage.MAIN_PAGE_URL, currentURL);
    }

    @Test
    @DisplayName("Проверка входа по кнопке 'Войти в аккаунт' на главной")
    public void checkAuthOnMainPage() {
        open(MainPage.MAIN_PAGE_URL, MainPage.class).clickButtonEnterToAccount();
        checkLoginWithValidData();
    }

    @Test
    @DisplayName("Проверка входа через 'Личный кабинет'")
    public void checkAuthOnProfilePage() {
        open(MainPage.MAIN_PAGE_URL, HeaderPage.class).clickButtonProfilePage();
        checkLoginWithValidData();
    }

    @Test
    @DisplayName("Проверка входа через форму 'Регистрация'")
    public void checkAuthOnRegisterPage() {
        open(RegistrationPage.REGISTRATION_URL, RegistrationPage.class).clickButtonEnterFromRegist();
        checkLoginWithValidData();
    }

    @Test
    @DisplayName("Проверка входа через форму 'Восстановления пароля'")
    public void checkAuthOnRecoverPasswordPage() {
        open(PasswordRecoveryPage.RECOVER_PASSWORD_URL, PasswordRecoveryPage.class).clickButtonEnterFromRecoverPassword();
        checkLoginWithValidData();
    }


    @After
    public void cleanData() {
        if (user != null) {
            user.deleteUser();
        }
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }

}

