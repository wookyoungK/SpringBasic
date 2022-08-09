package hello.core.singleton;

import hello.core.AppCofig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    /*
    스프링 없는 순수한 DI 컨테이너인 AppConfig는 요청을 할 때 마다 객체를 새로 생성한다.
    즉, 메모리 낭비가 심하다. -> 객체가 딱 1개만 생성되고, 공유하도록 설계하면 된다. = 싱글톤 패턴
    */
    void pureContainer() {
        AppCofig appCofig = new AppCofig();
        //1. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService1 = appCofig.memberService();

        //2. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService2 = appCofig.memberService();

        //참조값확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1 != memberService2
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);

    }
}
