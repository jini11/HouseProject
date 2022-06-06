package com.mycom.HouseProject.service;

import com.mycom.HouseProject.model.Role;
import com.mycom.HouseProject.model.User;
import com.mycom.HouseProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User save(User user) { // 저장
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setEnabled(true);
        user.setUseradd1("");
        user.setUseradd2("");
        user.setUseradd3("");
        Role role = new Role();
        role.setId(1l);
        user.getRoles().add(role);
        return userRepository.save(user);
    }

    public User save(String userid, User user) { // 수정
        User current = userRepository.findByUserid(userid);

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        current.setPassword(encodedPassword);
        current.setUseradd1(user.getUseradd1());
        current.setUseradd2(user.getUseradd2());
        current.setUseradd3(user.getUseradd3());
        current.setUsername(user.getUsername());
        current.setEmail(user.getEmail());
        return userRepository.save(current);
    }

}
