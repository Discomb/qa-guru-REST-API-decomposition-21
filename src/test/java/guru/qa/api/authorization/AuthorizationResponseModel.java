package guru.qa.api.authorization;

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
