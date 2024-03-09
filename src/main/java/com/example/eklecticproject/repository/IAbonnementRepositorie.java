package com.example.eklecticproject.repository;

import com.example.eklecticproject.entity.Abonnement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAbonnementRepositorie  extends  JpaRepository<Abonnement,Integer> {
    Abonnement findAbonnementByNameAb(String name);

}
