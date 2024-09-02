package com.example.eklecticproject.service;

import com.example.eklecticproject.Iservice.ISmartPayService;
import com.example.eklecticproject.Security.TokenResponse;
import com.example.eklecticproject.entity.Abonnement;
import com.example.eklecticproject.repository.IAbonnementRepositorie;
import com.example.eklecticproject.repository.IServiceRepositorie;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Slf4j
@RequiredArgsConstructor
@Service
public class SmartPayService implements ISmartPayService {
    private final RestTemplate restTemplate;
   private final IAbonnementRepositorie AbonnementRepositorie;

    @Value("${client-id}")
    private String clientId;

    @Value("${client-secret}")
    private String clientSecret;


    @Override
    public String getAccessToken(String authorizationCode) {
        String tokenUrl = "https://payment.eklectic.tn/API/oauth/token";

        // Préparer le corps de la requête
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("code", authorizationCode);

        // Préparer les en-têtes HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED);

        // Créer l'entité HTTP avec le corps et les en-têtes
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        // Envoyer la requête POST
        ResponseEntity<TokenResponse> response = restTemplate.exchange(
                tokenUrl,
                HttpMethod.POST,
                request,
                TokenResponse.class
        );

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return response.getBody().getAccessToken();
        } else {
            throw new RuntimeException("Failed to obtain access token");
        }
    }
    @Override
    public Abonnement  getUserInfo(String apiToken) {
        String url = "https://payment.eklectic.tn/API/oauth/user/info";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                String.class
        );

        String responseBody = response.getBody();
        return mapToAbonnement(responseBody);
    }

    private Abonnement mapToAbonnement(String responseBody) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(responseBody);

            Abonnement abonnement = new Abonnement();
            abonnement.setIdAbonnement(root.path("id").asText());
            abonnement.setTel(root.path("msisdn").asText());
            abonnement.setDateDebFree(LocalDateTime.parse(root.path("date_debfree").asText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            abonnement.setDateFinFree(LocalDateTime.parse(root.path("date_finfree").asText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            abonnement.setDateAbonnement(LocalDateTime.parse(root.path("subscription_date").asText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            abonnement.setDateDesabonnement(root.path("unsubscription_date").isNull() ? null : LocalDateTime.parse(root.path("unsubscription_date").asText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            abonnement.setIdService(root.path("service_id").asText());
            abonnement.setType(root.path("type").asText());
            abonnement.setDateExpiration(LocalDateTime.parse(root.path("expire_date").asText(), DateTimeFormatter.ISO_OFFSET_DATE_TIME));

            return  AbonnementRepositorie.save(abonnement);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}











