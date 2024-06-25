package com.example.eklecticproject.controller;

import com.example.eklecticproject.Iservice.IAbonnementServices;
import com.example.eklecticproject.Iservice.IOffreServices;
import com.example.eklecticproject.entity.Abonnement;
import com.example.eklecticproject.entity.Offre;
import com.example.eklecticproject.entity.Services;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
@RequestMapping("/Offer")
public class OfferController {
    IOffreServices iOffreServices;


    @PutMapping("/addAndassignServicetypeToOffre/{idServicetype}")
    Offre addAndassigncategorieToService(@RequestBody Offre O, @PathVariable("idServicetype") Integer idServicetype){

        return iOffreServices.setServiceTypeIdInOffer22(O,idServicetype);
    }

    @GetMapping("/allOffre")
    List<Offre> findallOffres() {
        return iOffreServices.AllOffre();
    }

}
