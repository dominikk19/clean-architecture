package io.github.mat3e.auth;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("jwt")
@Getter(AccessLevel.PACKAGE)
@Setter(AccessLevel.PACKAGE)
class JwtConfigurationProperties {
    private String secret;
    private long validity;
}
