package me.junkxxl.testing.trelloandregard;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class FoundProductsPage {
    String elementsCollection =
            "//div[contains(@class, 'Card_id')]/../div/a/h6[contains(@title, 'Ryzen 5')]";
    String elementSelectableList =
            "//span[@class='SelectableList_title__2giSh']";

    @Step("Выбираем сортировку \"{0}\"")
    public FoundProductsPage selectingSort(String sort) {
        $(By.xpath(elementSelectableList)).shouldBe(Condition.visible).click();
        $(By.xpath("//li/span[text()='" + sort + "']")).shouldBe(Condition.visible).click();
        return this;
    }

    @Step("Сохраняем название товара")
    public String saveNameProduct(int numberProduct) {
        return $$(By.xpath(elementsCollection)).get(numberProduct).shouldBe(Condition.visible).getAttribute("title");
    }

    @Step("Выбираем товар под номером \"{0}\"")
    public FoundProductsPage selectProduct(int numberProduct) {
        $$(By.xpath(elementsCollection)).get(numberProduct).shouldBe(Condition.visible).click();
        return this;
    }

}
