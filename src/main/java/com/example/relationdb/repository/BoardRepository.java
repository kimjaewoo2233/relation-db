package com.example.relationdb.repository;

import com.example.relationdb.entity.Board;
import com.example.relationdb.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long> {
    @Query("SELECT b,w FROM Board b LEFT JOIN b.writer w WHERE b.bno = :bno")
    Object getBoardWithWriter(@Param("bno") Long bno);

    @Query("SELECT r FROM Board b LEFT JOIN Reply r ON r.board = b" +
            " WHERE b.bno = :bno")
    List<Reply> getBoardWithReply(@Param("bno") Long bno);
    //여기서 콜론은 @Param 매개변수 타입을 넣는다는 거임

    @Query("SELECT b,w,count(r) FROM Board b LEFT JOIN b.writer w LEFT JOIN Reply r " +
            "ON r.board = b GROUP BY b")
    Page<Object[]> getBoardWithReplyCount(Pageable pageable);

}
