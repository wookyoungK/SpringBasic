package hello.hellospring;

import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;


// - spring bean
// 직접 설정파일을 운영하는 장점
// 컴포넌트 스캔을 사용하면 여러코드를 변경해야하지만 스프링 빈으로 등록하면 정형화 되지 않거나, 상황에 따라 구현 클래스를 변경하는것이 쉽다.
// spring이 제공하는 configuration만 변경하면 db연동가능
// 개방-폐쇄 원칙(OCP, Open-Closed Principle) 확장에는 열려있고, 수정, 변경에는 닫혀있다.
// 스프링의 DI (Dependencies Injection)을 사용하면 기존 코드를 전혀 손대지 않고, 설정만으로 구현 클래스를 변경할 수 있다.

@Configuration
public class SpringConfig {

/*    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }*/

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em){
        this.em = em;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
    //    return new MemoryMemberRepository();
    //    return new JdbcMemberRepository(dataSource); //순수 jdbc
//        return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }




}
