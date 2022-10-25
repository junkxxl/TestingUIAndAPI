package me.junkxxl.testing.trelloandregard;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.$;

public class FoundProductsPage {
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'Card_id')]/../div/a/h6[contains(@title, 'Ryzen 5')]")
    ElementsCollection elementsCollection;

    @Step("Выбираем сортировку \"{0}\"")
    public FoundProductsPage selectingSort(String sort) {
        $(By.xpath("//span[@class='SelectableList_title__2giSh']")).shouldBe(Condition.visible).click();
        $(By.xpath("//li/span[text()='" + sort + "']")).shouldBe(Condition.visible).click();
        return this;
    }

    @Step("Сохраняем название товара")
    public String saveNameProduct(int numberProduct) {
        return elementsCollection.get(numberProduct).shouldBe(Condition.visible).getAttribute("title");
    }

    @Step("Выбираем товар под номером \"{0}\"")
    public FoundProductsPage selectProduct(int numberProduct) {
        elementsCollection.get(numberProduct).shouldBe(Condition.visible).click();
        return this;
    }

}
