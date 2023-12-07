package ru.savinov.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PasswordRecoveryPage {
    public static final String RECOVER_PASSWORD_URL = "https://stellarburgers.nomoreparties.site/forgot-password";
    @FindBy(how = How.XPATH, using = "//a[text()='Войти']")
    private SelenideElement buttonLogin;

    @Step("Клик по кнопке Войти")
    public PasswordRecoveryPage clickButtonEnterFromRecoverPassword() {
        buttonLogin.shouldBe(Condition.visible).click();
        return this;
    }
}