import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import ru.savinov.page.MainPage;

import static com.codeborne.selenide.Selenide.*;

public class ConstructorTest extends BasedTest{
    @Test
    @DisplayName("Проверка переключения на вкладку 'Булки'")
    public void checkTabBuns() {
        open(MainPage.MAIN_PAGE_URL, MainPage.class).clickButtonSauce();
        page(MainPage.class).clickButtonBun();
        String expectedTab = "Булки";
        String actualTab = page(MainPage.class).checkSelectedTab();
        Assert.assertEquals("Таб булки не выбрался", expectedTab, actualTab);
    }

    @Test
    @DisplayName("Проверка переключения на вкладку 'Соусы'")
    public void checkTabSauce() {
        open(MainPage.MAIN_PAGE_URL, MainPage.class).clickButtonSauce();
        String expectedTab = "Соусы";
        String actualTab = page(MainPage.class).checkSelectedTab();
        Assert.assertEquals("Таб соусы не выбрался", expectedTab, actualTab);
    }

    @Test
    @DisplayName("Проверка переключения на вкладку 'Начинки'")
    public void checkTabFilling() {
        open(MainPage.MAIN_PAGE_URL, MainPage.class).clickButtonFilling();
        String expectedTab = "Начинки";
        String actualTab = page(MainPage.class).checkSelectedTab();
        Assert.assertEquals("Таб начинки не выбрался", expectedTab, actualTab);
    }

    @After
    public void cleanData() {
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }
}
