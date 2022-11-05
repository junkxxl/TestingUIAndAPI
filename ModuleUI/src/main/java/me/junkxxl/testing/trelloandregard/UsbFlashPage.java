package me.junkxxl.testing.trelloandregard;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class UsbFlashPage {
    String elementsCollection =
            "//div[contains(@class, 'Card_id')]/../div/a/h6";
    String elementAddBucket =
            "//*/button[contains(text(),'В корзину')]";
    String elementHomePage =
            "//*/a[contains(@class,'Header_logo')]";

    @Step("Выбираем производителя \"{0}\"")
    public UsbFlashPage selectionManufacturer(String nameManufacturer) {
        $(By.xpath("//*/label[text()='" + nameManufacturer + "']")).shouldBe(Condition.visible).click();
        return this;
    }

    @Step("Добавляем товар под номером \"{0}\" в корзину")
    public UsbFlashPage addBucket(int numberProduct) {
        $$(By.xpath(elementAddBucket)).get(numberProduct).click();
        return this;
    }

    @Step("Сохраняем название товара под номером \"{0}\"")
    @Attachment
    public String saveNameProduct(int numberProduct) {
        return $$(By.xpath(elementsCollection)).get(numberProduct).shouldBe(Condition.visible).getAttribute("title");
    }

    @Step("Кликаем на ссылку \"Домой\"")
    public UsbFlashPage clickToHomePage() {
        $(By.xpath(elementHomePage)).click();
        return this;
    }
}
