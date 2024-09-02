package com.example.eklecticproject.controller;


import com.example.eklecticproject.Iservice.IserviceUser;
import com.example.eklecticproject.auditing.ApplicationAuditAware;
import com.example.eklecticproject.entity.Token;
import com.example.eklecticproject.entity.Utilisateur;
import com.example.eklecticproject.repository.TokenRepository;
import com.example.eklecticproject.repository.UserRepository;
import com.example.eklecticproject.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/User")
@CrossOrigin(origins = "*")
public class UserController {
    IserviceUser iserviceUser;
    TokenRepository tokenRepository;
    private final AuthenticationService service;
    private final UserRepository repository;



    @GetMapping("/retrieve-all-user")
    public List<Utilisateur> GetAllUser() {
        return iserviceUser.GetALLUser();
    }

    @GetMapping("GetUsrById/{idU}")
    public Utilisateur GetUserById(@PathVariable("idU") Integer idU) {
        return iserviceUser.GetUserById(idU);
    }
    @GetMapping("GetUsrByUsername/{Username}")
    public Utilisateur GetUserByUsername(@PathVariable("Username") String Username) {
        return iserviceUser.GetUserByUsername(Username);
    }
    @PutMapping("/EditUser")
    public ResponseEntity EditUser(@RequestBody Utilisateur utilisateur) {

        ApplicationAuditAware a = new ApplicationAuditAware();
        if (a.getCurrentAuditor().get() == utilisateur.getId() || iserviceUser.GetUserById(a.getCurrentAuditor().get()).getRole().toString().equals("ADMIN")) {
            List<Token> tokens = tokenRepository.findAllTokenByUser(utilisateur.getId());
            for (Token i : tokens) {
                tokenRepository.deleteById(i.id);
            }
            iserviceUser.EditUser(utilisateur);
            return ResponseEntity.ok("Modifié avec succés");
        }
        return ResponseEntity.status(403).build();
    }
    @PutMapping("/ModifyUser")
    public ResponseEntity<String> editUser(@RequestBody Utilisateur utilisateur) {
        Utilisateur existingUserByUsername = repository.GetUserByUsername(utilisateur.getUsername());
        Utilisateur existingUserByEmail = repository.GetUserByEmail(utilisateur.getEmail());

        // Vérifier si le nom d'utilisateur existe déjà pour un autre utilisateur
        if (existingUserByUsername != null && existingUserByUsername.getId() != utilisateur.getId()) {
            return ResponseEntity.ok("Nom d'utilisateur existe déjà");
        }

        // Vérifier si l'email existe déjà pour un autre utilisateur
        if (existingUserByEmail != null && existingUserByEmail.getId() != utilisateur.getId()) {
            return ResponseEntity.ok("Email existe déjà");
        }

        // Suppression des tokens associés
        List<Token> tokens = tokenRepository.findAllTokenByUser(utilisateur.getId());
        tokenRepository.deleteAll(tokens);

        // Mise à jour de l'utilisateur
        iserviceUser.EditUser(utilisateur);

        return ResponseEntity.ok("Modifié avec succès");
    }
    @PreAuthorize("hasAuthority('admin:create')")
    @PostMapping("/registerAdmin")
    public ResponseEntity <String> regiregisterAdminster(@RequestBody Utilisateur request) {
        String password = request.getPassword();
        System.out.println("mot"+password);

        if (password == null || password.isEmpty()) {

            return ResponseEntity.badRequest().body("Le mot de passe est requis."+password);
        }



        return ResponseEntity.ok(service.registerAdmin(request)).getBody();
    }

    @PreAuthorize("hasAuthority('admin:create')")
    @GetMapping("/admin")
    public ResponseEntity<String> testAdminAccess() {
        return ResponseEntity.ok("Accès administrateur autorisé");
    }

    @PreAuthorize("hasAuthority('admin:delete')")
    @DeleteMapping("/remove-user/{idU}")
    @ResponseBody
    public void removeUser(@PathVariable("idU") Integer idU) {
        List<Token> tokens=tokenRepository.findAllTokenByUser(idU);
        for (Token i:tokens) {
            tokenRepository.deleteById(i.id);
        }
        iserviceUser.DeleteUser(idU);
    }

}