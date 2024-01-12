package com.kuldeep.zaika.repositories;

import com.kuldeep.zaika.enities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
    User findByEmail(String email);
    User findUserByid(Long id);
}