package class6.springbootconversion.repository;

import class6.springbootconversion.domain.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * 과제(Assignment) 엔티티를 관리하기 위한 Spring Data JPA 레포지토리 인터페이스입니다.
 */
public interface SpringDataJpaAssignmentRepository extends JpaRepository<Assignment, Long> {

    /**
     * 특정 회원 고유 ID(memberId)를 조건으로 과제 목록을 구하는 쿼리 메서드입니다.
     * Spring Data JPA가 "SELECT a FROM Assignment a WHERE a.member.id = :memberId" 쿼리를 자동으로 생성합니다.
     * 
     * @param memberId 회원 ID
     * @return 해당 회원이 소유한 과제 리스트
     */
    List<Assignment> findByMemberId(Long memberId);
}
