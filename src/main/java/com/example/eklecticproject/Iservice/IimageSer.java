package com.example.eklecticproject.Iservice;


import com.example.eklecticproject.entity.Categorie;
import com.example.eklecticproject.entity.Image;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface IimageSer extends Serializable {
    List<Image> list();
    Optional<Image> getOne(int id);







    ResponseEntity<?> AddandAssigService(MultipartFile image, Integer id) throws IOException;

    ResponseEntity<?> AddandAssigType(MultipartFile image, Integer id) throws IOException;

    ResponseEntity<?> addAndAssignServicetest(MultipartFile image, Integer id) throws IOException;

    ResponseEntity<?> AjouterModifierImage(MultipartFile image, Integer id) throws IOException;

    void delete(int id);
    boolean exists(int id);


}
