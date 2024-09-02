package com.example.eklecticproject.service;


import com.example.eklecticproject.Security.JwtService;
import com.example.eklecticproject.auth.AuthenticationRequest;
import com.example.eklecticproject.auth.AuthenticationResponse;
import com.example.eklecticproject.entity.Role;
import com.example.eklecticproject.entity.Token;
import com.example.eklecticproject.entity.TokenType;
import com.example.eklecticproject.entity.Utilisateur;
import com.example.eklecticproject.repository.TokenRepository;
import com.example.eklecticproject.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class AuthenticationService {
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity register(Utilisateur request) {
        if(repository.GetUserByUsername(request.getUsername())!=null){
             return ResponseEntity.status(HttpStatus.CONFLICT).body("Le nom d'utilisateur existe déjà.");
        }else if(repository.GetUserByEmail(request.getEmail())!=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("L'adresse e-mail est déjà utilisée.");
        }else {

            request.setPassword(this.passwordEncoder.encode(request.getPassword()));
            request.setRole(Role.USER);
            repository.save(request);
            var jwtToken = jwtService.generateToken(request);
            var refreshToken = jwtService.generateRefreshToken(request);
            saveUserToken(request, jwtToken);
            return ResponseEntity.ok(AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                    .build());
        }
    }
    public ResponseEntity register1(Utilisateur request) {
        if(repository.GetUserByUsername(request.getUsername())!=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Le nom d'utilisateur existe déjà.");
        }else if(repository.GetUserByEmail(request.getEmail())!=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("L'adresse e-mail est déjà utilisée.");
        }else {

            request.setPassword(this.passwordEncoder.encode(request.getPassword()));
            repository.save(request);
            var jwtToken = jwtService.generateToken(request);
            var refreshToken = jwtService.generateRefreshToken(request);
            saveUserToken(request, jwtToken);
            return ResponseEntity.ok(AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                    .build());
        }
    }
    public ResponseEntity registerAdmin(Utilisateur request) {
        if(repository.GetUserByUsername(request.getUsername())!=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Le nom d'utilisateur existe déjà.");
        }else if(repository.GetUserByEmail(request.getEmail())!=null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("L'adresse e-mail est déjà utilisée.");
        }else {

            request.setPassword(this.passwordEncoder.encode(request.getPassword()));
            repository.save(request);
            var jwtToken = jwtService.generateToken(request);
            var refreshToken = jwtService.generateRefreshToken(request);
            saveUserToken(request, jwtToken);
            return ResponseEntity.ok(AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                    .build());
        }
    }


    public ResponseEntity<?> telRegistration( String phonenumber) {
        Optional<Utilisateur> existingUser = repository.findByPhonenumber(phonenumber);

        if (existingUser.isPresent()) {
            Utilisateur user = existingUser.get();
            System.out.println("Utilisateur trouvé : " + user);

            return ResponseEntity.ok("Numéro existe déjà. Utilisateur : " + user);
        } else {
            Utilisateur newUser = new Utilisateur();
            newUser.setPhonenumber(phonenumber);
            newUser.setPassword(this.passwordEncoder.encode(UUID.randomUUID().toString()));
            newUser.setEmail(UUID.randomUUID().toString());
            String prefix = "user";
            String uniqueString = prefix + UUID.randomUUID().toString();
            newUser.setUsername(uniqueString);
            newUser.setRole(Role.USER);

            repository.save(newUser);

            var jwtToken = jwtService.generateToken(newUser);
            var refreshToken = jwtService.generateRefreshToken(newUser);
            saveUserToken(newUser, jwtToken);

            return ResponseEntity.ok(AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                    .build());
        }
    }

    public ResponseEntity authenticate(AuthenticationRequest request) {
        if(repository.GetUserByEmail(request.getEmail())==null){
                return ResponseEntity.ok("Email est incorrect");
            }else
            if(!passwordEncoder.matches(request.getPassword(),repository.GetUserByEmail(request.getEmail()).getPassword())){
                return ResponseEntity.ok("Mot de passe est incorrect");
            }


        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return ResponseEntity.ok(AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build());
    }

    private void saveUserToken(Utilisateur user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(Utilisateur user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
    public Utilisateur GetCurrentUserByUsername(String Username){
        return repository.GetUserByUsername(Username);
    }
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.repository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}
