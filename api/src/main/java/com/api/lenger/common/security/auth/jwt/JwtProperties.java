package com.api.lenger.common.security.auth.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@ConfigurationProperties(prefix = "jwt")
public record JwtProperties(
    RSAPublicKey publicKey,
    RSAPrivateKey privateKey,
    int expirationDays
) {}