package class6.springbootconversion.repository;

import class6.springbootconversion.domain.Lion;
import class6.springbootconversion.domain.Member;
import class6.springbootconversion.domain.Staff;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 테스트용 더미 데이터를 보관하는 Mock 저장소 구현체입니다.
 * 
 * `@Repository` 어노테이션을 통해 컴포넌트 스캔 대상이 되며, 빈으로 자동 등록됩니다.
 * `@Primary`가 없기 때문에 동일한 타입(MemberRepository)의 빈이 필요할 때는
 * 기본적으로 MemoryMemberRepository(@Primary가 붙은 빈)가 주입됩니다.
 */
@Repository
public class MockMemberRepository implements MemberRepository {
    private final List<Member> dummyMembers = new ArrayList<>();

    public MockMemberRepository() {
        // 더미 아기사자 및 더미 운영진을 생성하여 초기 데이터로 적재합니다.
        Lion dummyLion = new Lion("더미사자1", "컴퓨터공학과", 14, "백엔드", "202020202");
        dummyLion.setId(999L);
        dummyMembers.add(dummyLion);

        Staff dummyStaff = new Staff("더미운영진1", "소프트웨어학과", 13, "프론트엔드", "파트장");
        dummyStaff.setId(888L);
        dummyMembers.add(dummyStaff);
    }

    @Override
    public void save(Member member) {
        System.out.println("\n⚠️ [Mock Warning] Mock 저장소는 실제 데이터를 저장하지 않습니다.");
    }

    @Override
    public Member findByName(String name) {
        for (Member member : dummyMembers) {
            if (member.getName().equals(name)) {
                return member;
            }
        }
        return null;
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(dummyMembers);
    }

    @Override
    public boolean existsByName(String name) {
        for (Member member : dummyMembers) {
            if (member.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateByName(String name, Member member) {
        for (int i = 0; i < dummyMembers.size(); i++) {
            if (dummyMembers.get(i).getName().equals(name)) {
                member.setId(dummyMembers.get(i).getId());
                dummyMembers.set(i, member);
                break;
            }
        }
    }

    @Override
    public boolean deleteByName(String name) {
        return dummyMembers.removeIf(m -> m.getName().equals(name));
    }
}
