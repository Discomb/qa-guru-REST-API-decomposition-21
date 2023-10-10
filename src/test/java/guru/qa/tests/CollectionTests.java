package guru.qa.tests;

import guru.qa.api.authorization.AuthorizationResponseModel;
import guru.qa.api.collections.BookModel;
import org.junit.jupiter.api.Test;

import static guru.qa.api.authorization.AuthorizationApi.getAuthResponse;
import static guru.qa.api.collections.CollectionsApi.*;
import static guru.qa.pages.BasePage.setAuthCookies;
import static guru.qa.pages.ProfilePage.checkBook;
import static guru.qa.pages.ProfilePage.checkBookIsNotPresent;
import static guru.qa.tests.TestData.getCredentials;

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
        BookModel book = getRandomBook(authResponse);

        addBookUnauthorized(authResponse, book);
    }

    @Test
    void negative400addBookToTheCollectionTest() {

        AuthorizationResponseModel authResponse = getAuthResponse(getCredentials());
        BookModel book = getRandomBook(authResponse);

        deleteAllBooks(authResponse);
        addBookToTheCollection(authResponse, book);
        addExistingBook(authResponse, book);
    }

    @Test
    void deleteSingleBookFromTheCollectionTest() {

        AuthorizationResponseModel authResponse = getAuthResponse(getCredentials());
        BookModel book = getRandomBook(authResponse);

        deleteAllBooks(authResponse);
        addBookToTheCollection(authResponse, book);
        deleteBookFromTheCollection(authResponse, book);
        setAuthCookies(authResponse);
        checkBookIsNotPresent(book);
    }
}
