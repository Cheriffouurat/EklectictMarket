package com.example.eklecticproject.controller;

import com.example.eklecticproject.Iservice.ICategorieServices;
import com.example.eklecticproject.Iservice.IServiceService;
import com.example.eklecticproject.entity.Categorie;
import com.example.eklecticproject.entity.Services;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
@RequestMapping("/Categorie")

public class CategorieController {
    ICategorieServices iCategorieServices;
    @GetMapping("/allCategories")
    List<Categorie> findAllCategories() {
        return iCategorieServices.retrieveAllCategories();
    }

    @PostMapping("/addCategorie")
    Categorie addCategorie(@RequestBody Categorie c) {
        return iCategorieServices.addCategorie(c);
    }

    @PutMapping("/updateCategorie")
    Categorie updateCategorie(@RequestBody Categorie c) {
        return iCategorieServices.updateCategorie(c);
    }

    @GetMapping("/getCategorie/{id}")
    Categorie findById(@PathVariable("id") Integer id) {
        return iCategorieServices.retrieveCategorie(id);
    }

    @DeleteMapping("/deleteCategorie/{id}")
    void deleteCategorie(@PathVariable("id") Integer id) {
        iCategorieServices.removeCategorie(id);
    }
}
