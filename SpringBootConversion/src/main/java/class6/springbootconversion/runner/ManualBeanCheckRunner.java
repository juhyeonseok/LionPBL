package class6.springbootconversion.runner;

import class6.springbootconversion.domain.Lion;
import class6.springbootconversion.domain.Member;
import class6.springbootconversion.service.MemberService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 애플리케이션 시작 직후 자동으로 실행되어, 수동으로 등록한 빈이 스프링 컨테이너에 잘 올라갔는지 검증하는 러너 클래스입니다.
 * 
 * `CommandLineRunner` 인터페이스를 구현하면, Spring Boot 애플리케이션 구동이 완료된 시점에 
 * run(String... args) 메서드가 자동으로 호출됩니다.
 */
@Component
public class ManualBeanCheckRunner implements CommandLineRunner {

    // 스프링 컨테이너 자체를 의미하는 ApplicationContext를 주입받아 빈을 동적으로 직접 꺼냅니다.
    private final ApplicationContext applicationContext;

    public ManualBeanCheckRunner(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n==================================================");
        System.out.println("🔍 [검증 시작] 수동 Bean 등록 및 동작 확인");
        System.out.println("==================================================");

        try {
            // 1. ApplicationContext.getBean()을 활용하여 수동 등록된 "manualMemberService"를 가져옵니다.
            MemberService manualService = applicationContext.getBean("manualMemberService", MemberService.class);
            System.out.println("✅ 수동 등록된 Bean 'manualMemberService' 획득 성공!");

            // 2. 수동 빈의 실제 동작(회원 가입 및 조회)을 테스트합니다.
            Member testLion = new Lion("테스트사자", "컴퓨터공학", 11, "백엔드", "2023112233");
            manualService.join(testLion);
            System.out.println("👉 수동 빈 서비스를 통한 테스트 회원 등록 완료: " + testLion.getName());

            Member findLion = manualService.findMember(testLion.getId());
            System.out.println("👉 수동 빈 서비스를 통한 테스트 회원 조회 성공!");
            System.out.println("📋 조회 결과 요약: " + findLion.getSummaryString());

        } catch (Exception e) {
            System.err.println("❌ 수동 등록 Bean 검증 실패: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("==================================================\n");
    }
}
