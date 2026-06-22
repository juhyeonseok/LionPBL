package class6.springbootconversion.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 첫 REST API를 테스트하기 위한 컨트롤러 클래스입니다.
 * 
 * `@RestController` 어노테이션:
 * 1. `@Controller`와 `@ResponseBody`가 합쳐진 어노테이션입니다.
 * 2. 이 컨트롤러 내의 모든 메서드는 뷰 템플릿(HTML)을 반환하는 대신, 
 *    반환값(문자열, JSON 등)을 HTTP 응답 바디(Body)에 직접 작성하여 클라이언트에 바로 전달합니다.
 */
@RestController
public class HelloController {

    /**
     * "/hello" 경로로 들어오는 HTTP GET 요청을 처리합니다.
     * 
     * `@GetMapping("/hello")`:
     * - HTTP GET 메서드로 "/hello" URI가 호출되었을 때 이 메서드가 실행되도록 매핑합니다.
     * 
     * @return 브라우저에 직접 출력할 환영 메시지 문자열
     */
    @GetMapping("/hello")
    public String hello() {
        return "Hello, Likelion!";
    }
}
