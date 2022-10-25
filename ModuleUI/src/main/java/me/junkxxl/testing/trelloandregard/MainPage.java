package me.junkxxl.testing.trelloandregard;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class MainPage {
    @FindBy(how = How.XPATH, using = "//*[@data-testid='language-select']")
    SelenideElement languageSelect;
    @FindBy(how = How.XPATH, using = "//span[text()='Кабинет']")
    SelenideElement elementButtonPersonalAccount;
    @FindBy(how = How.XPATH, using = "//span[text()='E-mail или номер телефона']/../input")
    SelenideElement elementLogin;
    @FindBy(how = How.XPATH, using = "//span[text()='Пароль']/../input")
    SelenideElement elementPassword;
    @FindBy(how = How.XPATH, using = "//span[text()='Название или ID товара']/../input[@id='searchInput']")
    SelenideElement elementSearch;
    @FindBy(how = How.XPATH, using = "//*[contains(@class, 'burgerButton')]")
    SelenideElement elementButtonCatalog;
    @FindBy(how = How.XPATH, using = "//*/a[contains(@class,'BasketItem_link')]")
    SelenideElement elementWindowBucket;
    @FindBy(how = How.XPATH, using = "//*[text()='Корзина']")
    SelenideElement elementBucket;


    @Step("Открываем сайт \"{0}\"")
    public static MainPage open(String s) {
        Selenide.open(s);
        return page(MainPage.class);
    }

    @Step("Нажимаем на вкладку выбора языка")
    public MainPage openLanguagesTab() {
        languageSelect.click();
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
        elementButtonPersonalAccount.click();
        return this;
    }

    @Step("Вводим почту \"{0}\"")
    public MainPage enteringMail(String mail) {
        elementLogin.setValue(mail);
        return this;
    }

    @Step("Вводим пароль \"{0}\"")
    public MainPage enteringPassword(String password) {
        elementPassword.setValue(password).pressEnter();
        return this;
    }

    @Step("Вводим данные \"{0}\" в поле поиска")
    public MainPage search(String title) {
        elementSearch.setValue(title).pressEnter();
        return this;
    }

    @Step("Кликаем по вкладке \"Каталог\"")
    public MainPage clickButtonCatalog() {
        elementButtonCatalog.click();
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
        elementBucket.click();
        return this;
    }

    @Step("Сохраняем название товара")
    @Attachment
    public String saveNameProduct() {
        return elementWindowBucket.shouldBe(Condition.visible).getAttribute("title");
    }
}
