package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component //Bean 에 등록해서 명시적으로 하는것도 좋음

/*
* 공톰 관심 사항 - AOP
* 만약 핵심 관심 사항이 아닌 메소드별 시간을 측정 해보려고 할 때 메소드별로 하나하나 해당 코드를 작성해야한다. -> 메소드수가 100개 이상이라면?
* 1. 해당 코드 때문에 핵심 관심 사항(회원가입, 조회 ..등) 코드가 복잡해지거나 보기 어려워진다.
* 2. 일일이 100개를 작성하기에도 시간이 오래걸린다.
* 3. 유지보수 어려움
*
* 이러한 공통 관심 사항을 한 곳 에서 묶어서 처리해주는 것
* */

public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*())") //수 많은 문법중 하나
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("START: "+ joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: "+joinPoint.toString() + " " + timeMs + "ms");

        }
    }
}
