package com.example.eklecticproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Services implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private Integer idService;
    private String nameService;
    private Long priceParJour;
    @OneToMany(mappedBy = "services", cascade = CascadeType.ALL)
    private List<Abonnement> abonnements = new ArrayList<>();
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "Categorie_id")
    private  Categorie Categorie;
    private boolean loyaltyPoints;
    private int ServiceLPointsParJour;

    @JsonIgnore
    boolean archiver;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "services")
    Image image;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "Services")
    Set<ServicesLike> servicesLikes ;








    public boolean isLoyaltyPointsRedeemable() {
        return loyaltyPoints;
    }


}
