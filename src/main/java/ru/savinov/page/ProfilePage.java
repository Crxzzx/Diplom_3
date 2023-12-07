package ru.savinov.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ProfilePage {
    public static final String PROFILE_URL = "https://stellarburgers.nomoreparties.site/account/profile";
    @FindBy(how = How.XPATH, using = "//a[text()='Профиль']")
    private SelenideElement textProfile;
    @FindBy(how = How.XPATH, using = "//p[text() = 'Конструктор']")
    private SelenideElement buttonConstructor;
    @FindBy(how = How.XPATH, using = "//button[text() = 'Выход']")
    private SelenideElement buttonLogOut;

    @Step("Проверка загрузки Личный профиль")
    public ProfilePage checkOpenProfilePage() {
        textProfile.shouldBe(Condition.exist).isDisplayed();
        return this;
    }

    @Step("Нажатие на кноку Констуктор")
    public ProfilePage clickButtonConstructor() {
        buttonConstructor.shouldBe(Condition.visible).click();
        return this;
    }

    @Step("Нажатие на кнопку Выход")
    public ProfilePage clickButtonLogOutUser() {
        buttonLogOut.shouldBe(Condition.visible).click();
        return this;
    }

    @Step("Ожидание закрытия профиля")
    public ProfilePage waitClosedProfilePage() {
        buttonLogOut.shouldBe(Condition.disappear);
        return this;
    }
}

