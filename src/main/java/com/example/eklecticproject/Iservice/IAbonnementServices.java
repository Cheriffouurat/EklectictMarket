package com.example.eklecticproject.Iservice;


import com.example.eklecticproject.entity.Abonnement;

import java.util.List;

public interface IAbonnementServices {
    Abonnement addAbonnement(Abonnement abonnement);

//    Abonnement getbyname(String name);

    List<Abonnement> retrieveAllAbonnement();

    Abonnement retrieveAbonnement(Integer idAbonnement);

    void removeAbonnement(Integer idAbonnement);

    Abonnement updateAbonnement(Abonnement abonnement);

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
         }*/ Abonnement setServiceTypeIdInAbonnement(Abonnement abonnement, int idServiceType);


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
}
