package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor // RequiredArg -> final이 붙은 생성자를 만들어준다
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

    기본으로 생성자 주입을 사용하고, 필수 값이 아닌 경우에는 수정자 주입 방식을 옵션으로 부여하면 된다.
    생성자 주입과 수정자 주입을 동시에 사용할 수 있다.

    항상 생성자 주입을 선택 그리고 가끔 옵션이 필요하면 수정자 주입을 선택해라. 필드 주입은 사용하지
    않는게 좋다.

    특징
    생성자 호출시점에 딱 1번만 호출되는 것이 보장된다.
    불변, 필수 의존관계에 사용

    장점
    생성자 주입을 사용하면 필드에 final 키워드를 사용할 수 있다. 그래서 생성자에서 혹시라도 값이
    설정되지 않는 오류를 컴파일 시점에 막아준다.


    */

    /*
        조회 대상 빈이 2개 이상일 때 해결 방법
        @Autowired 필드 명 매칭
        @Qualifier @Qualifier끼리 매칭 빈 이름 매칭
        @Primary 사용
        @Primary 는 우선순위를 정하는 방법이다. @Autowired 시에 여러 빈이 매칭되면 @Primary 가 우선권을 가진다.

        우선순위 - 스프링은 항상 자세한(상세한) 것이 우선순위가 높다,
        1. @Qualifier
        2. @Primary
        3. @Autowired
     */


        // lombok 이 만들어준다
        @Autowired
        /*
        조회 대상빈이 2개 이상일 때 해결방법
        @Autowired 매칭 정리
        타입 매칭
        타입 매칭의 결과가 2개 이상일 때 필드 명, 파라미터 명으로 빈 이름 매칭
        */
//        public OrderServiceImpl(MemberRepository memberRepository,@Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
//        public OrderServiceImpl(MemberRepository memberRepository,@MainDiscountPolicy  DiscountPolicy discountPolicy) {
        public OrderServiceImpl(MemberRepository memberRepository,DiscountPolicy discountPolicy) {
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
