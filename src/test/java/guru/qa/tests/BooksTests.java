package guru.qa.tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class BooksTests extends TestBase{

    @BeforeAll
    public static void beforeAll() {
        RestAssured.basePath = "/BookStore/v1";
    }

    public static void deleteAllBooks(String userId) {

        given()
                .log().all()
                .when()
                .delete("/Books?UserId"+userId)
                .then()
                .log().all()
                .statusCode(204);
    }

    @Test
    public void addBooksTest(){}

    @Test
    public void deleteAllBooksTest(){}

    @Test
    public void deleteOneBookTest(){}

}
