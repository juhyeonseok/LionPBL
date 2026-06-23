package class6.springbootconversion.service;

import class6.springbootconversion.domain.Member;
import class6.springbootconversion.domain.Lion;
import class6.springbootconversion.domain.Staff;
import class6.springbootconversion.dto.*;
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

    /**
     * DTO 데이터를 기반으로 Lion(아기사자) 객체를 생성하고 저장합니다.
     * 
     * @param request 아기사자 생성 요청 DTO
     * @return 생성 성공 시 Lion 객체, 이름 중복 시 null
     */
    public Lion createLion(LionCreateRequest request) {
        if (repository.existsByName(request.getName())) {
            return null;
        }
        Lion lion = new Lion(
            request.getName(),
            request.getMajor(),
            request.getGeneration(),
            request.getPart(),
            request.getStudentId()
        );
        repository.save(lion);
        return lion;
    }

    /**
     * DTO 데이터를 기반으로 Staff(운영진) 객체를 생성하고 저장합니다.
     * 
     * @param request 운영진 생성 요청 DTO
     * @return 생성 성공 시 Staff 객체, 이름 중복 시 null
     */
    public Staff createStaff(StaffCreateRequest request) {
        if (repository.existsByName(request.getName())) {
            return null;
        }
        Staff staff = new Staff(
            request.getName(),
            request.getMajor(),
            request.getGeneration(),
            request.getPart(),
            request.getPosition()
        );
        repository.save(staff);
        return staff;
    }

    /**
     * ID로 아기사자 정보를 찾아 수정합니다.
     * 
     * @param id 대상을 찾기 위한 회원의 ID
     * @param request 수정할 정보가 담긴 DTO
     * @return 수정 성공 시 수정된 Lion 객체, 대상이 없거나 아기사자가 아니면 null
     */
    public Lion updateLion(Long id, LionUpdateRequest request) {
        Member member = repository.findById(id);
        if (!(member instanceof Lion)) {
            return null;
        }
        Lion updatedLion = new Lion(
            member.getName(), // 이름은 변경 불가능하므로 기존 멤버의 이름 유지
            request.getMajor(),
            request.getGeneration(),
            request.getPart(),
            request.getStudentId()
        );
        repository.updateById(id, updatedLion);
        return updatedLion;
    }

    /**
     * ID로 운영진 정보를 찾아 수정합니다.
     * 
     * @param id 대상을 찾기 위한 회원의 ID
     * @param request 수정할 정보가 담긴 DTO
     * @return 수정 성공 시 수정된 Staff 객체, 대상이 없거나 운영진이 아니면 null
     */
    public Staff updateStaff(Long id, StaffUpdateRequest request) {
        Member member = repository.findById(id);
        if (!(member instanceof Staff)) {
            return null;
        }
        Staff updatedStaff = new Staff(
            member.getName(), // 이름은 변경 불가능하므로 기존 멤버의 이름 유지
            request.getMajor(),
            request.getGeneration(),
            request.getPart(),
            request.getPosition()
        );
        repository.updateById(id, updatedStaff);
        return updatedStaff;
    }

    /**
     * ID로 회원을 삭제합니다.
     * 
     * @param id 삭제할 회원의 ID
     * @return 삭제 성공 시 true, 대상 회원이 없으면 false
     */
    public boolean deleteMember(Long id) {
        return repository.deleteById(id);
    }

    /**
     * ID로 특정 회원을 조회합니다.
     */
    public Member findMember(Long id) {
        return repository.findById(id);
    }

    /**
     * 저장된 모든 회원 목록을 조회합니다.
     */
    public List<Member> findAllMembers() {
        return repository.findAll();
    }
}
