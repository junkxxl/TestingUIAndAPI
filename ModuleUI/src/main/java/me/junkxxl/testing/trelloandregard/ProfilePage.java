package me.junkxxl.testing.trelloandregard;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.text;


public class ProfilePage {
    @FindBy(how = How.XPATH, using = "//p[@class='SideUser_userEmail__3g1pi']")
    SelenideElement elementProfile;

    @Step("Проверяем личный кабинет по почте \"{0}\"")
    public ProfilePage checkingProfile(String mail) {
        elementProfile.shouldHave(text(mail));
        return this;
    }
}
