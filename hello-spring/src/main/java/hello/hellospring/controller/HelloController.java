package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    //@GetMapping
    // get 방식의 method
    // 받는 url name

    @GetMapping("hello")
    // 정적인 컨텐츠
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
    @GetMapping("hello-mvc")
    // MVC, template 엔진
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
        // 받는 template engine
    }

    @GetMapping("hello-string")
    @ResponseBody //http body 직접 반환
    public String helloSpring(@RequestParam("name") String name){
        return "hello "+name;
    }

    @GetMapping("hello-api")
    @ResponseBody //Json 반환이 기본이다.
    // Api
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;
        //getter,setter = 자바bean 방식, property 방식
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }



}
