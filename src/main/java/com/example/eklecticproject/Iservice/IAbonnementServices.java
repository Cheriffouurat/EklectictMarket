package com.example.eklecticproject.Iservice;


import com.example.eklecticproject.entity.Abonnement;

import java.util.List;

public interface IAbonnementServices {
    Abonnement addAbonnement(Abonnement abonnement);

    Abonnement getbyname(String name);

    List<Abonnement> retrieveAllAbonnement();

    Abonnement retrieveAbonnement(Integer idAbonnement);

    void removeAbonnement(Integer idAbonnement);

    Abonnement updateAbonnement(Abonnement abonnement);
    void verifierEtDesactiverAbonnements();
}
