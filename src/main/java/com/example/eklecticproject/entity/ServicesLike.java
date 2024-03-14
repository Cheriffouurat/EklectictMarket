package com.example.eklecticproject.entity;


import com.example.eklecticproject.enumerations.React;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ServicesLike {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonIgnore
    Integer idServicesLike;
    @Enumerated(EnumType.STRING)
    React react;
    @JsonIgnore
    LocalDateTime LikeServicesDate=LocalDateTime.now();
    @JsonIgnore
    @ManyToOne
    Utilisateur user;
    @JsonIgnore
    @ManyToOne
    Services Services ;
}
