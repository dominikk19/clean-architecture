package io.github.mat3e.auth;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Getter(AccessLevel.PACKAGE)
class AuthenticationResponseDto {
    private final String token;
}
