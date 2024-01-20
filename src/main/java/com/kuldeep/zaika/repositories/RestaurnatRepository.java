package com.kuldeep.zaika.repositories;

import com.kuldeep.zaika.enities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurnatRepository extends JpaRepository<Restaurant,Long> {
    Restaurant findRestaurantByid(Long id);
    Restaurant findRestaurantByEmail(String email);

}