package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
//componentScan 에서 제외할 것들을 지정해주는 것
/*
        컴포넌트 스캔은 @Component 뿐만 아니라 다음과 내용도 추가로 대상에 포함한다.
        @Component : 컴포넌트 스캔에서 사용
        @Controlller : 스프링 MVC 컨트롤러에서 사용
        @Service : 스프링 비즈니스 로직에서 사용
        @Repository : 스프링 데이터 접근 계층에서 사용
        @Configuration : 스프링 설정 정보에서 사용
*/
@ComponentScan(
/*        탐색할 패키지의 시작 위치를 정한다. 이 패키지를 포함해서 하위패키지에서 찾는다.
        basePackages = "hello.core.member",
        basePackageClasses -> 지정한 클래스의 패키지를 탐색 시작 위치로 지정한다

        권장하는 방법
        개인적으로 즐겨 사용하는 방법은 패키지 위치를 지정하지 않고, 설정 정보 클래스의 위치를 프로젝트
        최상단에 두는 것이다. 최근 스프링 부트도 이 방법을 기본으로 제공한다.
*/

        //예제 유지를 위해 제외시킨 것 -> @Configuration이 붙은 설정 정보도 자동으로 등록되기 때문에
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
/*
    수동으로 같은 이름의 빈을 선언해주면 수동으로 잡은 것이 더 우선시 된다. 하지만 이런 애매한 경우를 안 만드는 것이 좋다.
    최근 스프링 부트에서는 오류로 결과를 나오도록 변경했다.
    변경하려면 application.properties 에서 설정 -> spring.main.allow-bean-definition-overriding=false

    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
 */



}
