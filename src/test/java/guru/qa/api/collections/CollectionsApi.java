package guru.qa.api.collections;

import guru.qa.api.authorization.AuthorizationResponseModel;
import io.restassured.http.ContentType;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

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

    public static void addBookToTheCollection(AuthorizationResponseModel authResponse, BookModel book) {

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authResponse.getToken())
                .body(generateAddBookRequest(authResponse, book))
                .when()
                .basePath("BookStore/v1")
                .post("/Books")
                .then()
                .log().status()
                .log().body()
                .statusCode(201);
    }

    public static void addBookUnauthorized(AuthorizationResponseModel authResponse, BookModel book) {
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(generateAddBookRequest(authResponse, book))
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

    public static void addExistingBook(AuthorizationResponseModel authResponse, BookModel book) {
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authResponse.getToken())
                .body(generateAddBookRequest(authResponse, book))
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

    public static void deleteBookFromTheCollection(AuthorizationResponseModel authResponse, BookModel book) {
        DeleteBookModel deleteBookData = new DeleteBookModel();
        deleteBookData.setUserId(authResponse.getUserId());
        deleteBookData.setIsbn(book.getIsbn());

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

    public static ArrayList<BookModel> getBooks(AuthorizationResponseModel authResponse) {
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + authResponse.getToken())
                .when()
                .basePath("BookStore/v1")
                .get("/Books")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(BooksListModel.class).getBooks();
    }

    public static BookModel getRandomBook(AuthorizationResponseModel authResponse) {
        ArrayList<BookModel> books = getBooks(authResponse);

        return books.get(ThreadLocalRandom.current().nextInt(0, books.size()) + 1);
    }

    private static AddBookRequestModel generateAddBookRequest(AuthorizationResponseModel authResponse, BookModel book) {
        ISBNModel isbn = new ISBNModel();
        AddBookRequestModel body = new AddBookRequestModel();

        isbn.setIsbn(book.getIsbn());

        ArrayList<ISBNModel> listOfISBNs = new ArrayList<>();
        listOfISBNs.add(isbn);

        body.setUserId(authResponse.getUserId());
        body.setCollectionOfIsbns(listOfISBNs);

        return body;
    }
}
