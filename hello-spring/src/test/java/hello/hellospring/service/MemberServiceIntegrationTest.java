package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

// 통합 테스트
// 속도가 좀 느리다.
@SpringBootTest // 스프링 컨테이너와 테스트를 함께 실행한다.
@Transactional
// 테스트 케이스에 이 애노테이션이 있으면, 테스트 시작 전에 트랜잭션을 시작하고, 테스트 완료 후에 항상 롤백한다.
// 이렇게 하면 DB에 데이터가 남지 않으므로 다음 테스트에 영향을 주지 않는다.
class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;


    //테스트 코드에서는 한글명으로 해주어도 상관은 없다 빌드시 포함되지않는다.
    @Test
    void 회원가입() {
        //given 이런 주어진것을 통해
        Member member = new Member();
        member.setName("spring100");

        //when 이것을 실행했을때
        Long saveId = memberService.join(member);

        //then 이런것이 나와야한다. 기본은 이 세개로 나뉜다. 연습하기
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    //테스트는 기본적인 사이클이 성공하는것만 중요한게 아니라 예외의 상황을 테스트하는것이 중요하다.
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


    }

}