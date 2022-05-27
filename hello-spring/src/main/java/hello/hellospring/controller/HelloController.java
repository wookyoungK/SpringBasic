package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    // get 방식의 method
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        /*
        * data는 model이라는 것을 통해 넘기면서
        * resources의 return의 hello와 같은 파일 즉, resources에서 hello라는 곳에 넘긴다.
        * 컨트롤러에서 리턴 값으로 문자를 반환하면 뷰 리졸버( viewResolver )가 화면을 찾아서 처리한다.
        * 스프링 부트 템플릿엔진 기본 viewName 매핑
        * resources:templates/ +{ViewName}+ .html
        * */
        return "hello";
    }
}
