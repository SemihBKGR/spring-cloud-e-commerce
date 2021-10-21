package com.semihbkgr.ecommerce.ecommerce.userserver.config;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakClientConfig {


    private String clientId="default";
    @Value("${keycloak.auth-server-url}")
    private String authUrl;
    @Value("${keycloak.realm}")
    private String realm;

    private String username = "user";

    private String password = "password";

    @Bean
    public Keycloak keycloak() {
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl("http://localhost:8080/auth")
                .grantType(OAuth2Constants.PASSWORD)
                .realm("ecom")
                .clientId("default")
                .username("user")
                .password("password")
                .resteasyClient(
                        new ResteasyClientBuilder()
                                .connectionPoolSize(10).build()
                ).build();
        keycloak.tokenManager().getAccessToken();
        return keycloak;
    }

}
