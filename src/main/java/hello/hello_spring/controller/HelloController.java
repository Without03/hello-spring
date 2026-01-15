package hello.hello_spring.controller;

import org.springframework.resilience.annotation.ConcurrencyLimit;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }

    /*
    반환
    static content: 가공되지 않은 파일
    mvc: 처리된 html
    api: 데이터

    static content로 파일을 전달할때 서버에서 처리
    파일을 그대로 전달

    cotroller와 view의 분리 이유
    관심사 분리, 유지보수

    api(객체 반환)의 사용 목적
    클라이언트(앱, 웹), 다른 서버에 구조화된 데이터만 전달하기 위해

    spring에서 controller method가 obj를 반환하고 @ResponsBody가 있을때, object를 client에 전달 가능한 형태로 반환하는 역할을 하는것
    HTTP Message Converter
     */
    @GetMapping("hello-api")
    @ResponseBody // 객체가 오면 json 방식으로 넘김 객체-jsonconverter, 문자열-stringconverter
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    public class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


    }
}

