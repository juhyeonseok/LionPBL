package class6.springbootconversion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot 애플리케이션의 시작점(Entry Point) 클래스입니다.
 * 
 * `@SpringBootApplication` 어노테이션은 다음 3가지 핵심 역할을 자동으로 설정해 줍니다:
 * 1. `@SpringBootConfiguration`: 이 클래스가 스프링 부트의 설정 클래스임을 선언합니다.
 * 2. `@EnableAutoConfiguration`: 스프링 부트가 클래스패스에 있는 라이브러리들을 바탕으로 필요한 빈들을 자동으로 설정해 줍니다. (예: Web starter가 있으므로 내장 톰캣 서버 구동 등)
 * 3. `@ComponentScan`: 이 패키지(class6.springbootconversion) 및 그 하위 패키지에서
 *    `@Component`, `@Service`, `@Repository`, `@Controller` 등이 붙은 클래스들을 찾아 스프링 컨테이너에 빈(Bean)으로 등록합니다.
 */
@SpringBootApplication
public class SpringBootConversionApplication {

    public static void main(String[] args) {
        // 스프링 컨테이너를 구동하고 애플리케이션을 실행시킵니다.
        SpringApplication.run(SpringBootConversionApplication.class, args);
    }
}
