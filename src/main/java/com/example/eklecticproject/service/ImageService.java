package com.example.eklecticproject.service;


import com.example.eklecticproject.Iservice.IimageSer;
import com.example.eklecticproject.entity.Image;
import com.example.eklecticproject.entity.Services;
import com.example.eklecticproject.repository.IServiceRepositorie;
import com.example.eklecticproject.repository.ImageRepositorie;
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

    @Override
    public ResponseEntity<?> AddandAssig(MultipartFile image,Integer id) throws IOException {
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
    public void delete(int id) {
        imageRepo.deleteById((long) id);
    }

    @Override
    public boolean exists(int id) {
        return imageRepo.existsById((long) id);
    }
}
