package com.example.eklecticproject.controller;


import com.example.eklecticproject.Iservice.IserviceUser;
import com.example.eklecticproject.auditing.ApplicationAuditAware;
import com.example.eklecticproject.entity.Token;
import com.example.eklecticproject.entity.Utilisateur;
import com.example.eklecticproject.repository.TokenRepository;
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