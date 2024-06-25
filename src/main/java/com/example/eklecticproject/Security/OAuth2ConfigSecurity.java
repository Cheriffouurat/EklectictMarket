package com.example.eklecticproject.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.beans.factory.annotation.Value;






@Configuration
@EnableWebSecurity
public class OAuth2ConfigSecurity {

    @Value("${spring.security.oauth2.client.registration.eklecticapi.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.eklecticapi.client-secret}")
    private String clientSecret;


    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        ClientRegistration clientRegistration = eklecticApiProvider();
        return new InMemoryClientRegistrationRepository(clientRegistration);
    }

    private ClientRegistration eklecticApiProvider() {
        return ClientRegistration.withRegistrationId("eklecticapi-provider")
                .clientId(clientId)
                .clientSecret(clientSecret)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .redirectUri("http://localhost:8086/callback")
                .authorizationUri("https://payment.eklectic.tn/API/oauth/user/authorize")
                .tokenUri("https://payment.eklectic.tn/API/oauth/token")
                .scope("SENDBOX")
                .userInfoUri("https://payment.eklectic.tn/API/oauth/user/info")
                .userNameAttributeName(IdTokenClaimNames.SUB)
                .clientName("EklecticAPI")
                .build();
    }


}


