package com.example.relationdb.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    private String title;

    private String contetnt;

    @ManyToOne
    @ToString.Exclude       //순환참조 방지
    private Member writer;  //연관관계 지정 여기가 N이다
    //Board 클래스는 Member 의 이메일을 FK로 참조하는 구조이다.
}
