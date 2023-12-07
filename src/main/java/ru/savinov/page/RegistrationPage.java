package ru.savinov.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RegistrationPage {
    public static final String REGISTRATION_URL = "https://stellarburgers.nomoreparties.site/register";
    @FindBy(how = How.XPATH, using = "//label[text()='Имя']/following-sibling::input")
    private SelenideElement nameInput;
    @FindBy(how = How.XPATH, using = "//label[text()='Email']/following-sibling::input")
    private SelenideElement emailInput;
    @FindBy(how = How.XPATH, using = "//label[text()='Пароль']/following-sibling::input")
    private SelenideElement passwordInput;
    @FindBy(how = How.XPATH, using = "//button[text()='Зарегистрироваться']")
    private SelenideElement buttonRegistration;
    @FindBy(how = How.XPATH, using = "//a[text()='Войти']")
    private SelenideElement buttonEnter;
    @FindBy(how = How.XPATH, using = "//p[text()='Такой пользователь уже существует']")
    private SelenideElement textErrorRepeatUser;
    @FindBy(how = How.XPATH, using = "//p[text()='Некорректный пароль']")
    private SelenideElement textErrorIncorrectPassword;
    @FindBy(how = How.XPATH, using = "//h2[text ='Регистрация']")
    private SelenideElement headerReg;

    @Step("Заполнение поля Имя")
    public RegistrationPage setNameInput(String name) {
        nameInput.sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
        nameInput.sendKeys(name);
        return this;
    }

    @Step("Заполнение поля Email")
    public RegistrationPage setEmailInput(String email) {
        emailInput.sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
        emailInput.sendKeys(email);
        return this;
    }

    @Step("Заполнение поля Пароль")
    public RegistrationPage setPasswordInput(String password) {
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys(Keys.SHIFT, Keys.HOME, Keys.DELETE);
        passwordInput.sendKeys(password);
        return this;
    }

    @Step("Клик по кнопке Зарегистрироваться")
    public RegistrationPage clickButtonRegistration() {
        buttonRegistration.shouldBe(Condition.visible).click();
        return this;
    }

    @Step("Ожидания открытия страницы регистрации")
    public RegistrationPage checkOpenPageRegistration() {
        buttonRegistration.shouldBe(Condition.visible);
        return this;
    }

    @Step("Ожидание закрытия страницы регистрации")
    public RegistrationPage checkClosingPageRegistration() {
        buttonRegistration.shouldBe(Condition.disappear);
        return this;
    }

    @Step("Ошибка ввода некорректного пароля")
    public boolean checkPasswordError() {
        return textErrorIncorrectPassword.shouldBe(Condition.exist).isDisplayed();
    }

    @Step("Клик по кнопке Войти")
    public RegistrationPage clickButtonEnterFromRegist() {
        buttonEnter.shouldBe(Condition.visible).click();
        return this;
    }

}


