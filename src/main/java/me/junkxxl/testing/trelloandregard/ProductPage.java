package me.junkxxl.testing.trelloandregard;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ProductPage {
    @FindBy(how = How.XPATH, using = "//*[text()='Добавить в корзину']")
    SelenideElement elementsAddBucket;
    @FindBy(how = How.XPATH, using = "//*[text()='Корзина']")
    SelenideElement elementsBucket;


    @Step("Кликаем на кнопку добавить в корзину")
    public ProductPage addBucket() {
        elementsAddBucket.click();
        return this;
    }

    @Step("Кликаем на кнопку корзина")
    public ProductPage selectBucket() {
        elementsBucket.doubleClick();
        return this;
    }
}
