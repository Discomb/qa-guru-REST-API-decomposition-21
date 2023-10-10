package guru.qa.api.collections;

import guru.qa.api.authorization.AuthorizationResponseModel;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class CollectionsApi {

    public static void deleteAllBooks(AuthorizationResponseModel authResponse) {
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
    }

    public static void addBookToTheCollection(AuthorizationResponseModel authResponse, String bookData) {
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
    }

    public static void addBookUnauthorized(String bookData) {
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

    public static void addExistingBook(AuthorizationResponseModel authResponse, String bookData) {
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
                .statusCode(400)
                .body("code", is("1210"))
                .body("message", is("ISBN already present in the User's Collection!"));
    }

    public static void deleteBookFromTheCollection(AuthorizationResponseModel authResponse, String deleteBookData) {
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authResponse.getToken())
                .body(deleteBookData)
                .when()
                .basePath("BookStore/v1")
                .delete("/Book")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }
}
