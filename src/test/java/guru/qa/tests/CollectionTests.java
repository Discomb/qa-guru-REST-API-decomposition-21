package guru.qa.tests;

import guru.qa.api.authorization.AuthorizationResponseModel;
import guru.qa.api.collections.BookModel;
import org.junit.jupiter.api.Test;

import static guru.qa.api.authorization.AuthorizationApi.getAuthResponse;
import static guru.qa.api.collections.CollectionsApi.*;
import static guru.qa.pages.BasePage.setAuthCookies;
import static guru.qa.pages.ProfilePage.checkBook;
import static guru.qa.tests.TestData.getCredentials;
import static java.lang.String.format;

public class CollectionTests extends TestBase {

    @Test
    void addBookToTheCollectionTest() {

        AuthorizationResponseModel authResponse = getAuthResponse(getCredentials());
        BookModel book = getRandomBook(authResponse);

        deleteAllBooks(authResponse);
        addBookToTheCollection(authResponse, book);
        setAuthCookies(authResponse);
        checkBook(book);
    }

    @Test
    void negative401AddBookToTheCollectionTest() {

        AuthorizationResponseModel authResponse = getAuthResponse(getCredentials());

        String isbn = "9781491904244";
        String bookData = format("{\"userId\":\"%s\",\"collectionOfIsbns\":[{\"isbn\":\"%s\"}]}",
                authResponse.getUserId(), isbn);

        addBookUnauthorized(bookData);
    }

//    @Test
//    void negative400addBookToTheCollectionTest() {
//
//        AuthorizationResponseModel authResponse = getAuthResponse(getCredentials());
//
//        String isbn = "9781491904244";
//        String bookData = format("{\"userId\":\"%s\",\"collectionOfIsbns\":[{\"isbn\":\"%s\"}]}",
//                authResponse.getUserId(), isbn);
//
//        deleteAllBooks(authResponse);
//        addBookToTheCollection(authResponse, bookData);
//        addExistingBook(authResponse, bookData);
//    }

//    @Test
//    void deleteSingleBookFromTheCollectionTest() {
//
//        AuthorizationResponseModel authResponse = getAuthResponse(getCredentials());
//
//        String isbn = "9781491904244";
//        String bookData = format("{\"userId\":\"%s\",\"collectionOfIsbns\":[{\"isbn\":\"%s\"}]}",
//                authResponse.getUserId(), isbn);
//        String deleteBookData = format("{\"userId\":\"%s\",\"isbn\":\"%s\"}",
//                authResponse.getUserId(), isbn);
//
//        deleteAllBooks(authResponse);
//        addBookToTheCollection(authResponse, bookData);
//        deleteBookFromTheCollection(authResponse, deleteBookData);
//    }
}
