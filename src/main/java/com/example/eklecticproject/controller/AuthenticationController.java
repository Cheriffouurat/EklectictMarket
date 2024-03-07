package com.example.eklecticproject.controller;


import com.example.eklecticproject.Iservice.IserviceUser;
import com.example.eklecticproject.auth.AuthenticationRequest;
import com.example.eklecticproject.configuration.MessageResponse;
import com.example.eklecticproject.entity.ResetPassword;
import com.example.eklecticproject.entity.Utilisateur;
import com.example.eklecticproject.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    private final IserviceUser iserviceUser;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody Utilisateur request) {
        return ResponseEntity.ok(service.register(request)).getBody();
    }
    @PostMapping("/authenticate")
    public ResponseEntity authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request)).getBody();
    }
    @GetMapping("/GetCurrentUser/{username}")
    public Utilisateur GetCurrentUser(@PathVariable("username") String username){
        return service.GetCurrentUserByUsername(username);
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }
    @GetMapping("/SendCode/{Email}")
    public ResponseEntity<?> SendCode(@PathVariable("Email") String Email) {
        try {
            return ResponseEntity.ok(new MessageResponse(iserviceUser.SendCode(Email)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }}
    @PostMapping("/resetPassword")
    public ResponseEntity resetPassword(@RequestBody ResetPassword resetPassword) {
        return ResponseEntity.ok(new MessageResponse(iserviceUser.ResetPassword(resetPassword.getVerificationCode(),resetPassword.getNewPassword())));
    }


}