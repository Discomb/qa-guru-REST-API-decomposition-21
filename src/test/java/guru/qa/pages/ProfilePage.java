package guru.qa.pages;

import com.codeborne.selenide.Condition;
import guru.qa.api.collections.BookModel;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ProfilePage {

    public static void checkBook(BookModel book) {
        open("/profile");
        $(".ReactTable").shouldHave(Condition.text(book.getTitle()));
    }

    public static void checkBookIsNotPresent(BookModel book) {
        open("/profile");
        $(".ReactTable").shouldNotHave(Condition.text(book.getTitle()));
    }
}
