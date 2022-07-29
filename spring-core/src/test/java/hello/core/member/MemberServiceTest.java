package hello.core.member;

import hello.core.AppCofig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// 테스트는 필수
public class MemberServiceTest {

    //MemberService memberService = new MemberServiceImpl();
    MemberService memberService;

    // 각 test 실행 전 먼저 무조건 실행
    @BeforeEach
    public void beforeEach(){
        AppCofig appCofig = new AppCofig();
        memberService = appCofig.memberService();
    }

    @Test
    void join(){
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);


        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);


        //then
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
