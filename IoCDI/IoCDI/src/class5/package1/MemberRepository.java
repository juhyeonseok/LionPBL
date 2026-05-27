package class5.package1;

import class5.role.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class MemberRepository {
    private final Map<Long, Member> store = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(0L);

    public void save(Member member) {
        Long id = sequence.incrementAndGet();
        member.setId(id);
        store.put(id, member);
    }

    public Member findByName(String name) {
        for (Member member : store.values()) {
            if (member.getName().equals(name)) {
                return member;
            }
        }
        return null;
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public boolean existsByName(String name) {
        for (Member member : store.values()) {
            if (member.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
