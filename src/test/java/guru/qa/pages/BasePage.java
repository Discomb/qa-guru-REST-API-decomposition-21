package guru.qa.pages;

import guru.qa.api.authorization.models.AuthorizationResponseModel;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;


public class BasePage {

    public static void setAuthCookies(AuthorizationResponseModel authResponse) {

        step("Setting auth cookies", () -> {
            open("/favicon.ico");
            getWebDriver().manage().addCookie(new Cookie("userID", authResponse.getUserId()));
            getWebDriver().manage().addCookie(new Cookie("token", authResponse.getToken()));
            getWebDriver().manage().addCookie(new Cookie("expires", authResponse.getExpires()));
        });
    }
}
