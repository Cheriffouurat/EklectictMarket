package com.example.eklecticproject.controller;


import com.example.eklecticproject.Iservice.IServiceService;
import com.example.eklecticproject.Iservice.IServiceType;
import com.example.eklecticproject.Iservice.IimageSer;
import com.example.eklecticproject.configuration.MessageResponse;
import com.example.eklecticproject.entity.Services;
import com.example.eklecticproject.entity.ServicesType;
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
@RequestMapping("/ServicesType")
public class ServicesTypeController {
   private IServiceType iServiceType;
    final IimageSer imageSer;

    @GetMapping("/allservicesType")
    List<ServicesType> findAllServicesType() {
        return iServiceType.retrieveAllServicesType();
    }
    @RequestMapping(value = "/addserviceType", method = RequestMethod.PUT)
    ServicesType addServiceType(@RequestBody ServicesType st) {
        return iServiceType.addServiceType(st);
    }
    @PutMapping("/updateserviceType")
    ServicesType updateServiceType(@RequestBody ServicesType st) {
        return iServiceType.updateServiceType(st);
    }

    @GetMapping("/getServiceType/{id}")
    ServicesType findById(@PathVariable("id") Integer id) {
        return iServiceType.retrieveServicesType(id);
    }

    @DeleteMapping( "/deleteServiceType/{id}")
    void deleteServiceType(@PathVariable("id") Integer id) {
        iServiceType.removeServiceType(id);
    }

    @PutMapping("/addAndassignServiceToServiceType/{idService}")
    ServicesType addAndassigncategorieToService(@RequestBody ServicesType S, @PathVariable("idService") Integer idService){

        return iServiceType.setServiceIdInServiceType(S,idService);
    }
    @PostMapping("/FiltresDeToutesLesServicesTypesParPrixCroissant")
    public List<ServicesType> callSortByPrice(@RequestBody List<ServicesType> serviceTypes) {
        return iServiceType.trierServiceTypesParPrixCroissant(serviceTypes);
    }
    @PostMapping("/FiltresDeToutesLesServicesTypesParPrixDecroisant")
    public List<ServicesType> callSortByPriceDecroisant(@RequestBody List<ServicesType> serviceTypes) {
        return iServiceType.trierServiceTypesParPrixDecroissant(serviceTypes);
    }
    @PostMapping("/FiltresDeToutesLesServicesTypesParNomcroisant")
    public List<ServicesType> filtreNomcroisant(@RequestBody List<ServicesType> serviceTypes) {
        return iServiceType.trierServiceTypesParNomCroisant(serviceTypes);
    }
    @PostMapping("/FiltresDeToutesLesServicesTypesParNomDecroisant")
    public List<ServicesType> filtreNomDecroisant(@RequestBody List<ServicesType> serviceTypes) {
        return iServiceType.trierServiceTypesParNomDecroisant(serviceTypes);
    }
    @PutMapping(value = "/addImageAndAssigToServiceType/{idServiceType}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    public ResponseEntity<?> addAndAssignImageToServiceType(@RequestParam("image") MultipartFile image, @PathVariable("idServiceType") Integer idSt) throws IOException {
        try{imageSer.AddandAssigType(image,idSt);
            return ResponseEntity.ok(new MessageResponse("image is added successfully!"));
        }catch ( Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse("Erreur"));
        }
    }
    @GetMapping("/servicesTypesByServiceId/{ServiceId}")
    public ResponseEntity<List<ServicesType>> servicesTypesByServiceId(@PathVariable Integer ServiceId) {
        List<ServicesType> servicesTypes = iServiceType.getAllServicesTypeByServiceId(ServiceId);
        return ResponseEntity.ok().body(servicesTypes);
    }
    @PutMapping(value = "/AjouterModifierImage/{idServiceType}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    public ResponseEntity<?> test(@RequestParam("image") MultipartFile image, @PathVariable("idServiceType") Integer idSt) throws IOException {
        try{imageSer.AjouterModifierImage(image,idSt);
            return ResponseEntity.ok(new MessageResponse("image is added successfully!"));
        }catch ( Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse("Erreur"));
        }
    }

}
