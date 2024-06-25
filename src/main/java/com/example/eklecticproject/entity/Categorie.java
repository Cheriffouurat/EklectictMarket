package com.example.eklecticproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Categorie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCategorie;
    private String name;
    private String description;



    @JsonIgnore
    @OneToMany(mappedBy = "categorie", cascade = CascadeType.ALL)
    private List<Services>  Services = new ArrayList<>();


    public Categorie(String name, String description) {
        this.name = name;
        this.description = description;
    }



}
