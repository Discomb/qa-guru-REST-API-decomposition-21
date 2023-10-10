package guru.qa.api.base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static guru.qa.api.authorization.AuthorizationApi.getAuthResponse;
import static guru.qa.helpers.CustomAllureListener.withCustomTemplates;
import static guru.qa.tests.TestData.getCredentials;
import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;

public class BaseApi {
    protected final static RequestSpecification defaultRequestSpec = new RequestSpecBuilder()
            .addFilter(withCustomTemplates())
            .log(ALL)
            .setContentType(JSON)
            .addHeader("Authorization", "Bearer " + getAuthResponse(getCredentials()).getToken())
            .build();

    protected final static RequestSpecification unauthorizedRequestSpec = new RequestSpecBuilder()
            .addFilter(withCustomTemplates())
            .log(ALL)
            .setContentType(JSON)
            .build();

    protected final static ResponseSpecification defaultResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .build();
}
