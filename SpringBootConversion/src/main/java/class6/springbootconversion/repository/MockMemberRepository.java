package class6.springbootconversion.repository;

import class6.springbootconversion.domain.Lion;
import class6.springbootconversion.domain.Member;
import class6.springbootconversion.domain.Staff;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 테스트용 더미 데이터를 보관하는 Mock 저장소 구현체입니다.
 * ID 기반 CRUD로 확장된 MemberRepository 인터페이스 규격에 맞춰 재구성하였습니다.
 */
@Repository
public class MockMemberRepository implements MemberRepository {
    private final List<Member> dummyMembers = new ArrayList<>();

    public MockMemberRepository() {
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
    public Member findById(Long id) {
        for (Member member : dummyMembers) {
            if (member.getId().equals(id)) {
                return member;
            }
        }
        return null;
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
    public void updateById(Long id, Member member) {
        for (int i = 0; i < dummyMembers.size(); i++) {
            if (dummyMembers.get(i).getId().equals(id)) {
                member.setId(id);
                dummyMembers.set(i, member);
                break;
            }
        }
    }

    @Override
    public boolean deleteById(Long id) {
        return dummyMembers.removeIf(m -> m.getId().equals(id));
    }
}
