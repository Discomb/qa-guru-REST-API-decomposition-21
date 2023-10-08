package guru.qa.tests;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Configuration.baseUrl;

public class TestBase {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://demoqa.com";

        baseUrl = RestAssured.baseURI;
        Configuration.pageLoadStrategy = "eager";
    }
}
