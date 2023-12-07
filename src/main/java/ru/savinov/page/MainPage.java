package ru.savinov.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MainPage {
    public static final String MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/";
    @FindBy(how = How.XPATH, using = "//button[text()='Войти в аккаунт']")
    private SelenideElement buttonEnterToAccount;
    @FindBy(how = How.XPATH, using = "//span[text()='Булки']")
    private SelenideElement tabBun;
    @FindBy(how = How.XPATH, using = "//span[text()='Соусы']")
    private SelenideElement tabSauce;
    @FindBy(how = How.XPATH, using = "//span[text()='Начинки']")
    private SelenideElement tabFilling;
    @FindBy(how = How.XPATH, using = "//div[contains(@class , 'current')]/span")
    private SelenideElement selectedTab;
    @FindBy(how = How.XPATH, using = "//h1[text()='Соберите бургер']")
    private SelenideElement textMainPage;

    @Step("Получение текста выбранного раздела")
    public String checkSelectedTab() {
        return selectedTab.getText();
    }

    @Step("Выбор раздела булки")
    public MainPage clickButtonBun() {
        tabBun.shouldBe(Condition.enabled).click();
        return this;
    }

    @Step("Выбор раздела булки")
    public MainPage clickButtonSauce() {
        tabSauce.shouldBe(Condition.enabled).click();
        return this;
    }

    @Step("Выбор раздела булки")
    public MainPage clickButtonFilling() {
        tabFilling.shouldBe(Condition.enabled).click();
        return this;
    }

    @Step("Нажатие на кнопку Войти в аккаунт")
    public MainPage clickButtonEnterToAccount() {
        buttonEnterToAccount.shouldBe(Condition.visible).click();
        return this;
    }

    @Step("Проверка загрузки главной страницы")
    public MainPage checkOpenMainPage() {
        textMainPage.shouldBe(Condition.visible);
        return this;
    }
}

