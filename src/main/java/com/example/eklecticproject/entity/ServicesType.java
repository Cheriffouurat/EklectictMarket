package com.example.eklecticproject.entity;



import com.example.eklecticproject.configuration.GrantedAuthorityDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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
    public class ServicesType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer Id;


        private String nom;



        private float prix;


        @Enumerated(EnumType.STRING)

        private ServiceTypeEnum periode;



        private LocalDate date;


        private Float prixApresOffer;


        private boolean onOffer;



        @JsonIgnore
        @ManyToOne
    @JoinColumn(name = "service_id")
    private  Services service;
        @JsonIgnore

        @OneToMany(mappedBy = "servicesType", cascade = CascadeType.ALL)
    private List<Abonnement> abonnements = new ArrayList<>();

        @JsonIgnore
        @OneToMany(mappedBy = "servicesType", cascade = CascadeType.ALL)
    private List<Offre> offres = new ArrayList<>();




        @OneToOne(cascade = CascadeType.ALL, mappedBy = "servicesType")
        Image image;
        @JsonIgnore
        @OneToMany(cascade = CascadeType.ALL, mappedBy = "servicesType")
        private List<ServicesLike> servicesLikes ;

    }

