package guru.qa.tests;

import com.codeborne.selenide.Condition;
import guru.qa.api.authorization.AuthorizationResponseModel;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static guru.qa.api.authorization.AuthorizationApi.getAuthResponse;
import static guru.qa.tests.TestData.*;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.is;

public class CollectionTests extends TestBase {

    @Test
    void addBookToCollectionTest() {

        AuthorizationResponseModel authResponse = getAuthResponse(getCredentials());

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authResponse.getToken())
                .queryParams("UserId", authResponse.getUserId())
                .when()
                .basePath("BookStore/v1")
                .delete("/Books")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);

        String isbn = "9781491904244";

        String bookData = format("{\"userId\":\"%s\",\"collectionOfIsbns\":[{\"isbn\":\"%s\"}]}",
                authResponse.getUserId(), isbn);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authResponse.getToken())
                .body(bookData)
                .when()
                .basePath("BookStore/v1")
                .post("/Books")
                .then()
                .log().status()
                .log().body()
                .statusCode(201);

        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", authResponse.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("token", authResponse.getToken()));
        getWebDriver().manage().addCookie(new Cookie("expires", authResponse.getExpires()));

        open("/profile");
        $(".ReactTable").shouldHave(Condition.text("You Don't Know JS"));
    }

    @Test
    void negative401AddBookToCollectionTest() {

        String user = "6b11b363-9eed-4a00-9469-51d95bed4ecf";

        String isbn = "9781491904244";

        String bookData = format("{\"userId\":\"%s\",\"collectionOfIsbns\":[{\"isbn\":\"%s\"}]}",
                user, isbn);

            given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(bookData)
                .when()
                .basePath("/BookStore/v1")
                .post("/Books")
                .then()
                .log().status()
                .log().body()
                .statusCode(401)
                .body("code", is("1200"))
                .body("message", is("User not authorized!"));
    }

    @Test
    void negative400addBookToCollectionTest() {

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

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authResponse.path("token"))
                .queryParams("UserId", authResponse.path("userId"))
                .when()
                .basePath("BookStore/v1")
                .delete("/Books")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);

        String isbn = "9781491904244";

        String bookData = format("{\"userId\":\"%s\",\"collectionOfIsbns\":[{\"isbn\":\"%s\"}]}",
                authResponse.path("userId"), isbn);

        Response addBookResponse = given()
                .log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authResponse.path("token"))
                .body(bookData)
                .when()
                .basePath("BookStore/v1")
                .post("/Books")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .extract().response();

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authResponse.path("token"))
                .body(bookData)
                .when()
                .basePath("BookStore/v1")
                .post("/Books")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("code", is("1210"))
                .body("message", is("ISBN already present in the User's Collection!"));
    }

    @Test
    void deleteSingleBookFromCollectionTest() {

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

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authResponse.path("token"))
                .queryParams("UserId", authResponse.path("userId"))
                .when()
                .basePath("BookStore/v1")
                .delete("/Books")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);

        String isbn = "9781491904244";

        String bookData = format("{\"userId\":\"%s\",\"collectionOfIsbns\":[{\"isbn\":\"%s\"}]}",
                authResponse.path("userId"), isbn);
        String deleteBookData = format("{\"userId\":\"%s\",\"isbn\":\"%s\"}",
                authResponse.path("userId"), isbn);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authResponse.path("token"))
                .body(bookData)
                .when()
                .basePath("BookStore/v1")
                .post("/Books")
                .then()
                .log().status()
                .log().body()
                .statusCode(201);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authResponse.path("token"))
                .body(deleteBookData)
                .when()
                .basePath("BookStore/v1")
                .delete("/Book")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);

//        open("/favicon.ico");
//        getWebDriver().manage().addCookie(new Cookie("userID", authResponse.path("userId")));
//        getWebDriver().manage().addCookie(new Cookie("token", authResponse.path("token")));
//        getWebDriver().manage().addCookie(new Cookie("expires", authResponse.path("expires")));
//
//        open("/profile");
//        $(".ReactTable").shouldHave(Condition.text("You Don't Know JS"));
    }
}
