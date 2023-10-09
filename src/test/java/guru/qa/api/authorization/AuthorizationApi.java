package guru.qa.api.authorization;

import io.restassured.http.ContentType;

import static guru.qa.helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.given;

public class AuthorizationApi {

    public static AuthorizationResponseModel getAuthResponse(String credentials) {
        return given()
                .filter(withCustomTemplates())
                .log().all()
                .contentType(ContentType.JSON)
                .body(credentials)
                .when()
                .basePath("/Account/v1")
                .post("/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(AuthorizationResponseModel.class);
    }

}
