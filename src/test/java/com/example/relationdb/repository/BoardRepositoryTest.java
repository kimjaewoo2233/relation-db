package com.example.relationdb.repository;

import com.example.relationdb.entity.Board;
import com.example.relationdb.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;
    @Test
    void insertBoard(){

        IntStream.rangeClosed(1,10).forEach( i -> {
            Member member = Member.builder()
                    .email("user"+i+"@spring.com")
                    .build();

            Board board = Board.builder()
                    .title("Title..."+i)
                    .contetnt("Content..."+i)
                    .writer(member)
                    .build();

            boardRepository.save(board);
        });
        //한명의 사용자가 하나의 게시물을 등록하도록 작성함.
    }
}