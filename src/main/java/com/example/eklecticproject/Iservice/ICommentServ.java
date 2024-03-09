package com.example.eklecticproject.Iservice;




import com.example.eklecticproject.entity.Comment;

import java.util.List;

public interface ICommentServ {
    List<Comment> getAllComment();

    Comment updateComment(Comment c);

    Comment addComment(Comment c);

    Comment getOneComment(Integer idC);

    void removeComment(Integer idC);

    Comment addAndAssignCommentToPostUser(Comment comment, Integer idPost, Integer iduser);

    int nbCommentLike(Integer id);

    String[] CommentaireSuggestion(Integer idPost);
}
