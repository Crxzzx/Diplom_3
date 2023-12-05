package ru.savinov.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class AuthorizationPage {
    public static final String AUTHORIZATION_URL = "https://stellarburgers.nomoreparties.site/login";
    @FindBy(how = How.XPATH, using = "//label[text()='Email']/following-sibling::input")
    private SelenideElement emailInput;
    @FindBy(how = How.XPATH, using = "//label[text()='Пароль']/following-sibling::input")
    private SelenideElement passwordInput;
    @FindBy(how = How.XPATH, using = "//button[text()='Войти']")
    private SelenideElement buttonEnter;
    @FindBy(how = How.XPATH, using = "//h2[text()='Вход']")
    private SelenideElement headerLogIn;

    @Step("Заполнение поля Email")
    public AuthorizationPage setEmailInput(String email) {
        emailInput.click();
        emailInput.clear();
        emailInput.sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
        emailInput.sendKeys(email);
        return this;
    }

    @Step("Заполнение поля Пароль")
    public AuthorizationPage setPasswordInput(String password) {
        passwordInput.sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
        passwordInput.sendKeys(password);
        return this;
    }

    @Step("Клик по кнопке Войти")
    public AuthorizationPage clickButtonLogin() {
        buttonEnter.shouldBe(Condition.visible).click();
        return this;
    }

    @Step("Ожидание перехода на страницу Вход")
    public AuthorizationPage mainPageLoaded() {
        headerLogIn.shouldBe(Condition.visible);
        return this;
    }

    @Step("Проверка закрытия страницы авторизации")
    public MainPage checkClosedAuthorizationPage() {
        headerLogIn.should(Condition.disappear);
        return page(MainPage.class);
    }
}
