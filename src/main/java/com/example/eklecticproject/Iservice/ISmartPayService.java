package com.example.eklecticproject.Iservice;

import com.example.eklecticproject.entity.Abonnement;
import com.example.eklecticproject.entity.UserInfo;
import org.springframework.http.ResponseEntity;

public interface ISmartPayService {






    String getAccessToken(String authorizationCode);


    Abonnement getUserInfo(String apiToken);
}
