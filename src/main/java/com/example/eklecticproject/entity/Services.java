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

public class Services implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idService;
    private String nameService;
    private String description;



    @OneToOne(cascade = CascadeType.ALL, mappedBy = "services")
    Image image;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private  Categorie categorie;
    @JsonIgnore
    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
    private List<ServicesType>  ServicesType = new ArrayList<>();




}


