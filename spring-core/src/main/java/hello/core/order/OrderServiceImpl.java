package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    // 인터페이스에만 의존하도록 변경

//    수정자 주입
//    private  MemberRepository memberRepository;
//    private  DiscountPolicy discountPolicy;

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;


    /*
        의존관계 주입은 크게 4가지 방법이 있다.
        생성자 주입
        수정자 주입(setter 주입)
        필드 주입
        일반 메서드 주입

        의존관계 자동 주입은 스프링 컨테이너가 관리하는 스프링 빈이어야
        동작한다. 스프링 빈이 아닌 Member 같은 클래스에서 @Autowired 코드를 적용해도 아무 기능도
        동작하지 않는다.
    */

    /*
    생성자 주입
    이름 그대로 생성자를 통해서 의존 관계를 주입 받는 방법이다.
    지금까지 우리가 진행했던 방법이 바로 생성자 주입이다.

    특징
    생성자 호출시점에 딱 1번만 호출되는 것이 보장된다.
    불변, 필수 의존관계에 사용
    */
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


    /*
    수정자 주입
    setter라 불리는 필드의 값을 변경하는 수정자 메서드를 통해서 의존관계를 주입하는 방법이다.
    특징
    선택, 변경 가능성이 있는 의존관계에 사용
    자바빈 프로퍼티 규약의 수정자 메서드 방식을 사용하는 방법이다.
    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }
    */

    /*
        DIP 위반
        private final MemberRepository memberRepository = new MemoryMemberRepository();
        private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
        private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
        */
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        //단일체계 원칙이 잘 지킨것
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
