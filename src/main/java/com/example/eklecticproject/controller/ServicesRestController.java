package com.example.eklecticproject.controller;


import com.example.eklecticproject.Iservice.IServiceService;
import com.example.eklecticproject.Iservice.IimageSer;
import com.example.eklecticproject.configuration.MessageResponse;
import com.example.eklecticproject.entity.Services;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
@RequestMapping("/service")
public class ServicesRestController {
   private IServiceService iServiceService;
    final IimageSer imageSer;


    @GetMapping("/allservices")
    List<Services> findAllServices() {
        return iServiceService.retrieveAllServices();
    }
    @GetMapping("/servicesByCategory/{categoryId}")
    public ResponseEntity<List<Services>> getServicesByCategoryId(@PathVariable Integer categoryId) {
        List<Services> services = iServiceService.getAllServicesByCategorieId(categoryId);
        return ResponseEntity.ok().body(services);
    }
    @RequestMapping(value = "/addservice", method = RequestMethod.PUT)
    Services addService(@RequestBody Services s) {
        return iServiceService.addService(s);
    }



    @PutMapping("/updateservice")
    Services updateService(@RequestBody Services s) {
        return iServiceService.updateService(s);
    }

    @GetMapping("/getService/{id}")
    Services findById(@PathVariable("id") Integer id) {
        return iServiceService.retrieveService(id);
    }

    @DeleteMapping("/deleteService/{id}")
    void deleteService(@PathVariable("id") Integer id) {
        iServiceService.removeService(id);
    }

//    @PutMapping("/addImageAndAssigToServices/{idService}")
//    public ResponseEntity<?> addAndAssignImageToServices(@RequestPart MultipartFile image, @PathVariable("idService") Integer idS) throws IOException {
//        try{imageSer.AddandAssig(image,idS);
//            return ResponseEntity.ok(new MessageResponse("image is added successfully!"));
//        }catch ( Exception e){
//            return ResponseEntity.badRequest().body(new MessageResponse("Erreur"));
//        }
//    }
    @PutMapping("/addAndassignCategorieToService/{idCategorie}")
    Services addAndassigncategorieToService(@RequestBody Services S, @PathVariable("idCategorie") Integer idCategorie){
        System.out.println(idCategorie);
        return iServiceService.setCategoryIdInService(S,idCategorie);
    }

    @PutMapping(value = "/addImageAndAssigToService/{idService}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    public ResponseEntity<?> addAndAssignImageToServiceType(@RequestParam("image") MultipartFile image, @PathVariable("idService") Integer idSt) throws IOException {
        try{imageSer.AddandAssigService(image,idSt);
            return ResponseEntity.ok(new MessageResponse("image is added successfully!"));
        }catch ( Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse("Erreur"));
        }
    }
    @PutMapping(value = "/SaveAndAssaignImagetoService/{idService}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    public ResponseEntity<?> test(@RequestParam("image") MultipartFile image, @PathVariable("idService") Integer idSt) throws IOException {
        try{imageSer.addAndAssignServicetest(image,idSt);
            return ResponseEntity.ok(new MessageResponse("image is added successfully!"));
        }catch ( Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse("Erreur"));
        }
    }
}
