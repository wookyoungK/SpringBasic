package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// - spring bean test
// 직접 설정파일을 운영하는 장점
// 컴포넌트 스캔을 사용하면 여러코드를 변경해야하지만 스프링 빈으로 등록하면 정형화 되지 않거나, 상황에 따라 구현 클래스를 변경하는것이 쉽다.
@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
