package com.example.eklecticproject.repository;

import com.example.eklecticproject.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query(value = """
      select t from Token t inner join Utilisateur u\s
      on t.user.Id = u.Id\s
      where u.Id = :id and (t.expired = false or t.revoked = false)\s
      """)
    List<Token> findAllValidTokenByUser(Integer id);
    @Query(value = """
      select t from Token t inner join Utilisateur u\s
      on t.user.Id = u.Id\s
      where u.Id = :id
      """)
    List<Token> findAllTokenByUser(Integer id);

    Optional<Token> findByToken(String token);
}