package guru.qa.tests;

import com.codeborne.selenide.Condition;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static guru.qa.tests.TestData.login;
import static guru.qa.tests.TestData.password;
import static io.restassured.RestAssured.given;


public class LoginTests extends TestBase {

    @Test
    void successfulLoginTest() {

        String authData = new JSONObject()
                .put("userName", login)
                .put("password", password)
                .toString();

        Response authResponse = given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(authData)
                .when()
                .basePath("/Account/v1")
                .post("/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().response();

        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", authResponse.path("userId")));
        getWebDriver().manage().addCookie(new Cookie("token", authResponse.path("token")));
        getWebDriver().manage().addCookie(new Cookie("expires", authResponse.path("expires")));

        open("/profile");
        $("#userName-value").shouldHave(Condition.text(login));
    }
}
