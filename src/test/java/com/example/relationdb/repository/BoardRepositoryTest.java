package com.example.relationdb.repository;

import com.example.relationdb.entity.Board;
import com.example.relationdb.entity.Member;
import com.example.relationdb.entity.Reply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private ReplyRepository repository;
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
    @Test
    @Transactional
    void testRead(){
        Optional<Board> boardOptional
                = boardRepository.findById(1L);
        Board board = boardOptional.get();
        System.out.println(board);
        System.out.println(board.getWriter());
        //board에서 Many관계로 Member한명을 가진다. Member가 One이다.
        // 단방향으로 설정했기에 값을 조회할 필요가 없다
        // Board를 즉시로딩 처리하면 join으로 결과값을 받아오고
        // 지연로딩 처리하면 getWirter로 Member 테이블 데이터를
        // 가져오기 위해  Member 테이블을 읽는다. -join을 안하고
        // Board 조회한 후 member 조회한다.
        // 그리고 이때 해당 메소드를 트랜잭션 처리를 하지 않으면
        // board만 읽고 종료된 후 값을 찾기에 에러난다. 그래서 트랜잭션처리필요

    }
    @Test
    public void boardWithReply(){
        //8번은 댓글이 두개임
        List<Reply> replys = boardRepository.getBoardWithReply(8L);
        replys.forEach(System.out::println);
    }
    @Test
    @Transactional
    void pageTest(){
        Pageable pageable = PageRequest.of(1,5, Sort.by("bno").descending());

        Page<Object[]> page = boardRepository.getBoardWithReplyCount(pageable);

        page.forEach( row -> {
            Object[] arr = (Object[]) row;
            System.out.println(Arrays.toString(arr));
        });
    }
}