package com.example.eklecticproject.service;

import com.example.eklecticproject.Iservice.ISmartPayService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class SmartPayService implements ISmartPayService {
    private final RestTemplate restTemplate;

    @Value("${spring.security.oauth2.client.registration.my-client.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.my-client.client-secret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.client.registration.my-client.redirect-uri}")
    private String redirectUri;

    private final ClientRegistrationRepository clientRegistrationRepository;
    @Override
    public String getAccessToken(String clientId, String clientSecret, String authorizationCode) {
        String oauthTokenUrl = "https://payment.eklectic.tn/API/oauth/token";

        // Construire les paramètres de la requête
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(oauthTokenUrl)
                .queryParam("grant_type", "authorization_code")
                .queryParam("client_id", clientId)
                .queryParam("client_secret", clientSecret)
                .queryParam("code", authorizationCode);

        // Configurer les en-têtes HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Configurer la requête HTTP
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Faire la requête et récupérer la réponse
        ResponseEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.POST,
                entity,
                String.class);

        // Vérifier si la requête a réussi
        if (response.getStatusCode() == HttpStatus.OK) {
            // Extraire l'access token de la réponse
            String responseBody = response.getBody();

             JSONObject jsonObject = new JSONObject(responseBody);
             String accessToken = jsonObject.getString("access_token");

            return responseBody; // Retourne la réponse brute pour l'exemple
        } else {
            // Gérer les erreurs ici, par exemple, loguer l'erreur
            throw new RuntimeException("Failed to retrieve access token: " + response.getStatusCode());
        }
    }







}




