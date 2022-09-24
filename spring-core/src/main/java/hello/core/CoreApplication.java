package hello.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//스프링 부트의 대표 시작 정보
//이 설정안에 @ComponentScan 이 들어있다

/*
	spring-boot-starter-web 라이브러리를 추가하면 스프링 부트는 내장 톰켓 서버를 활용해서 웹
	서버와 스프링을 함께 실행시킨다.

	스프링 부트는 웹 라이브러리가 없으면 우리가 지금까지 학습한
	>	AnnotationConfigApplicationContext 을 기반으로 애플리케이션을 구동한다. 웹 라이브러리가
		추가되면 웹과 관련된 추가 설정과 환경들이 필요하므로
	> 	AnnotationConfigServletWebServerApplicationContext 를 기반으로 애플리케이션을 구동한다.

	>	main/resources/application.properties : port 변경

*/
@SpringBootApplication
public class CoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}

}
