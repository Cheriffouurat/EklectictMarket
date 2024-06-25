package com.example.eklecticproject.service;

import com.example.eklecticproject.configuration.RestTemplateConfig;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class SmartPayService {
    private final RestTemplate restTemplate;

    @Value("${spring.security.oauth2.client.registration.eklecticapi.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.eklecticapi.client-secret}")
    private String clientSecret;

//    @Value("${spring.security.oauth2.client.provider.eklecticapi.token-uri}")
private final ClientRegistrationRepository clientRegistrationRepository;

    public String getAccessToken(String authorizationCode) {
        ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId("eklecticapi-provider");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        String body = "grant_type=authorization_code" +
                "&client_id=" + clientRegistration.getClientId() +
                "&client_secret=" + clientRegistration.getClientSecret() +
                "&redirect_uri=" + clientRegistration.getRedirectUri() +
                "&code=" + authorizationCode; // Replace "authorizationCode" with the actual authorization code

        HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                clientRegistration.getProviderDetails().getTokenUri(),
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            JSONObject jsonObject = new JSONObject(response.getBody());
            return jsonObject.getString("access_token");
        } else {
            throw new RuntimeException("Failed to obtain access token");
        }
    }

    public ResponseEntity<String> callSecureApi(String url, String authorizationCode) {
        String accessToken = getAccessToken(authorizationCode);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        try {
            return restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    requestEntity,
                    String.class
            );
        } catch (RestClientException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error calling secure API: " + e.getMessage());
        }
    }
}


