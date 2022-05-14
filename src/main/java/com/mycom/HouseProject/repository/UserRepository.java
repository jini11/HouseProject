package com.mycom.HouseProject.repository;

import com.mycom.HouseProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
