package class6.springbootconversion.service;

import class6.springbootconversion.domain.Member;
import class6.springbootconversion.domain.Lion;
import class6.springbootconversion.domain.Staff;
import class6.springbootconversion.dto.*;
import class6.springbootconversion.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 회원 관련 비즈니스 로직을 처리하는 서비스 클래스입니다.
 * 
 * `@Transactional(readOnly = true)`: 읽기 전용 트랜잭션을 기본값으로 적용하여 조회 성능을 최적화합니다.
 */
@Service
@Transactional(readOnly = true)
public class MemberService {
    
    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    /**
     * 회원 가입 처리를 진행합니다. (쓰기 가능 트랜잭션 필요)
     */
    @Transactional
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
     */
    @Transactional
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
     */
    @Transactional
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
     */
    @Transactional
    public Lion updateLion(Long id, LionUpdateRequest request) {
        Member member = repository.findById(id);
        if (!(member instanceof Lion)) {
            return null;
        }
        Lion updatedLion = new Lion(
            member.getName(),
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
     */
    @Transactional
    public Staff updateStaff(Long id, StaffUpdateRequest request) {
        Member member = repository.findById(id);
        if (!(member instanceof Staff)) {
            return null;
        }
        Staff updatedStaff = new Staff(
            member.getName(),
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
     */
    @Transactional
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
