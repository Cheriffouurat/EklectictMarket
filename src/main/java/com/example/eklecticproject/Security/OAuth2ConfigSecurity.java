package com.example.eklecticproject.Security;

import com.example.eklecticproject.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
public class OAuth2ConfigSecurity {

//    @Value("${spring.security.oauth2.client.registration.my-client.client-id}")
//    private String clientId;
//
//    @Value("${spring.security.oauth2.client.registration.my-client.client-secret}")
//    private String clientSecret;
//
//    @Value("${spring.security.oauth2.client.registration.my-client.redirect-uri}")
//    private String redirectUri;
//    @Bean
//    public HttpSessionOAuth2AuthorizationRequestRepository authorizationRequestRepository() {
//        return new HttpSessionOAuth2AuthorizationRequestRepository();
//    }
//
////    @Bean
////    SecurityFilterChain oauthsecurityFilterChain(HttpSecurity http) throws Exception {
////        http
////                .authorizeHttpRequests(auth -> {
//////                    auth.requestMatchers("/favicon.ico", "/SmartPay/secured").authenticated();
//////                    auth.requestMatchers("/User").hasAnyRole(Role.ADMIN.name(), Role.USER.name(), Role.SUPER_ADMIN.name());
//////                    auth.
////                   anyRequest().permitAll(); // Allow all other requests without authentication
////                })
////                .oauth2Login(withDefaults()) // Configure OAuth2Login for authenticated requests
////                .csrf(csrf -> csrf.disable()); // Disable CSRF protection (optional based on needs)
////
////        return http.build();
////    }
////    @Bean
////    public ClientRegistrationRepository clientRegistrationRepository() {
////        ClientRegistration clientRegistration = ClientRegistration.withRegistrationId("my-client")
////                .clientId(clientId)
////                .clientSecret(clientSecret)
////                .redirectUri(redirectUri)
////                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
////                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
////                .authorizationUri("https://payment.eklectic.tn/API/oauth/user/authorize")
////                .tokenUri("https://payment.eklectic.tn/API/oauth/token")
////                .scope("SENDBOX")
////                .build();
////
////        return new InMemoryClientRegistrationRepository(clientRegistration);
////    }
}