package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// @Service 추가 시 컴포넌트 스캔 및 자동 의존( 주로 사용) 제외 시 - 직접 spring bean 등록
public class MemberService {

    // private final MemberRepository memberRepository = new MemoryMemberRepository(); DI 주입 전
    private final MemberRepository memberRepository;


    //외부에서 넣어주도록 바꿔준다 new() 생성x
    // @Autowired 추가 시 컴포넌트 스캔 및 자동 의존( 주로 사용) 제외 시 - 직접 spring bean 등록
    // 스프링 컨테이너에 올라가는 것들만 @Autowired가 동작한다
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
    * 회원 가입
    * */
    public Long join(Member member){
        //같은 이름이 있는 중복 회원x

/*        Optional<Member> result = memberRepository.findByName(member.getName());
        1. Optional로 한번 감싸준 객체는 null까지 체크
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });*/

        // 첫번째(1) 방법을 extract method 로 만들어준것
        // extract method는 메소드 이름을 내가 지정(validateDuplicateMember)
        validateDuplicateMember(member); // 중복회원검증
        //그냥 바로 가져올 수 있지만 권장하지않음
        // 있으면 가져오고 없으면 default값이나 메서드 실행하는 것도 많이 사용
        // result.orElseGet()
        // 핵심관심사항 - AOP

        
        memberRepository.save(member);
        return member.getId();

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /*
    * 전체 회원 조회
    * */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    // 핵심관심사항 - AOP
    public Optional<Member> findOne(Long memeberID){
        return memberRepository.findById(memeberID);
    }
}
