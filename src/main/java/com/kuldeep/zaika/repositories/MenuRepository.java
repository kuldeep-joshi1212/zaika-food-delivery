package com.kuldeep.zaika.repositories;

import com.kuldeep.zaika.enities.Menu;
import com.kuldeep.zaika.enities.dto.MenuDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface MenuRepository extends JpaRepository<Menu,Long> {
    Menu findMenuById(Long id);


}