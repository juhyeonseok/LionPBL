package class6.springbootconversion.repository;

import class6.springbootconversion.domain.Member;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * JPA 기반으로 멤버 데이터를 관리하는 저장소 구현체입니다.
 * 
 * `@Repository`: 스프링 컨테이너의 빈으로 등록합니다.
 * `@Primary`: 동일한 MemberRepository 인터페이스를 구현한 여러 빈 중, 
 *             이 JPA 구현체를 최우선 순위로 서비스 계층에 자동 주입하도록 설정합니다.
 * `@Transactional`: 이 클래스의 데이터 변경 작업(CUD)이 트랜잭션 범위 안에서 수행되도록 보장합니다.
 */
@Repository
@Primary
@Transactional(readOnly = true)
public class JpaMemberRepository implements MemberRepository {

    // Spring Data JPA 인터페이스를 생성자 주입 방식으로 협력 객체로 사용합니다.
    private final SpringDataJpaMemberRepository springDataJpaMemberRepository;

    public JpaMemberRepository(SpringDataJpaMemberRepository springDataJpaMemberRepository) {
        this.springDataJpaMemberRepository = springDataJpaMemberRepository;
    }

    @Override
    @Transactional
    public void save(Member member) {
        // save() 메서드는 엔티티를 영속화하여 INSERT SQL을 실행합니다.
        springDataJpaMemberRepository.save(member);
    }

    @Override
    public Member findById(Long id) {
        // findById는 Optional을 반환하므로, 데이터가 없으면 null을 반환하도록 예외 처리를 덧붙입니다.
        return springDataJpaMemberRepository.findById(id).orElse(null);
    }

    @Override
    public Member findByName(String name) {
        return springDataJpaMemberRepository.findByName(name);
    }

    @Override
    public List<Member> findAll() {
        return springDataJpaMemberRepository.findAll();
    }

    @Override
    public boolean existsByName(String name) {
        return springDataJpaMemberRepository.existsByName(name);
    }

    @Override
    @Transactional
    public void updateById(Long id, Member member) {
        // 이미 식별자(ID)가 세팅되어 있는 객체를 save()하면 JPA는 병합(merge)을 시도하며 
        // 데이터베이스에 UPDATE 쿼리를 준비하여 트랜잭션 커밋 시점에 실행합니다.
        member.setId(id);
        springDataJpaMemberRepository.save(member);
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        // 데이터가 실제로 존재하는지 확인한 후 삭제를 수행하고 성공 여부를 반환합니다.
        if (springDataJpaMemberRepository.existsById(id)) {
            springDataJpaMemberRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
