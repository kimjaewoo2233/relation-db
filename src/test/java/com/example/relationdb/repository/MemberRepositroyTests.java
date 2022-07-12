package com.example.relationdb.repository;

import com.example.relationdb.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositroyTests {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void insertMembers(){
        IntStream.rangeClosed(1,10).forEach( i -> {
            Member member = Member.builder()
                    .email("user"+i+"@spring.com")
                    .password("111")
                    .name("User"+i)
                    .build();
            memberRepository.save(member);
        });

    }
    @Test
    void testMember(){
        Member member = memberRepository.findById("user1@spring.com").orElseThrow(RuntimeException::new);
        member.getBoardList().forEach(System.out::println);
    }
}
