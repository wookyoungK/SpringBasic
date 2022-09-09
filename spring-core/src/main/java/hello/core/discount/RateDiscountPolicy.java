package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
// 재정의한 애노테이션
@MainDiscountPolicy
/*
        @Qualifier 정리
        1. @Qualifier끼리 매칭
        2. 빈 이름 매칭
        3. NoSuchBeanDefinitionException 예외 발생*/
public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP){
            return price * discountPercent / 100;
        } else{
            return 0;
        }
    }
}
