package guru.qa.pages;

import com.codeborne.selenide.Condition;
import guru.qa.api.authorization.models.AuthorizationResponseModel;
import guru.qa.api.collections.models.BookModel;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class ProfilePage {

    public static void checkSuccessfulLogin(AuthorizationResponseModel authResponse) {

        step("Checking if the login was successful", () -> {
            open("/profile");
            $("#userName-value").shouldHave(Condition.text(authResponse.getUsername()));
        });
    }

    public static void checkBookIsPresent(BookModel book) {

        step("Checking book in the collection", () -> {
            open("/profile");
            $(".ReactTable").shouldHave(Condition.text(book.getTitle()));
        });
    }

    public static void checkBookIsNotPresent(BookModel book) {

        step("Checking that given book isn't in the collection", () -> {
            open("/profile");
            $(".ReactTable").shouldNotHave(Condition.text(book.getTitle()));
        });
    }
}
