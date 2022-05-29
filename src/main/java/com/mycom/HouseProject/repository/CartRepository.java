package com.mycom.HouseProject.repository;

import com.mycom.HouseProject.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAll();
    List<Cart> findByUserid(String userid);

    Cart findByid(Long id);
    //Cart findByUseridAndPidAndCount(String userid, Long pid, Long count);
}
