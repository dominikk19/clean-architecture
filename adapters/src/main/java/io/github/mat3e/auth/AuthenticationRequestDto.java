package io.github.mat3e.auth;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter(value = AccessLevel.PACKAGE)
@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
class AuthenticationRequestDto {
    private String username;
    private String password;

}
