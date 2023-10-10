package guru.qa.api.authorization.models;

import lombok.Getter;

@Getter
public class AuthorizationResponseModel {
    String userId,
            username,
            password,
            token,
            expires,
            created_date;

    Boolean isActive;
}
