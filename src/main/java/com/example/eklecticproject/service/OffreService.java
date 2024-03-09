package com.example.eklecticproject.service;


import com.example.eklecticproject.entity.Offre;
import com.example.eklecticproject.entity.Utilisateur;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OffreService {
    @Transactional
    //pour mettre à jour les points de fidélité de l'utilisateur lors de l'insertion d'une nouvelle offre :
    public void beforeInsert(Offre offre) {
        Integer points = offre.getPointsFidelite();
        Utilisateur utilisateur = offre.getAbonnement().getUser();
        utilisateur.setLoyaltyPoints(utilisateur.getLoyaltyPoints() + points);
    }

}
