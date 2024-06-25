package com.example.eklecticproject.service;


import com.example.eklecticproject.Iservice.IAbonnementServices;
import com.example.eklecticproject.Iservice.IServiceType;
import com.example.eklecticproject.entity.Abonnement;
import com.example.eklecticproject.entity.ServicesType;
import com.example.eklecticproject.repository.IAbonnementRepositorie;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class AbonnementService implements IAbonnementServices {
     IAbonnementRepositorie AbonnementRepositorie;
     IServiceType iServiceType;
   //  IUserServices userServices;

    @Override
    public Abonnement addAbonnement(Abonnement abonnement) {
        return AbonnementRepositorie.save(abonnement);
    }
//    @Override
//    public Abonnement getbyname(String name) {
//        return AbonnementRepositorie.findAbonnementByNameAb(name);
//    }

    @Override
    public List<Abonnement> retrieveAllAbonnement() {
        List<Abonnement> Abonnements = new ArrayList<>();
        AbonnementRepositorie.findAll().forEach(Abonnements::add);
        return Abonnements;
    }


    @Override
    public Abonnement retrieveAbonnement(Integer idAbonnement) {
        return AbonnementRepositorie.findById(idAbonnement).orElse(null);
    }

    @Override
    public void removeAbonnement(Integer idAbonnement) {
        AbonnementRepositorie.deleteById(idAbonnement);
   }

    @Override
      public Abonnement updateAbonnement(Abonnement abonnement) {

       return AbonnementRepositorie.saveAndFlush(abonnement);
    }
//    @Override
//    @Scheduled(fixedRate = 5000)
//    public void verifierEtDesactiverAbonnements() {
//        List<Abonnement> abonnements = AbonnementRepositorie.findAll();
//        LocalDate currentDate = LocalDate.now();
//
//        for (Abonnement abonnement : abonnements) {
//            if (currentDate.isAfter(abonnement.getDatefin())) {
//                abonnement.setActive(false);
//                AbonnementRepositorie.save(abonnement);
//                System.out.println("L'abonnement expiré a été désactivé.");
//            }
//        }
//    }




   /* public boolean purchaseAbonnementByPoints(User user, Services service, int durationInDays) {
        double   TotalparPoint = (durationInDays*service.getServiceLPointsParJour());
        // Vérifiez si le service peut être acheté par des points de fidélité
        if (!service.isLoyaltyPointsRedeemable()) {
            return false; // Le service ne peut pas être acheté par des points de fidélité
        }

        // Vérifiez si l'utilisateur a suffisamment de points de fidélité pour acheter le service
        if (user.getLoyaltyPoints() >= TotalparPoint) {
            // Déduisez le coût en points de fidélité de l'utilisateur
            user.setLoyaltyPoints(user.getLoyaltyPoints() - (int) TotalparPoint);

            // Créez un nouvel abonnement pour l'utilisateur avec le service acheté
            Abonnement abonnement = new Abonnement();
            abonnement.setUser(user);
            abonnement.setServices(service);
            abonnement.setDatedebut(LocalDate.now());
            // Supposons que la durée de l'abonnement soit de 30 jours
            abonnement.setDatefin(LocalDate.now().plusDays( durationInDays));

            saveAbonnement(abonnement);

            userServices.saveUser(user);
            return true; // L'achat a réussi
        } else {
            return false; // L'utilisateur n'a pas suffisamment de points de fidélité
        }
    }


    public void saveAbonnement(Abonnement abonnement) {
        AbonnementRepositorie.save(abonnement);
    }*/
   @Override

   public Abonnement setServiceTypeIdInAbonnement(Abonnement abonnement, int idServiceType) {
       if (idServiceType <= 0) {
           throw new IllegalArgumentException("idServiceType doit être positif");
       }
       ServicesType servicesType = iServiceType.retrieveServicesType(idServiceType);
       if (servicesType == null) {
           throw new IllegalArgumentException("le ServiceType avec l'ID " + idServiceType + " n'existe pas");
       }
       abonnement.setServicesType(servicesType);
       return AbonnementRepositorie.save(abonnement);
   }



}
