package guru.qa.pages;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ProfilePage {

    public static void checkBook(String bookName) {
        open("/profile");
        $(".ReactTable").shouldHave(Condition.text(bookName));
    }
}
