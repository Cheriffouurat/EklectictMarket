package com.example.eklecticproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Abonnement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)

    private Integer idAbonnement;
    private String nameAb;
    private LocalDate Datedebut;
    private LocalDate Datefin;
    private  boolean isActive;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Utilisateur user;
    @ManyToOne
    @JoinColumn(name = "Service_id")
    private  Services services;
    @OneToMany(mappedBy = "abonnement", cascade = CascadeType.ALL)
    private List<Offre> offres = new ArrayList<>();



}
