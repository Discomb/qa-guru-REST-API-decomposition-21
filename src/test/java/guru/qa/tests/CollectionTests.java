package guru.qa.tests;

import guru.qa.api.authorization.models.AuthorizationResponseModel;
import guru.qa.api.collections.models.BookModel;
import guru.qa.helpers.WithLogin;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static guru.qa.api.authorization.AuthorizationApi.getAuthResponse;
import static guru.qa.api.collections.CollectionsApi.*;
import static guru.qa.pages.ProfilePage.checkBookIsPresent;
import static guru.qa.pages.ProfilePage.checkBookIsNotPresent;
import static guru.qa.tests.TestData.getCredentials;

@Epic(value = "Bookstore tests")
@Feature(value = "Collections tests")
public class CollectionTests extends TestBase {

    @Test
    @WithLogin
    @Story(value = "Adding books")
    @DisplayName("Checking add book to the collection")
    @Description("Adding book to the collection and checking that it is displayed")
    void addBookToTheCollectionTest() {

        AuthorizationResponseModel authResponse = getAuthResponse(getCredentials());
        BookModel book = getRandomBook();

        deleteAllBooks(authResponse);
        addBookToTheCollection(authResponse, book);
        checkBookIsPresent(book);
    }

    @Test
    @Story(value = "Adding books")
    @DisplayName("Trying to add book to the collection without authorization")
    @Description("Adding book to the collection without authorization")
    void negative401AddBookToTheCollectionTest() {

        AuthorizationResponseModel authResponse = getAuthResponse(getCredentials());
        BookModel book = getRandomBook();

        addBookUnauthorized(authResponse, book);
    }

    @Test
    @Story(value = "Adding books")
    @DisplayName("Trying to add book that already exists in the collection")
    @Description("Adding already existing book to the collection")
    void negative400addBookToTheCollectionTest() {

        AuthorizationResponseModel authResponse = getAuthResponse(getCredentials());
        BookModel book = getRandomBook();

        deleteAllBooks(authResponse);
        addBookToTheCollection(authResponse, book);
        addExistingBook(authResponse, book);
    }

    @Test
    @WithLogin
    @Story(value = "Deleting books")
    @DisplayName("Trying to delete single book from the collection")
    @Description("Adding a book to the collection and then deleting it")
    void deleteSingleBookFromTheCollectionTest() {

        AuthorizationResponseModel authResponse = getAuthResponse(getCredentials());
        BookModel book = getRandomBook();

        deleteAllBooks(authResponse);
        addBookToTheCollection(authResponse, book);
        deleteBookFromTheCollection(authResponse, book);
        checkBookIsNotPresent(book);
    }

    @Test
    @WithLogin
    @Story(value = "Deleting books")
    @DisplayName("Trying to delete all books from the collection")
    @Description("Deleting all books from the collection")
    void deleteAllBooksFromTheCollectionTest() {

        AuthorizationResponseModel authResponse = getAuthResponse(getCredentials());
        BookModel book = getRandomBook();

        deleteAllBooks(authResponse);
        checkBookIsNotPresent(book);
    }
}
