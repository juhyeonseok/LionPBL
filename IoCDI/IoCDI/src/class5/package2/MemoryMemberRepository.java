package class5.package2;

import class5.role.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

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
}
