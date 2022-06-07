package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    //여기서 왜 이부분을 추가해주는지 ??
    MemoryMemberRepository memberRepository;
    //test에 있는 인스턴스(저장소)를 같은 것을 쓰기위해서

    @BeforeEach //각 테스트 실행전에
    public void beforeEach(){

        // 멤버서비스에 같은메모리멤버리포를 넣어준다
        //DI : Dependency Injection
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }


    //테스트 코드에서는 한글명으로 해주어도 상관은 없다 빌드시 포함되지않는다.
    @Test
    void 회원가입() {
        //given 이런 주어진것을 통해
        Member member = new Member();
        member.setName("spring");

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


/*        memberService.join(member1);
        try {
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/

        //then


    }



    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}