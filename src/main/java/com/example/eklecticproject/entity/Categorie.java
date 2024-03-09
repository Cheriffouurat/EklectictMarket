package com.example.eklecticproject.entity;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private int idCategorie;
    private String name;
    private String image;
    @OneToMany(mappedBy = "Categorie", cascade = CascadeType.ALL)
    private List<Services>  Services = new ArrayList<>();
}
