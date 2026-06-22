package class6.springbootconversion.repository;

import class6.springbootconversion.domain.Member;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 메모리 기반으로 멤버 정보를 저장하는 저장소 구현체입니다.
 * 
 * `@Repository` 어노테이션은 다음을 의미합니다:
 * 1. 스프링이 컴포넌트 스캔 시 이 클래스를 자동으로 스프링 컨테이너의 빈(Bean)으로 등록하게 합니다.
 * 2. 데이터 접근 계층의 예외를 스프링의 일관된 데이터 접근 예외(DataAccessException)로 추상화하여 변환해 줍니다.
 * 
 * `@Primary` 어노테이션:
 * - 동일한 인터페이스(MemberRepository)를 구현한 빈이 여러 개 있을 때(MemoryMemberRepository, MockMemberRepository 등),
 *   의존성 주입 시 우선적으로 선택되도록 가중치를 부여합니다.
 */
@Repository
@Primary
public class MemoryMemberRepository implements MemberRepository {
    
    // 동시성 문제를 해결하기 위해 ConcurrentHashMap과 AtomicLong을 사용합니다.
    private final Map<Long, Member> store = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(0L);

    @Override
    public void save(Member member) {
        Long id = sequence.incrementAndGet();
        member.setId(id);
        store.put(id, member);
    }

    @Override
    public Member findByName(String name) {
        for (Member member : store.values()) {
            if (member.getName().equals(name)) {
                return member;
            }
        }
        return null;
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public boolean existsByName(String name) {
        for (Member member : store.values()) {
            if (member.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateByName(String name, Member member) {
        // 리스트를 순회하는 개념처럼 맵의 엔트리셋을 순회하며 이름이 일치하는 대상을 찾아 교체합니다.
        for (Map.Entry<Long, Member> entry : store.entrySet()) {
            if (entry.getValue().getName().equals(name)) {
                // 기존 객체의 고유 ID를 새 객체에 할당하여 식별자 일관성을 유지합니다.
                member.setId(entry.getKey());
                store.put(entry.getKey(), member);
                break;
            }
        }
    }

    @Override
    public boolean deleteByName(String name) {
        // removeIf를 사용하여 컬렉션 내에서 이름이 일치하는 회원을 간결하게 제거하고, 삭제 성공 여부를 반환합니다.
        return store.values().removeIf(m -> m.getName().equals(name));
    }
}
