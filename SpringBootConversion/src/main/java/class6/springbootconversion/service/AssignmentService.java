package class6.springbootconversion.service;

import class6.springbootconversion.domain.Assignment;
import class6.springbootconversion.domain.Member;
import class6.springbootconversion.dto.AssignmentCreateRequest;
import class6.springbootconversion.dto.AssignmentUpdateRequest;
import class6.springbootconversion.repository.MemberRepository;
import class6.springbootconversion.repository.SpringDataJpaAssignmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 과제(Assignment)와 관련된 비즈니스 로직을 수행하는 서비스 클래스입니다.
 * 
 * `@Transactional(readOnly = true)`: 서비스 계층 전체에 읽기 전용 트랜잭션을 적용하여 
 *                                   JPA 더티 체킹용 스냅샷 유지를 하지 않아 메모리와 조회를 최적화합니다.
 */
@Service
@Transactional(readOnly = true)
public class AssignmentService {

    private final SpringDataJpaAssignmentRepository assignmentRepository;
    private final MemberRepository memberRepository;

    public AssignmentService(SpringDataJpaAssignmentRepository assignmentRepository, MemberRepository memberRepository) {
        this.assignmentRepository = assignmentRepository;
        this.memberRepository = memberRepository;
    }

    /**
     * 특정 회원에게 과제를 등록합니다.
     * 
     * `@Transactional`: 데이터 변경(INSERT)이 발생하므로 쓰기 가능한 트랜잭션을 선언합니다.
     */
    @Transactional
    public Assignment createAssignment(Long memberId, AssignmentCreateRequest request) {
        Member member = memberRepository.findById(memberId);
        if (member == null) {
            return null; // 회원이 존재하지 않는 경우 null 반환
        }

        // 과제 객체를 생성하고 양방향 연관관계 편의 메서드를 통해 회원의 과제 목록에도 연동해 줍니다.
        Assignment assignment = new Assignment(request.getTitle(), request.getContent(), member);
        member.addAssignment(assignment);

        // 연관관계의 주인인 assignment를 저장합니다.
        return assignmentRepository.save(assignment);
    }

    /**
     * 특정 회원의 과제 목록을 구합니다.
     */
    public List<Assignment> getAssignmentsByMemberId(Long memberId) {
        return assignmentRepository.findByMemberId(memberId);
    }

    /**
     * 단건 과제를 조회합니다.
     */
    public Assignment getAssignment(Long id) {
        return assignmentRepository.findById(id).orElse(null);
    }

    /**
     * 과제 정보를 수정합니다.
     * 
     * JPA의 **변경 감지(Dirty Checking)** 기능 덕분에, 트랜잭션 범위 안에서 
     * 조회된 엔티티의 값을 변경하기만 하면 별도의 save/update 메서드를 호출하지 않아도 
     * 트랜잭션이 종료(Commit)되는 시점에 자동으로 UPDATE SQL이 데이터베이스에 실행됩니다.
     */
    @Transactional
    public Assignment updateAssignment(Long id, AssignmentUpdateRequest request) {
        Assignment assignment = assignmentRepository.findById(id).orElse(null);
        if (assignment == null) {
            return null;
        }

        // 트랜잭션 범위 내에서 엔티티 데이터 변경
        assignment.setTitle(request.getTitle());
        assignment.setContent(request.getContent());

        return assignment;
    }

    /**
     * 과제를 삭제합니다.
     */
    @Transactional
    public boolean deleteAssignment(Long id) {
        if (assignmentRepository.existsById(id)) {
            assignmentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
