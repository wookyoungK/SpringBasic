package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
/*     구현 객체를 생성
     생성자를 통해서 주입

    역할, 구현을 구분 했지만 어떻게 구현했는지 한 눈에 보여야 한다.

* Spring Start
* @Bean : 스프링 컨테이너에 객체를 스프링 빈으로 등록
*
* */

/*    singleton을 어긴 것 일까?
    @Bean memberService ->   new MemoryMemberRepository();
    @Bean orderService ->  new MemoryMemberRepository();

    논리적으로는 테스트를 해보면
    call AppConfig.memberRepository가 3번 호출 되어야 한다

    결과 :
    call AppConfig.memberService
    call AppConfig.memberRepository
    call AppConfig.orderService
    -> spring container가 해주는 일 보기

    @Bean만 사용해도 스프링 빈으로 등록되지만, 싱글톤을 보장하지 않는다.
    스프링 설정 정보는 항상 @Configuration을 사용하자
*/

    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());

    }

    @Bean
    public DiscountPolicy discountPolicy(){
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
