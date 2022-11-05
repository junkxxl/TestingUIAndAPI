package me.junkxxl.testing.trelloandregard;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class BucketPage {
    String elementsNameProduct =
            "//*[contains(@class, 'BasketItem_link')]";

    @Step("Сохраняем название товара")
    public String saveNameProduct() {
        return $(By.xpath(elementsNameProduct)).getAttribute("title");
    }
}
