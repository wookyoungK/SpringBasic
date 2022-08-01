package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {

/*        메인메서드에서 테스트 하는 것은 안좋다
        순수 자바 테스트
        MemberService memberService = new MemberServiceImpl();
*/

//        AppCofig appCofig = new AppCofig();
//        MemberService memberService = appCofig.memberService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppCofig.class);
        //스프링 컨테이너에서 꺼내는것
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}
