package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {
/*        순수 자바 테스트
        메인메서드에서 테스트 하는 것은 안좋다
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();
*/
//        AppCofig appCofig = new AppCofig();
//        MemberService memberService = appCofig.memberService();
//        OrderService orderService = appCofig.orderService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppCofig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        Long memberId = 1L;
        Member member = new Member(memberId,"itemA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order.toString());
    }
}
