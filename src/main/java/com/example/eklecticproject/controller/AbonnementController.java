package com.example.eklecticproject.controller;

import com.example.eklecticproject.Iservice.IAbonnementServices;
import com.example.eklecticproject.Iservice.IServiceType;
import com.example.eklecticproject.entity.Abonnement;
import com.example.eklecticproject.entity.Services;
import com.example.eklecticproject.entity.ServicesType;
import com.example.eklecticproject.service.PDFGeneratorServices;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
@RequestMapping("/Abonnement")
public class AbonnementController {
    IAbonnementServices iAbonnementServices;

    private final PDFGeneratorServices pdfGeneratorServices;

    @GetMapping("/generate-pdf")
    public void generatePdf(@RequestParam Integer idA, HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=abonnement.pdf");
        pdfGeneratorServices.export(response, idA);
    }
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
//    @GetMapping("/desactiverAbonnements")
//    public String desactiverAbonnements() {
//        iAbonnementServices.verifierEtDesactiverAbonnements();
//        return "Opération de désactivation des abonnements effectuée avec succès.";
//    }
    @PutMapping("/addAndassignServicetypeToAbonnement/{idServicetype}")
    Abonnement addAndassigncategorieToService(@RequestBody Abonnement A, @PathVariable("idServicetype") Integer idServicetype){

        return iAbonnementServices.setServiceTypeIdInAbonnement(A,idServicetype);
    }

}
