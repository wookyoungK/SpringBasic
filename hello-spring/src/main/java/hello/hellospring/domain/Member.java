package hello.hellospring.domain;

import javax.persistence.*;

/*
* 도메인 주로 데이터베이스에 저장 관리됨
* */
@Entity
public class Member {
    // PK
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //DB가 ex) seq 알아서 생성해주는것
    private Long id;

    //@Column(name= "username")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
