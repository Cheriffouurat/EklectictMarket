package com.example.eklecticproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

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
    private Integer pointsFidelite;
    private LocalDate DateDePointParOffre;
    @ManyToOne
    @JoinColumn(name = "Abonnement_id")
    private  Abonnement abonnement;


}
