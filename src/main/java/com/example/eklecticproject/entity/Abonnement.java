package com.example.eklecticproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
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


    private Integer id;
    private String idAbonnement;
    private String tel;
    private LocalDateTime dateDebFree;
    private LocalDateTime dateFinFree;
    private LocalDateTime dateAbonnement;
    private LocalDateTime dateDesabonnement;
    private String idService;
    private String type;
    private LocalDateTime dateExpiration;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Utilisateur user;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "servicesType_id")
    private ServicesType servicesType;


}
