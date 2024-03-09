package com.example.eklecticproject.repository;


import com.example.eklecticproject.entity.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ICommentRepositorie extends CrudRepository<Comment,Integer> {
    @Query("select count(pl) from Comment p inner join p.commentLikes pl where p.idComment = :id ")
    public int nbCommentLike(@Param("id") Integer id);
    @Query("select count(pl) from Comment p inner join p.commentLikes pl where  pl.react ='NULL'")
    public int nbNULLComment(@Param("id") Integer id);
}
