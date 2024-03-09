package com.example.eklecticproject.repository;


import com.example.eklecticproject.entity.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategorieRepositorie extends  JpaRepository<Categorie,Integer> {
    Categorie findCategorieByname(String name);
}
