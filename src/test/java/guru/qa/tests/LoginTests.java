package guru.qa.tests;

import com.codeborne.selenide.Condition;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static guru.qa.api.authorization.AuthorizationApi.authorizationResponse;
import static guru.qa.tests.TestData.login;

public class LoginTests extends TestBase {

    @BeforeAll
    public static void beforeAll() {
        RestAssured.basePath = "/Account/v1";
    }

    @Test
    void successfulLoginTest() {

        Response response = authorizationResponse(RestAssured.basePath);

        open("/favicon.ico");

        getWebDriver().manage().addCookie(new Cookie("userID", response.path("userId")));
        getWebDriver().manage().addCookie(new Cookie("expires", response.path("expires")));
        getWebDriver().manage().addCookie(new Cookie("token", response.path("token")));

        open("/profile");
        $("#userName-value").shouldHave(Condition.text(login));

    }
}
