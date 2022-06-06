package com.mycom.HouseProject.service;

import com.mycom.HouseProject.model.Board;
import com.mycom.HouseProject.model.User;
import com.mycom.HouseProject.repository.BoardRepository;
import com.mycom.HouseProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    public Board save(String userid, Board board) { // 수정
        User user = userRepository.findByUserid(userid);
        board.setUser(user);
        LocalDate date = LocalDate.now();
        board.setDate(date);
        return boardRepository.save(board);
    }
}
