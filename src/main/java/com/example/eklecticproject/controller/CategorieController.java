package com.example.eklecticproject.controller;

import com.example.eklecticproject.Iservice.ICategorieServices;
import com.example.eklecticproject.Iservice.IServiceService;
import com.example.eklecticproject.Iservice.IimageSer;
import com.example.eklecticproject.configuration.MessageResponse;
import com.example.eklecticproject.entity.Categorie;
import com.example.eklecticproject.entity.Image;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController

@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@RequestMapping("/Categorie")

public class CategorieController {
    ICategorieServices iCategorieServices;
    final IimageSer imageSer;
    @GetMapping("/allCategories")
    List<Categorie> findAllCategories() {
        return iCategorieServices.retrieveAllCategories();
    }


    @RequestMapping(value = "/addCategorie", method = RequestMethod.PUT)
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
    @DeleteMapping("/deleteimage/{id}")
    void deleteimages(@PathVariable("id") int id) {
        imageSer.delete(id);
    }


//    @PutMapping("/updateimage/{idCategorie}")
//
//    public ResponseEntity<?> updateimage(@RequestPart MultipartFile image, @PathVariable("idCategorie") Integer idC) throws IOException {
//        try{imageSer.UpdateCategorieImage(image,idC);
//            return ResponseEntity.ok(new MessageResponse("image is added successfully!"));
//        }catch ( Exception e){
//            return ResponseEntity.badRequest().body(new MessageResponse("Erreur"));
//        }
//    }

    @GetMapping("/getALLImage")
    public List<Image> list() {
        return imageSer.list();
    }


//    @PostMapping(value ="/categorieavecimage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<?> addAndAssignImageToNewCategory( Categorie categorie, @RequestPart("image") MultipartFile image) {
//        try {
//            // Appel de la méthode du service pour ajouter la catégorie avec l'image
//            ResponseEntity<?> response = imageSer.addAndAssignImageToNewCategory(categorie, image);
//
//            // Retourner la réponse du service
//            return response;
//        } catch (IOException e) {
//            // En cas d'erreur lors de la manipulation de l'image, renvoyer une réponse d'erreur
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors du traitement de l'image.");
//        }
//    }

}
