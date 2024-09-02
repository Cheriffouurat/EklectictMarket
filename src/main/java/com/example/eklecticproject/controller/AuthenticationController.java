package com.example.eklecticproject.controller;


import com.example.eklecticproject.Iservice.IserviceUser;
import com.example.eklecticproject.Security.LogoutService;
import com.example.eklecticproject.auth.AuthenticationRequest;
import com.example.eklecticproject.configuration.MessageResponse;
import com.example.eklecticproject.entity.ResetPassword;
import com.example.eklecticproject.entity.Utilisateur;
import com.example.eklecticproject.repository.UserRepository;
import com.example.eklecticproject.service.AuthenticationService;
import com.example.eklecticproject.service.ServiceUser;
import com.nimbusds.oauth2.sdk.TokenResponse;
import com.nimbusds.openid.connect.sdk.claims.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    private final IserviceUser iserviceUser;
    private final UserRepository userRepository;
    private HttpServletRequest request;

    private HttpServletResponse response;


    private final LogoutService logoutService;

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String authHeader,
                                         HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        logoutService.logout(request, response, authentication);
        return ResponseEntity.ok("Logout successful");
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody Utilisateur request) {
        String password = request.getPassword();
        System.out.println("mot"+password);

        if (password == null || password.isEmpty()) {

            return ResponseEntity.badRequest().body("Le mot de passe est requis."+password);
        }


        return ResponseEntity.ok(service.register(request)).getBody();
    }
    @PostMapping("/register1")
    public ResponseEntity register1(@RequestBody Utilisateur request) {
        String password = request.getPassword();
        System.out.println("mot"+password);

        if (password == null || password.isEmpty()) {

            return ResponseEntity.badRequest().body("Le mot de passe est requis."+password);
        }


        return ResponseEntity.ok(service.register1(request)).getBody();
    }


    @GetMapping("/Oauth2Registration/{phonenumber}")
    public ResponseEntity Oauth2Registration(@PathVariable("phonenumber") String phonenumber) {

        return service.telRegistration(phonenumber);
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