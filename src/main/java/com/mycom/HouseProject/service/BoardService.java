package com.mycom.HouseProject.service;

import com.mycom.HouseProject.model.Board;
import com.mycom.HouseProject.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public Board save(Board board) {
        LocalDate date = LocalDate.now();
        board.setDate(date);
        return boardRepository.save(board);
    }
}
