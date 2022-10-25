package me.junkxxl.testing.trelloandregard;

import com.codeborne.selenide.junit5.TextReportExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.page;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith({TextReportExtension.class})
@DisplayName("Тесты для работы с UI")
public class TrelloAndRegardTest extends Quit {

    @Test
    @DisplayName("Проверка локализации сайта на примере русского и украинского языка")
    void LocalizationTest() {
        MainPage.open("https://trello.com/")
                .openLanguagesTab()
                .selectionLanguage("Русский")
                .selectButtonFeatures("Возможности")
                .checkingTranslationOfPopupElement("Автоматизация")
                .selectionLanguage("Українська")
                .selectButtonFeatures("Функції")
                .checkingTranslationOfPopupElement("Шаблони");
    }

    @Test
    @DisplayName("Проверка аутентификации")
    void AuthTest() {
        MainPage.open("https://www.regard.ru/")
                .clickButtonPersonalAccount()
                .enteringMail(System.getenv("Login"))
                .enteringPassword(System.getenv("Email"))
                .clickButtonPersonalAccount();

        var profilePage = page(ProfilePage.class);
        profilePage.checkingProfile(System.getenv("Login"));

    }

    @Test
    @DisplayName("Проверка сайта, на добавление выбранного CPU с рядом параметров, в корзину")
    void СpuPurchaseTest() {
        MainPage.open("https://www.regard.ru/")
                .search("Ryzen 5");

        var foundProductsPage = page(FoundProductsPage.class);

        foundProductsPage.selectingSort("Сначала с низкой ценой");
        String nameProduct = foundProductsPage.saveNameProduct(3);
        foundProductsPage.selectProduct(3);

        ProductPage productPage = page(ProductPage.class);
        productPage
                .addBucket()
                .selectBucket();
        String resultNameProduct = page(BucketPage.class).saveNameProduct();
        assertEquals(nameProduct, resultNameProduct, "Продукт в корзине не соответствует продукту добавленному изначально");
    }

    @Test
    @DisplayName("Проверка сайта, на добавление выбранного USB Flash в корзину")
    void flashStoragePurchaseTest() {
        MainPage mainPage = MainPage.open("https://www.regard.ru/")
                .clickButtonCatalog()
                .selectCategory("Накопители данных")
                .selectStorageCategory("USB Flash");

        UsbFlashPage usbFlashPage = page(UsbFlashPage.class);
        usbFlashPage.selectionManufacturer("SanDisk");
        String nameProduct = usbFlashPage.saveNameProduct(6);
        usbFlashPage.addBucket(6);
        usbFlashPage.clickToHomePage();

        mainPage.clickToBucket();
        String resultNameProduct = mainPage.saveNameProduct();
        assertEquals(nameProduct, resultNameProduct, "Продукт в корзине не соответствует продукту добавленному изначально");
    }
}
