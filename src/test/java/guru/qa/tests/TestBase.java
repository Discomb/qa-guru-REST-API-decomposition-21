package guru.qa.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import guru.qa.config.ConfigProvider;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class TestBase {

    @BeforeAll
    public static void setup() {
        new ConfigProvider().setup();
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }
}
