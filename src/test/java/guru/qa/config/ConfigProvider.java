package guru.qa.config;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Configuration.baseUrl;

public class ConfigProvider {

    public void setup() {

        Config config = ConfigFactory.create(Config.class, System.getProperties());

        String selenoidHome = config.getRemoteUrl();
        String selenoidCreds = config.getRemoteAuth();

        if (!(selenoidHome.isEmpty() || selenoidCreds.isEmpty())) {
            Configuration.remote = "https://" + selenoidCreds + "@" + selenoidHome + "/wd/hub";
        }


        baseUrl = config.getBaseUrl();
        RestAssured.baseURI = config.getBaseUrl();
        Configuration.browserSize = config.getBrowserSize();
        Configuration.browser = config.getBrowserName();
        Configuration.browserVersion = config.getBrowserVersion();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.of(
                "enableVNC", true,
                "enableVideo", true
        ));

        Configuration.pageLoadStrategy = "eager";
        Configuration.browserCapabilities = capabilities;
    }
}
