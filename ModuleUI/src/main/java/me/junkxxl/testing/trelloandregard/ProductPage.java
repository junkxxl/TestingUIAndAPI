package me.junkxxl.testing.trelloandregard;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ProductPage {
    String elementsAddBucket =
            "//*[text()='Добавить в корзину']";
    String elementsBucket =
            "//*[text()='Корзина']";

    @Step("Кликаем на кнопку добавить в корзину")
    public ProductPage addBucket() {
        $(By.xpath(elementsAddBucket)).click();
        return this;
    }

    @Step("Кликаем на кнопку корзина")
    public ProductPage selectBucket() {
        $(By.xpath(elementsBucket)).doubleClick();
        return this;
    }
}
