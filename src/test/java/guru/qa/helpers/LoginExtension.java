package guru.qa.helpers;

import guru.qa.api.authorization.models.AuthorizationResponseModel;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static guru.qa.api.authorization.AuthorizationApi.getAuthResponse;
import static guru.qa.pages.BasePage.setAuthCookies;
import static guru.qa.tests.TestData.getCredentials;

public class LoginExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {

        AuthorizationResponseModel authResponse = getAuthResponse(getCredentials());

        setAuthCookies(authResponse);
    }
}
