package org.i4di.account.repository;


import org.i4di.doku.domain.Category;
import org.i4di.doku.domain.Token;
import org.i4di.doku.domain.User;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    List<Token> findAll();

    Optional<Token> findByUserEmail(String email);

    Optional<Token> findByValue(String value);

    Optional<Token> findByExpireTime(DateTime expireTime);

    Optional<Token> findByValueAndCategory(String value, Category category);

    Optional<Token> findByUser(User user);

}
