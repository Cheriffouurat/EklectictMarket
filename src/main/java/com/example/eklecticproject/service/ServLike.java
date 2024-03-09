package com.example.eklecticproject.service;


import com.example.eklecticproject.entity.Services;
import com.example.eklecticproject.entity.Utilisateur;
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
public class ServLike {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonIgnore
    Integer idServicesPost;
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
