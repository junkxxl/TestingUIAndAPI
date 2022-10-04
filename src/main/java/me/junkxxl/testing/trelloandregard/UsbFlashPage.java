package me.junkxxl.testing.trelloandregard;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.$;

public class UsbFlashPage {
    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'Card_id')]/../div/a/h6")
    ElementsCollection elementsCollection;
    @FindBy(how = How.XPATH, using = "//*/button[contains(text(),'В корзину')]")
    ElementsCollection elementAddBucket;
    @FindBy(how = How.XPATH, using = "//*/a[contains(@class,'Header_logo')]")
    SelenideElement elementHomePage;

    @Step("Выбираем производителя \"{0}\"")
    public UsbFlashPage selectionManufacturer(String nameManufacturer) {
        $(By.xpath("//*/label[text()='" + nameManufacturer + "']")).shouldBe(Condition.visible).click();
        return this;
    }
    @Step("Добавляем товар под номером \"{0}\" в корзину")
    public UsbFlashPage addBucket(int numberProduct) {
        elementAddBucket.get(numberProduct).click();
        return this;
    }

    @Step("Сохраняем название товара под номером \"{0}\"")
    @Attachment
    public String saveNameProduct(int numberProduct) {
        return elementsCollection.get(numberProduct).shouldBe(Condition.visible).getAttribute("title");
    }
    @Step("Кликаем на ссылку \"Домой\"")
    public UsbFlashPage clickToHomePage() {
        elementHomePage.click();
        return this;
    }
}
