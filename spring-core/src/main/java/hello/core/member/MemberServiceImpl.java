package hello.core.member;

public class MemberServiceImpl implements MemberService{
    //  private final MemberRepository memberRepository = new MemoryMemberRepository();
    //  추상화에만 의존 -> DIP를 지킨
    //  의존관계 고민은 외부, 실행에만 집중
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
