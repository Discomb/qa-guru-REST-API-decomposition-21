package guru.qa.tests;

import org.json.JSONObject;

public class TestData {
    public static String login = "testtestov31",
            password = "Testtestov31_%";

    public static String getCredentials() {
        return new JSONObject()
                .put("userName", login)
                .put("password", password)
                .toString();
    }
}
