package com.example.eklecticproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private Integer idAbonnement;
    private LocalDate Datedebut;
    private LocalDate Datefin;
    private float prix;
    private  boolean isActive;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Utilisateur user;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "servicesType_id")
    private ServicesType servicesType;


}
