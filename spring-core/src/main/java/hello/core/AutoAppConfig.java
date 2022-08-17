package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
//componentScan 에서 제외할 것들을 지정해주는 것
@ComponentScan(
        //예제 유지를 위해 제외시킨 것 -> @Configuration이 붙은 설정 정보도 자동으로 등록되기 때문에
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
}
