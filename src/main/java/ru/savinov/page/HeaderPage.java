package ru.savinov.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HeaderPage {
    public static final String AUTHORIZATION_URL = "https://stellarburgers.nomoreparties.site/";
    @FindBy(how = How.XPATH, using = "//p[text()='Личный Кабинет']")
    private SelenideElement buttonProfilePage;
    @FindBy(how = How.XPATH, using = "//div[contains(@class , 'logo')]/a[contains(@href ,'/')]")
    private SelenideElement headerLogo;

    @Step("Нажатие на кнопку личный кабинет")
    public HeaderPage clickButtonProfilePage() {
        buttonProfilePage.shouldBe(Condition.visible).click();
        return this;
    }

    @Step("Нажатие на лого")
    public HeaderPage clickHeaderLogo() {
        headerLogo.shouldBe(Condition.visible).click();
        return this;
    }
}
