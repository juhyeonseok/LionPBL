package class6.springbootconversion.service;

import class6.springbootconversion.domain.Member;
import class6.springbootconversion.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 회원 관련 비즈니스 로직을 처리하는 서비스 클래스입니다.
 * 
 * `@Service` 어노테이션을 통해 스프링 컨테이너가 이 객체를 비즈니스 로직 빈(Bean)으로 인식하고 관리합니다.
 */
@Service
public class MemberService {
    
    // final 키워드를 사용하여 의존성 객체가 런타임에 변경되지 않도록 불변성(Immutability)을 보장합니다.
    private final MemberRepository repository;

    /**
     * 의존성 주입(DI)을 위한 생성자입니다.
     * 
     * **[생성자 주입의 특징 및 @Autowired 생략 이유]**
     * 1. 스프링 4.3 버전부터 클래스의 **생성자가 단 하나만 존재**할 경우, `@Autowired` 어노테이션을 생략해도 
     *    스프링 컨테이너가 자동으로 해당 생성자의 매개변수 타입에 맞는 빈을 찾아 주입해 줍니다.
     * 2. 생성자 주입은 다음과 같은 큰 장점들을 가집니다:
     *    - **불변성**: 주입받는 필드를 `final`로 선언할 수 있어, 런타임에 의존성이 바뀔 위험을 완전히 차단합니다.
     *    - **누락 방지(컴파일 타임 체크)**: 순수 자바 코드로 테스트를 수행할 때(예: 단위 테스트),
     *      의존성 주입을 누락하면 컴파일 에러가 발생하므로 널포인터 예외(NPE)를 사전에 방지할 수 있습니다.
     *    - **순환 참조 방지**: 애플리케이션 시작 시점에 빈들이 서로를 참조하는 순환 참조 상태를 감지하여 구동 실패 에러를 냅니다.
     */
    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public void join(Member member) {
        if (repository.existsByName(member.getName())) {
            throw new IllegalArgumentException(
                String.format("중복된 이름('%s')의 회원이 이미 존재합니다.", member.getName())
            );
        }
        repository.save(member);
    }

    public Member findMember(String name) {
        return repository.findByName(name);
    }

    public List<Member> findAllMembers() {
        return repository.findAll();
    }
}
