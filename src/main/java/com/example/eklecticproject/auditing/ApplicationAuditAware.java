package com.example.eklecticproject.auditing;

import com.example.eklecticproject.entity.Utilisateur;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class ApplicationAuditAware implements AuditorAware<Integer> {
    @Override
    public Optional<Integer> getCurrentAuditor() {
        System.out.println("je suis là");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getPrincipal());
        if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
            return Optional.empty();
        }

        Utilisateur userPrincipal = (Utilisateur) authentication.getPrincipal();
        System.out.println(userPrincipal);
        return Optional.ofNullable(userPrincipal.getId());
    }
}
