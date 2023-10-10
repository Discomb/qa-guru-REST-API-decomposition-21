package guru.qa.tests;

import guru.qa.api.authorization.models.AuthorizationResponseModel;
import guru.qa.api.collections.models.BookModel;
import guru.qa.helpers.WithLogin;
import org.junit.jupiter.api.Test;

import static guru.qa.api.authorization.AuthorizationApi.getAuthResponse;
import static guru.qa.api.collections.CollectionsApi.*;
import static guru.qa.pages.ProfilePage.checkBookIsPresent;
import static guru.qa.pages.ProfilePage.checkBookIsNotPresent;
import static guru.qa.tests.TestData.getCredentials;

public class CollectionTests extends TestBase {

    @Test
    @WithLogin
    void addBookToTheCollectionTest() {

        AuthorizationResponseModel authResponse = getAuthResponse(getCredentials());
        BookModel book = getRandomBook();

        deleteAllBooks(authResponse);
        addBookToTheCollection(authResponse, book);
        checkBookIsPresent(book);
    }

    @Test
    void negative401AddBookToTheCollectionTest() {

        AuthorizationResponseModel authResponse = getAuthResponse(getCredentials());
        BookModel book = getRandomBook();

        addBookUnauthorized(authResponse, book);
    }

    @Test
    void negative400addBookToTheCollectionTest() {

        AuthorizationResponseModel authResponse = getAuthResponse(getCredentials());
        BookModel book = getRandomBook();

        deleteAllBooks(authResponse);
        addBookToTheCollection(authResponse, book);
        addExistingBook(authResponse, book);
    }

    @Test
    @WithLogin
    void deleteSingleBookFromTheCollectionTest() {

        AuthorizationResponseModel authResponse = getAuthResponse(getCredentials());
        BookModel book = getRandomBook();

        deleteAllBooks(authResponse);
        addBookToTheCollection(authResponse, book);
        deleteBookFromTheCollection(authResponse, book);
        checkBookIsNotPresent(book);
    }
}
