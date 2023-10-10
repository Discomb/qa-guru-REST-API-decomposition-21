package guru.qa.tests;

import guru.qa.api.authorization.models.AuthorizationResponseModel;
import org.junit.jupiter.api.Test;

import static guru.qa.api.authorization.AuthorizationApi.getAuthResponse;
import static guru.qa.pages.BasePage.setAuthCookies;
import static guru.qa.pages.ProfilePage.checkSuccessfulLogin;
import static guru.qa.tests.TestData.getCredentials;


public class LoginTests extends TestBase {

    @Test
    void successfulLoginTest() {

        AuthorizationResponseModel authResponse = getAuthResponse(getCredentials());

        setAuthCookies(authResponse);
        checkSuccessfulLogin(authResponse);
    }
}
