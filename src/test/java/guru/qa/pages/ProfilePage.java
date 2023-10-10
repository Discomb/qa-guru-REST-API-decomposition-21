package guru.qa.pages;

import com.codeborne.selenide.Condition;
import guru.qa.api.authorization.models.AuthorizationResponseModel;
import guru.qa.api.collections.models.BookModel;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ProfilePage {

    public static void checkSuccessfulLogin(AuthorizationResponseModel authResponse) {
        open("/profile");
        $("#userName-value").shouldHave(Condition.text(authResponse.getUsername()));
    }

    public static void checkBookIsPresent(BookModel book) {
        open("/profile");
        $(".ReactTable").shouldHave(Condition.text(book.getTitle()));
    }

    public static void checkBookIsNotPresent(BookModel book) {
        open("/profile");
        $(".ReactTable").shouldNotHave(Condition.text(book.getTitle()));
    }
}
