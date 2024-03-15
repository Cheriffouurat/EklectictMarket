package com.example.eklecticproject.controller;

import com.example.eklecticproject.Iservice.IAbonnementServices;
import com.example.eklecticproject.entity.Abonnement;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
@RequestMapping("/Abonnement")
public class AbonnementController {
    IAbonnementServices iAbonnementServices;

    @GetMapping("/allAbonnements")
    List<Abonnement> findAllAbonnements() {
        return iAbonnementServices.retrieveAllAbonnement();
    }

    @PostMapping("/addAbonnement")
    Abonnement addAbonnement(@RequestBody Abonnement c) {
        return iAbonnementServices.addAbonnement(c);
    }

    @PutMapping("/updateAbonnement")
    Abonnement updateAbonnement(@RequestBody Abonnement c) {
        return iAbonnementServices.updateAbonnement(c);
    }

    @GetMapping("/getAbonnement/{id}")
    Abonnement findById(@PathVariable("id") Integer id) {
        return iAbonnementServices.retrieveAbonnement(id);
    }

    @DeleteMapping("/deleteAbonnement/{id}")
    void deleteAbonnement(@PathVariable("id") Integer id) {
        iAbonnementServices.removeAbonnement(id);
    }
    @GetMapping("/desactiverAbonnements")
    public String desactiverAbonnements() {
        iAbonnementServices.verifierEtDesactiverAbonnements();
        return "Opération de désactivation des abonnements effectuée avec succès.";
    }
}
