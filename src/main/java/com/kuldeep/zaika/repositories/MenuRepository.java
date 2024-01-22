package com.kuldeep.zaika.repositories;

import com.kuldeep.zaika.enities.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface MenuRepository extends JpaRepository<Menu,Long> {

}