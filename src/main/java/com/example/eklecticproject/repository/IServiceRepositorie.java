package com.example.eklecticproject.repository;


import com.example.eklecticproject.entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IServiceRepositorie extends JpaRepository<Services,Integer> {
    Services findServicesByNameService(String name);
   // @Query("select count(pl) from Services p inner join p.postLikes pl where p.idPost = :id and  pl.react = 'LOVE'")
   // public int nbLovePost(@Param("id") Integer id);
}
