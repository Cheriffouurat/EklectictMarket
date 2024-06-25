package com.example.eklecticproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Offre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private Integer idOffre;
    private String nom;

    private float pourcentageDeReduction;
    private LocalDateTime dateDebutOffer;
    private LocalDateTime dateFinOffer;
    @JsonIgnore
    private boolean isactive;




    @ManyToOne
    @JoinColumn(name = "servicesType_id")
    private  ServicesType servicesType;


}
