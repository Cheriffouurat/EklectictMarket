package com.example.eklecticproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Comment {
    @Id
    @JsonIgnore
    Integer idComment;
    String text;
    @JsonIgnore
    LocalDateTime commentDate=LocalDateTime.now();
    @JsonIgnore
    @ManyToOne
    Utilisateur user;
//    @JsonIgnore
//    @ManyToOne
//    aaaa Services;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "comment")
    Set<CommentLike> commentLikes;
}
