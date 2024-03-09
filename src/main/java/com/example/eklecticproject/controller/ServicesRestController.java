package com.example.eklecticproject.controller;


import com.example.eklecticproject.Iservice.IServiceService;
import com.example.eklecticproject.Iservice.IimageSer;
import com.example.eklecticproject.configuration.MessageResponse;
import com.example.eklecticproject.entity.Services;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
@RequestMapping("/service")
public class ServicesRestController {
   private IServiceService iServiceService;
    final IimageSer imageSer;

    @GetMapping("/allservices")
    List<Services> findAllServices() {
        return iServiceService.retrieveAllServices();
    }

    @PostMapping("/addservice")
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
    void deleteProduct(@PathVariable("id") Integer id) {
        iServiceService.removeService(id);
    }

    @PutMapping("/addImageAndAssigToServices/{idService}")
    public ResponseEntity<?> addAndAssignPostToUser(@RequestPart MultipartFile image, @PathVariable("idService") Integer idP) throws IOException {
        try{imageSer.AddandAssig(image,idP);
            return ResponseEntity.ok(new MessageResponse("image is added successfully!"));
        }catch ( Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse("Erreur"));
        }
    }
}
