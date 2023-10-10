package guru.qa.tests;

import guru.qa.api.authorization.models.AuthorizationResponseModel;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static guru.qa.api.authorization.AuthorizationApi.getAuthResponse;
import static guru.qa.pages.BasePage.setAuthCookies;
import static guru.qa.pages.ProfilePage.checkSuccessfulLogin;
import static guru.qa.tests.TestData.getCredentials;

@Epic(value = "Bookstore tests")
@Feature(value = "Login tests")
public class LoginTests extends TestBase {

    @Test
    @Story(value = "Authorization")
    @DisplayName("Checking successful authorization")
    @Description("Authorizing with valid credentials")
    void successfulLoginTest() {

        AuthorizationResponseModel authResponse = getAuthResponse(getCredentials());

        setAuthCookies(authResponse);
        checkSuccessfulLogin(authResponse);
    }
}
