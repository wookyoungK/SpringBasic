package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

// @Repository - 추가 시 컴포넌트 스캔 및 자동 의존( 주로 사용) 제외 시 - 직접 spring bean 등록
public class MemoryMemberRepository implements MemberRepository {

    // 실무에서는 동시성 문제가 있어서 해당 변수들을 다른방법으로 고려해봐야한다.
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

     /*
     * interface에 있는 method? 오버라이드
      */
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //null 체크
        return Optional.ofNullable(store.get(id));
    }
    // 람다식, Optional 클래스
    @Override
    public Optional<Member> findByName(String name) {
       return  store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }
    // 제네릭스 보기
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }

}
