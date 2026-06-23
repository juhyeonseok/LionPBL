package class6.springbootconversion.repository;

import class6.springbootconversion.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 메모리 기반으로 멤버 정보를 저장하는 저장소 구현체입니다.
 * 
 * 8주차부터는 JpaMemberRepository를 기본 저장소(@Primary)로 사용하므로, 
 * 이 클래스는 주입 순위에서 밀려나 백업 혹은 테스트 목적으로 남게 됩니다 (@Primary 제거).
 */
@Repository
public class MemoryMemberRepository implements MemberRepository {
    
    private final Map<Long, Member> store = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(0L);

    @Override
    public void save(Member member) {
        Long id = sequence.incrementAndGet();
        member.setId(id);
        store.put(id, member);
    }

    @Override
    public Member findById(Long id) {
        return store.get(id);
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
    public void updateById(Long id, Member member) {
        if (store.containsKey(id)) {
            member.setId(id);
            store.put(id, member);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        return store.remove(id) != null;
    }
}
