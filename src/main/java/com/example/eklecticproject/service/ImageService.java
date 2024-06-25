package com.example.eklecticproject.service;


import com.example.eklecticproject.Iservice.IimageSer;
import com.example.eklecticproject.entity.Image;
import com.example.eklecticproject.entity.Services;
import com.example.eklecticproject.entity.ServicesType;
import com.example.eklecticproject.repository.IServiceRepositorie;
import com.example.eklecticproject.repository.ImageRepositorie;
import com.example.eklecticproject.repository.ServiceTypeRepositorie;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ImageService implements IimageSer {
    final CloudinaryService cloudinaryService;
    final ImageRepositorie imageRepo;
    final IServiceRepositorie iServiceRepositorie;
    final ServiceTypeRepositorie serviceTypeRepositorie;


    @Override
    public List<Image> list() {
        List<Image> i = new ArrayList<>();
        imageRepo.findAll().forEach(i::add);
        return i;
    }

    @Override
    public Optional<Image> getOne(int id) {
        return imageRepo.findById((long) id);

    }

//    @Override
//    public ResponseEntity<?> AddandAssig(MultipartFile image, Integer id) throws IOException {
//        Services s = iServiceRepositorie.findById(id).orElse(null);
//        Map result = cloudinaryService.upload(image);
//        BufferedImage bi = ImageIO.read(image.getInputStream());
//        Image media = new Image((String)
//                result.get("original_filename")
//                , (String) result.get("url"),
//                (String) result.get("public_id"));
//        media.setServices(s);
//        imageRepo.save(media);
//        return null;
//    }

//    @Override
//    public ResponseEntity<?> aze(Integer categoryId) throws IOException {
//        Categorie category = iCategorieRepositorie.findById(categoryId).orElse(null);
//
//        if (category == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        Image existingImage = category.getImage();
//        long azs = category.getImage().getId();
//        if (existingImage != null) {
//            imageRepo.deleteById(azs);
//        }
//        return ResponseEntity.notFound().build();
//    }

//    @Override
//    public ResponseEntity<?> UpdateCategorieImage(MultipartFile newImage, Integer categoryId) throws IOException {
//        Categorie category = iCategorieRepositorie.findById(categoryId).orElse(null);
//
//        if (category == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//         Image existingImage = category.getImage();
//        long azs=category.getImage().getId();
//        if (existingImage  != null) {
//            imageRepo.deleteById(azs);
//        }

//
//        Map<String, String> uploadResult = cloudinaryService.upload(newImage);
//
//        Image newMedia = new Image(uploadResult.get("original_filename"),
//                uploadResult.get("url"),
//                uploadResult.get("public_id"));
//
//        newMedia.setCategorie(category);
//
//        imageRepo.save(newMedia);
//
//        return ResponseEntity.ok().build();
//    }

    @Override
    public ResponseEntity<?> AddandAssigService(MultipartFile image, Integer id) throws IOException {
        Services s=iServiceRepositorie.findById(id).orElse(null);
        Map result = cloudinaryService.upload(image);
        BufferedImage bi = ImageIO.read(image.getInputStream());
        Image media = new Image((String)
                result.get("original_filename")
                , (String) result.get("url"),
                (String) result.get("public_id"));
        media.setServices(s);
        imageRepo.save(media);
        return null;
    }
    @Override
    public ResponseEntity<?> AddandAssigType(MultipartFile image, Integer id) throws IOException {
        ServicesType s=serviceTypeRepositorie.findById(id).orElse(null);
        Map result = cloudinaryService.upload(image);
        BufferedImage bi = ImageIO.read(image.getInputStream());
        Image media = new Image((String)
                result.get("original_filename")
                , (String) result.get("url"),
                (String) result.get("public_id"));
        media.setServicesType(s);
        imageRepo.save(media);
        return null;
    }

    @Override
    public ResponseEntity<?> addAndAssignServicetest(MultipartFile image, Integer id) throws IOException {
        Services service = iServiceRepositorie.findById(id).orElse(null);

        // Vérifier si une image est fournie
        if (image == null || image.isEmpty()) {
            return ResponseEntity.badRequest().body("Please provide an image.");
        }

        // Vérifier si le service a déjà une image
        Image existingImage = service.getImage();

        // Si le service a déjà une image, mettre à jour les détails de l'image existante
        if (existingImage != null) {
            // Supprimer l'ancienne image de Cloudinary
            cloudinaryService.delete(existingImage.getImagenId());

            // Uploader la nouvelle image sur Cloudinary
            Map<String, String> result = cloudinaryService.upload(image);

            // Mettre à jour les détails de l'image existante
            existingImage.setName(result.get("original_filename"));
            existingImage.setImagenUrl(result.get("url"));
            existingImage.setImagenId(result.get("public_id"));

            // Enregistrer les modifications dans la base de données
            imageRepo.save(existingImage);
        } else {
            // Si le service n'a pas encore d'image, créer une nouvelle image
            Map<String, String> result = cloudinaryService.upload(image);

            // Créer un nouvel objet Image avec les détails de l'image uploadée
            Image newImage = new Image(
                    result.get("original_filename"),
                    result.get("url"),
                    result.get("public_id")
            );

            // Associer la nouvelle image avec le service
            newImage.setServices(service);

            // Enregistrer l'image dans la base de données
            imageRepo.save(newImage);
        }

        // Retourner une réponse indiquant que l'opération s'est terminée avec succès
        return ResponseEntity.ok().build();
    }
//    @Override
//    public ResponseEntity<?> addAndAssignImageToNewCategory(Categorie categorie, MultipartFile image) throws IOException {
//        // Vérifiez si la catégorie est nulle
//        if (categorie == null) {
//            return ResponseEntity.badRequest().body("La catégorie ne peut pas être nulle.");
//        }
//        categorie = iCategorieRepositorie.save(categorie);
//
//        // Créer l'objet Image à partir de l'image téléversée sur Cloudinary
//        Map result = cloudinaryService.upload(image);
//        Image media = new Image(
//                (String) result.get("original_filename"),
//                (String) result.get("url"),
//                (String) result.get("public_id")
//        );
//
//        // Associer l'image à la catégorie passée en paramètre
//        media.setCategorie(categorie);
//
//        // Sauvegarder l'objet Image dans la base de données
//        imageRepo.save(media);
//
//        // Retourner une réponse indiquant que l'opération s'est terminée avec succès
//        return ResponseEntity.ok().build();
//    }

    @Override
    public void delete(int id) {
        imageRepo.deleteById((long) id);
    }

    @Override
    public boolean exists(int id) {
        return imageRepo.existsById((long) id);
    }
}
