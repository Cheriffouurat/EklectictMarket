package com.example.eklecticproject.controller;

import com.example.eklecticproject.service.SmartPayService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/SmartPay")
@CrossOrigin(origins = "*")
public class SmartPayController {


    private final SmartPayService smartPayService;
    @GetMapping("/callback")
    public String callback() {
        return "redirect:http://localhost:4200/#/callback";
    }

    @GetMapping("/token")
    public ResponseEntity<String> getToken(@RequestParam("code") String authorizationCode) {
        try {
            String accessToken = smartPayService.getAccessToken(authorizationCode);
            return ResponseEntity.ok("Access Token: " + accessToken);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error obtaining access token: " + e.getMessage());
        }
    }

    @GetMapping("/secure-api")
    public ResponseEntity<String> callSecureApi(@RequestParam("url") String url, @RequestParam("code") String authorizationCode) {
        try {
            ResponseEntity<String> response = smartPayService.callSecureApi(url, authorizationCode);
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error calling secure API: " + e.getMessage());
        }
    }
}
