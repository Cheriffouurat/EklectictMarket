package com.example.eklecticproject.repository;

import com.example.eklecticproject.entity.Comment;
import org.springframework.data.repository.CrudRepository;

public interface ICommentLikeRepositorie extends CrudRepository<Comment,Integer> {
}
