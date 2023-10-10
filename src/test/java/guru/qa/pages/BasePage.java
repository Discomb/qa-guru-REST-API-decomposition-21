package guru.qa.pages;

import com.codeborne.selenide.Condition;
import guru.qa.api.authorization.AuthorizationResponseModel;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BasePage {

    public static void setAuthCookies(AuthorizationResponseModel authResponse) {
        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", authResponse.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("token", authResponse.getToken()));
        getWebDriver().manage().addCookie(new Cookie("expires", authResponse.getExpires()));
    }

    public static void checkSuccessfulLogin(AuthorizationResponseModel authResponse) {
        open("/profile");
        $("#userName-value").shouldHave(Condition.text(authResponse.getUsername()));
    }
}
