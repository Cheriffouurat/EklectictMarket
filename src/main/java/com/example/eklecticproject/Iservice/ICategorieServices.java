package com.example.eklecticproject.Iservice;



import com.example.eklecticproject.entity.Categorie;

import java.util.List;

public interface ICategorieServices {

    Categorie addCategorie(Categorie categorie);

    Categorie getbyrname(String name);

    List<Categorie> retrieveAllCategories();


    Categorie retrieveCategorie(Integer idCategorie);

    void removeCategorie(Integer idCategorie);

    Categorie updateCategorie(Categorie categorie);
}
