package com.example.eklecticproject.repository;


import com.example.eklecticproject.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepositorie extends JpaRepository<Image, Long> {

}
