package com.example.eklecticproject.repository;

import com.example.eklecticproject.entity.Abonnement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
public interface IAbonnementRepositorie  extends  JpaRepository<Abonnement,Integer> {
//    Abonnement findAbonnementByNameAb(String name);

    Abonnement save(Abonnement abonnement);

}
