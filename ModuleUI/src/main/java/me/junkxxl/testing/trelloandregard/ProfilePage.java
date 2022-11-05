package me.junkxxl.testing.trelloandregard;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;


public class ProfilePage {
    String elementProfile =
            "//p[@class='SideUser_userEmail__3g1pi']";

    @Step("Проверяем личный кабинет по почте \"{0}\"")
    public ProfilePage checkingProfile(String mail) {
        $(By.xpath(elementProfile)).shouldHave(text(mail));
        return this;
    }
}
