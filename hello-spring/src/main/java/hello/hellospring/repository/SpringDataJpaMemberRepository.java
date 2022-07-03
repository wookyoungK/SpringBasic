package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// SpringDataJpa 가 구현체를 자동으로 등록해준다. 가져다 쓰면된다.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>, MemberRepository {


    /*
    * 보통 공통적으로 생각하는 것 findAll() 같은 것 들은 다 SpringDataJpa가 제공해준다.
    * 하지만 해당 메서드와 같이 찾는 내용이 프로젝트에 따라 다른 것 ex) String name으로 찾는다/ String doc_id로 찾는다. 이런 내용들은
    * 다르기 때문에 해당과 같이 선언해주어야한다.
    * 여기에도 문법 findByNameAAndname() 이런 문법이 있지만 나중에 JPA 공부 시 찾아볼것
    * JPA, SpringDataJpa, Querydsl 라이브러리 를 자유롭게 사용할 줄 알아야한다.
    * */

    // JPQL select m from Member m where m.name = ? 을 실행해준다.
    @Override
    Optional<Member> findByName(String name);
}
