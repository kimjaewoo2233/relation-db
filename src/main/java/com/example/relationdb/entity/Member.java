package com.example.relationdb.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Builder
@Entity
public class Member extends BaseEntity{

    @Id
    private String email;

    private String password;

    private String name;

    //Member 클래스는 이메일 주소를 pk로 사용한다 DB에서도 member테이블은 PK만 가지고
    // FK를 사용하지 않으므로 별도의 참조가 필요없다
}
