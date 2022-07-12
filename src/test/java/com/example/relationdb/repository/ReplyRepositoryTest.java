package com.example.relationdb.repository;

import com.example.relationdb.entity.Board;
import com.example.relationdb.entity.Reply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReplyRepositoryTest {
    @Autowired
    ReplyRepository repository;
    @Test
    public void insertReply(){
        IntStream.rangeClosed(1,10).forEach( i -> {
            long bno = (long)(Math.random() * 10)+1;
            Board board = Board.builder().bno(bno).build();

            Reply reply = Reply.builder()
                    .text("Reply>>>>"+i)
                    .board(board)
                    .replyer("guest")
                    .build();
              repository.save(reply);
        });
    }

    @Test
    void readReply(){
        Optional<Reply> optionalReply = repository.findById(2L);
        Reply reply = optionalReply.get();
        System.out.println(reply);
        System.out.println(reply.getBoard());
     }
}