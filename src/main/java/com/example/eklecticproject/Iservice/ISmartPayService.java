package com.example.eklecticproject.Iservice;

import org.springframework.http.ResponseEntity;

public interface ISmartPayService {



    String getAccessToken(String clientId, String clientSecret, String authorizationCode);


}
