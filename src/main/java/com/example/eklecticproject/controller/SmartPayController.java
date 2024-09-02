package com.example.eklecticproject.controller;

import com.example.eklecticproject.Iservice.ISmartPayService;
import com.example.eklecticproject.entity.Abonnement;
import com.example.eklecticproject.entity.UserInfo;
import com.example.eklecticproject.service.SmartPayService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@AllArgsConstructor
@RequestMapping("/SmartPay")
@CrossOrigin(origins = "*")
public class SmartPayController {
    private final ISmartPayService smartPayService;

    @GetMapping("/secured")
    public String secured() {
        return "Hello, Secured!";
    }
    @GetMapping("/")
    public String home() {
        return "Hello, Home!";
    }

    @PostMapping("/Aouth2.0/token")
    public ResponseEntity<String> getAccessToken(@RequestParam String authorizationCode) {
        try {
            String accessToken = smartPayService.getAccessToken(authorizationCode);
            return ResponseEntity.ok(accessToken);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to obtain access token: " + e.getMessage());
        }
    }
    @PostMapping("/user-info")
    public ResponseEntity<Abonnement> getUserInfo(@RequestParam String apiToken) {
        String token = apiToken.replace("Bearer ", "");
        Abonnement abonnement = smartPayService.getUserInfo(token);
        return ResponseEntity.ok(abonnement);
    }

}