package me.junkxxl.testing.trelloandregard;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.junit5.TextReportExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith({TextReportExtension.class})
@DisplayName("Тесты для работы с UI")
class TrelloAndRegardTest extends Quit {

    @Test
    @DisplayName("Проверка локализации сайта на примере русского и украинского языка")
    void LocalizationTest() {
        Selenide.open("https://trello.com/");
        var mainPage = new MainPage();
        mainPage.openLanguagesTab();
        mainPage.selectionLanguage("Русский");
        mainPage.selectButtonFeatures("Возможности");
        mainPage.checkingTranslationOfPopupElement("Автоматизация");
        mainPage.selectionLanguage("Українська");
        mainPage.selectButtonFeatures("Функції");
        mainPage.checkingTranslationOfPopupElement("Шаблони");
    }

    @Test
    @DisplayName("Проверка аутентификации")
    void AuthTest() {
        Selenide.open("https://www.regard.ru/");
        var mainPage = new MainPage();
        mainPage.clickButtonPersonalAccount();
        mainPage.enteringMail(System.getenv("Email"));
        mainPage.enteringPassword(System.getenv("Pass"));
        mainPage.clickButtonPersonalAccount();

        var profilePage = new ProfilePage();
        profilePage.checkingProfile(System.getenv("Email"));
    }

    @Test
    @DisplayName("Проверка сайта, на добавление выбранного CPU с рядом параметров, в корзину")
    void СpuPurchaseTest() {
        Selenide.open("https://www.regard.ru/");
        MainPage mainPage = new MainPage();
        mainPage.search("Ryzen 5");

        var foundProductsPage = new FoundProductsPage();
        foundProductsPage.selectingSort("Сначала с низкой ценой");
        String nameProduct = foundProductsPage.saveNameProduct(3);
        foundProductsPage.selectProduct(3);

        ProductPage productPage = new ProductPage();
        productPage.addBucket();
        productPage.selectBucket();

        BucketPage bucketPage = new BucketPage();
        String resultNameProduct = bucketPage.saveNameProduct();
        assertEquals(nameProduct, resultNameProduct, "Продукт в корзине не соответствует продукту добавленному изначально");
    }

    @Test
    @DisplayName("Проверка сайта, на добавление выбранного USB Flash в корзину")
    void flashStoragePurchaseTest() {
        Selenide.open("https://www.regard.ru/");
        MainPage mainPage = new MainPage();
        mainPage.clickButtonCatalog();
        mainPage.selectCategory("Накопители данных");
        mainPage.selectStorageCategory("USB Flash");

        UsbFlashPage usbFlashPage = new UsbFlashPage();
        usbFlashPage.selectionManufacturer("SanDisk");
        String nameProduct = usbFlashPage.saveNameProduct(6);
        usbFlashPage.addBucket(6);
        usbFlashPage.clickToHomePage();

        mainPage.clickToBucket();
        String resultNameProduct = mainPage.saveNameProduct();
        assertEquals(nameProduct, resultNameProduct, "Продукт в корзине не соответствует продукту добавленному изначально");
    }
}
