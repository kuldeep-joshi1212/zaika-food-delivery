package com.kuldeep.zaika.repositories;

import com.kuldeep.zaika.enities.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token,Long> {
    Token findTokenByUserID(Long user_id);
}