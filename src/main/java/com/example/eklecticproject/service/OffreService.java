package com.example.eklecticproject.service;


import com.example.eklecticproject.Iservice.IOffreServices;
import com.example.eklecticproject.Iservice.IServiceType;
import com.example.eklecticproject.entity.Offre;
import com.example.eklecticproject.entity.ServicesType;
import com.example.eklecticproject.repository.IOffreRepositorie;
import com.example.eklecticproject.repository.ServiceTypeRepositorie;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class OffreService implements IOffreServices {
    IServiceType iServiceType;
    ServiceTypeRepositorie serviceTypeRepositorie;
    IOffreRepositorie iOffreRepositorie;
//    @Transactional
//    //pour mettre à jour les points de fidélité de l'utilisateur lors de l'insertion d'une nouvelle offre :
//    public void beforeInsert(Offre offre) {
//        Integer points = offre.getPointsFidelite();
//        Utilisateur utilisateur = offre.getAbonnement().getUser();
//        utilisateur.setLoyaltyPoints(utilisateur.getLoyaltyPoints() + points);
//    }

    @Override

    public Offre setServiceTypeIdInOffer(Offre Offre, int idServiceType) {
        if (idServiceType <= 0) {
            throw new IllegalArgumentException("idServiceType doit être positif");
        }
        ServicesType servicesType = iServiceType.retrieveServicesType(idServiceType);
        if (servicesType == null) {
            throw new IllegalArgumentException("le ServiceType avec l'ID " + idServiceType + " n'existe pas");
        }
        Offre.setServicesType(servicesType);
        return iOffreRepositorie.save(Offre);
    }


    @Override
    public Offre setServiceTypeIdInOffer22(Offre offre, int idServiceType) {
        if (idServiceType <= 0) {
            throw new IllegalArgumentException("L'ID du type de service doit être positif");
        }

        ServicesType servicesType = iServiceType.retrieveServicesType(idServiceType);
        if (servicesType == null) {
            throw new IllegalArgumentException("Le type de service avec l'ID " + idServiceType + " n'existe pas");
        }

        // Calculer le prix après l'offre
        Float prixApresOffre = servicesType.getPrix() - (servicesType.getPrix() * offre.getPourcentageDeReduction() / 100);

        // Mettre à jour l'offre
        offre.setServicesType(servicesType);
        offre.getServicesType().setPrixApresOffer(prixApresOffre);
        offre.getServicesType().setOnOffer(true);

        return iOffreRepositorie.save(offre);
    }
//    @Scheduled(fixedRate = 60000) // Exécuter toutes les minutes
    public void verifierOffresExpirées() {
        List<Offre> offresExpirées = iOffreRepositorie.findByDateFinOfferBeforeAndIsactiveIsTrue(LocalDateTime.now());

        for (Offre offre : offresExpirées) {
            ServicesType serviceType = offre.getServicesType();
            if (serviceType != null) {
               Float prix = serviceType.getPrix();
                serviceType.setPrixApresOffer(prix);
            serviceType.setOnOffer(false);
            offre.setIsactive(false);

            serviceTypeRepositorie.save(serviceType);
            iOffreRepositorie.save(offre);
        }

        }
    }
    @Override
    public List<Offre> AllOffre(){
        List<Offre> offre = new ArrayList<>();

        iOffreRepositorie.findAll().forEach(offre::add);
        return offre;   }
}


