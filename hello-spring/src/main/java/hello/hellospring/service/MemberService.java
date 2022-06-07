package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    // private final MemberRepository memberRepository = new MemoryMemberRepository(); DI전
    private final MemberRepository memberRepository;

    //외부에서 넣어주도록 바꿔준다 new() 생성이 아닌
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
    * 회원 가입
    * */
    public Long join(Member member){
        //같은 이름이 있는 중복 회원x

        // extract method 로 만들어준것
        validateDuplicateMember(member); // 중복회원검증
        //그냥 바로 가져올수있다
        // 있으면 가져오고 없으면 default값이나 메서드 실행하는 것도 많이 사용
        // result.orElseGet()

        
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

    public Optional<Member> findOne(Long memeberID){
        return memberRepository.findById(memeberID);
    }
}
