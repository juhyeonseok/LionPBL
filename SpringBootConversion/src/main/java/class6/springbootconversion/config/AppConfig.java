package class6.springbootconversion.config;

import class6.springbootconversion.repository.MemberRepository;
import class6.springbootconversion.repository.MemoryMemberRepository;
import class6.springbootconversion.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 수동으로 스프링 빈(Bean)을 등록하고 의존성을 조립하는 설정 클래스입니다.
 * 
 * `@Configuration` 어노테이션은 이 클래스가 스프링 컨테이너의 설정 파일임을 나타냅니다.
 * 이 클래스 내에서 `@Bean` 어노테이션이 붙은 메서드들을 스프링이 호출하고, 반환된 객체들을 스프링 컨테이너의 빈으로 관리하게 됩니다.
 */
@Configuration
public class AppConfig {

    /**
     * 수동으로 MemberRepository 빈을 등록합니다.
     * 빈의 중복을 피하고 명확하게 식별하기 위해 이름을 "manualMemberRepository"로 지정합니다.
     * 
     * @return MemoryMemberRepository 객체
     */
    @Bean(name = "manualMemberRepository")
    public MemberRepository manualMemberRepository() {
        // 직접 new 연산자를 이용해 인스턴스를 생성하고 반환합니다.
        // 스프링 컨테이너는 이 반환된 객체를 싱글톤 빈으로 관리하게 됩니다.
        System.out.println("🔧 [수동 빈 등록] manualMemberRepository 등록 중...");
        return new MemoryMemberRepository();
    }

    /**
     * 수동으로 MemberService 빈을 등록합니다.
     * 마찬가지로 빈의 중복을 피하기 위해 이름을 "manualMemberService"로 지정합니다.
     * 생성자 주입 방식으로 manualMemberRepository()의 결과를 주입받습니다.
     * 
     * @return manualMemberRepository가 주입된 MemberService 객체
     */
    @Bean(name = "manualMemberService")
    public MemberService manualMemberService() {
        System.out.println("🔧 [수동 빈 등록] manualMemberService 등록 중...");
        // 수동으로 생성한 manualMemberRepository() 빈을 매개변수로 전달하여 수동 의존성 주입(DI)을 처리합니다.
        return new MemberService(manualMemberRepository());
    }
}
