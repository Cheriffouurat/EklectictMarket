package com.example.eklecticproject.repository;

import com.example.eklecticproject.entity.Offre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface IOffreRepositorie extends JpaRepository<Offre,Integer> {


    List<Offre> findByDateFinOfferBeforeAndIsactiveIsTrue(LocalDateTime dateFinOffer);
}
