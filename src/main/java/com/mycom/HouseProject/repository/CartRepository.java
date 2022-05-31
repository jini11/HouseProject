package com.mycom.HouseProject.repository;

import com.mycom.HouseProject.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAll();
    List<Cart> findByUserid(String userid);

    Cart findByid(Long id);

    @Transactional
    @Modifying
    @Query(value = "DELETE from cart where cart.userid = :userid", nativeQuery = true)
    void deleteByUserid(@Param("userid") String userid);
}
