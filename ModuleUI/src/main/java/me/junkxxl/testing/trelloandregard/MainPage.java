package me.junkxxl.testing.trelloandregard;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    String languageSelect =
            "//*[@data-testid='language-select']";
    String elementButtonPersonalAccount =
            "//span[text()='Кабинет']";
    String elementLogin =
            "//span[text()='E-mail или номер телефона']/../input";
    String elementPassword =
            "//span[text()='Пароль']/../input";
    String elementSearch =
            "//span[text()='Название или ID товара']/../input[@id='searchInput']";
    String elementButtonCatalog =
            "//*[contains(@class, 'burgerButton')]";
    String elementWindowBucket =
            "//*/a[contains(@class,'BasketItem_link')]";
    String elementBucket =
            "//*[text()='Корзина']";

    @Step("Нажимаем на вкладку выбора языка")
    public MainPage openLanguagesTab() {
        $(By.xpath(languageSelect)).click();
        return this;
    }

    @Step("Выбираем \"{0}\" язык")
    public MainPage selectionLanguage(String language) {
        $(By.xpath("//*[@data-testid='language-select']/option[text()='" + language + "']")).click();
        return this;
    }

    @Step("Выбираем вкладку \"{0}\"")
    public MainPage selectButtonFeatures(String nameButton) {
        $(By.xpath("//button[text()='" + nameButton + "']")).click();
        return this;
    }

    @Step("Проверка элемента \"{0}\"")
    public MainPage checkingTranslationOfPopupElement(String nameElement) {
        $(By.xpath("//nav/a/div/p[text()='" + nameElement + "']")).shouldBe(Condition.visible);
        return this;
    }

    @Step("Кликаем на кнопку \"Кабинет\"")
    public MainPage clickButtonPersonalAccount() {
        $(By.xpath(elementButtonPersonalAccount)).click();
        return this;
    }

    @Step("Вводим почту \"{0}\"")
    public MainPage enteringMail(String mail) {
        $(By.xpath(elementLogin)).setValue(mail).click();
        return this;
    }

    @Step("Вводим пароль \"{0}\"")
    public MainPage enteringPassword(String password) {
        $(By.xpath(elementPassword)).setValue(password).pressEnter();
        return this;
    }

    @Step("Вводим данные \"{0}\" в поле поиска")
    public MainPage search(String title) {
        $(By.xpath(elementSearch)).setValue(title).pressEnter();
        return this;
    }

    @Step("Кликаем по вкладке \"Каталог\"")
    public MainPage clickButtonCatalog() {
        $(By.xpath(elementButtonCatalog)).click();
        return this;
    }

    @Step("Выбираем каталог \"{0}\"")
    public MainPage selectCategory(String nameCatalog) {
        $(By.xpath("//*[contains(text(), '" + nameCatalog + "') and contains(@class, 'Catalog') ]")).shouldBe(Condition.visible).hover();
        return this;
    }

    @Step("Выбираем подкаталог \"{0}\"")
    public MainPage selectStorageCategory(String nameStorageCategory) {
        $(By.xpath("//*[contains(text(), '" + nameStorageCategory + "') and contains(@class, 'Category_subTitle') ]")).shouldBe(Condition.visible).click();
        return this;
    }

    @Step("Кликаем на вкладку \"Корзина\"")
    public MainPage clickToBucket() {
        $(By.xpath(elementBucket)).click();
        return this;
    }

    @Step("Сохраняем название товара")
    @Attachment
    public String saveNameProduct() {
        return $(By.xpath(elementWindowBucket)).shouldBe(Condition.visible).getAttribute("title");
    }
}
