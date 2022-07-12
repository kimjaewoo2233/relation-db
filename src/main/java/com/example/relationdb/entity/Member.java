package com.example.relationdb.entity;


import lombok.*;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
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
    //단방향으로 설계했기에 One쪽에는 필드가 없고 연관된걸 보려면 거기서 그 객체를 호출
    @OneToMany
    @Builder.Default
    private List<Board> boardList = new ArrayList<>();
    //양방향일때는 이렇게해서 조회가 가능하다 DB테이블에는 이 타입이 없다,
    //참조하는 곳에서는 키가 있어야한다.
}
