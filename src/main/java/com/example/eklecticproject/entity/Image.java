package com.example.eklecticproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String imagenUrl;
    private String imagenId;
    @JsonIgnore
    @OneToOne
    Services services;


    @JsonIgnore
    @OneToOne
    ServicesType servicesType;


    public ServicesType getServicesType() {
        return servicesType ;
    }

    public void setServicesType(ServicesType servicesType) {
        this.servicesType = servicesType;
    }
    public Services getServices() {
        return services ;
    }

    public void setServices(Services services) {
        this.services =services ;
    }





    public Image() {
    }

    public Image(String name, String imagenUrl, String imagenId) {
        this.name = name;
        this.imagenUrl = imagenUrl;
        this.imagenId = imagenId;
    }


}
