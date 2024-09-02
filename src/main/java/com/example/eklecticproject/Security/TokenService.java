package com.example.eklecticproject.Security;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class TokenService {
    private RestTemplate restTemplate;
    private String tokenUrl;

    public TokenService(RestTemplate restTemplate, String tokenUrl) {
        this.restTemplate = restTemplate;
        this.tokenUrl = tokenUrl;
    }

    public String getAccessToken() {
        // Effectuer la requête pour obtenir la réponse contenant le token
        ResponseEntity<TokenResponse> responseEntity = restTemplate.postForEntity(tokenUrl, null, TokenResponse.class);
        TokenResponse tokenResponse = responseEntity.getBody();

        // Vérifier que la réponse n'est pas nulle et extraire le token
        if (tokenResponse != null) {
            return tokenResponse.getAccessToken();
        } else {
            throw new IllegalStateException("TokenResponse is null");
        }
    }
}
