package com.example.eklecticproject.service;

import com.example.eklecticproject.Iservice.ICategorieServices;
import com.example.eklecticproject.entity.Categorie;
import com.example.eklecticproject.repository.ICategorieRepositorie;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CategorieService implements ICategorieServices {

    ICategorieRepositorie CategorieRepositorie;

    @Override
    public Categorie addCategorie(Categorie categorie) {
        return CategorieRepositorie.save(categorie);
    }
    @Override
    public Categorie getbyrname(String name) {
        return CategorieRepositorie.findCategorieByname(name);
    }


    @Override
    public List<Categorie> retrieveAllCategories() {
        List<Categorie> Categories = new ArrayList<>();
        CategorieRepositorie.findAll().forEach(Categories::add);
        return Categories;
    }

    @Override
    public Categorie retrieveCategorie(Integer idCategorie) {
        return CategorieRepositorie.findById(idCategorie).orElse(null);
    }

    @Override
    public void removeCategorie(Integer idCategorie) {
        CategorieRepositorie.deleteById(idCategorie);
    }

    @Override
    public Categorie updateCategorie(Categorie categorie) {

        return CategorieRepositorie.saveAndFlush(categorie);
    }
}

