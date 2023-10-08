package guru.qa.api.authorization;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import static guru.qa.tests.TestData.login;
import static guru.qa.tests.TestData.password;
import static io.restassured.RestAssured.given;

public class AuthorizationApi {

    public static Response authorizationResponse(String basePath) {

        RestAssured.basePath = basePath;

        String authdata = new JSONObject()
                .put("userName", login)
                .put("password", password)
                .toString();

        Response response = given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(authdata)
                .when()
                .post("/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().response();

        return response;
    }
}
