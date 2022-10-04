package me.junkxxl.testing.trelloandregard;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class BucketPage {
    @FindBy(how = How.XPATH, using = "//*[contains(@class, 'BasketItem_link')]")
    SelenideElement elementsNameProduct;

    @Step("Сохраняем название товара")
    public String saveNameProduct() {
        return elementsNameProduct.getAttribute("title");
    }

}
