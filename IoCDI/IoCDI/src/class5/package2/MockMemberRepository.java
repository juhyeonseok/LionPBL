package class5.package2;

import class5.role.Lion;
import class5.role.Member;
import class5.role.Staff;

import java.util.ArrayList;
import java.util.List;

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
}
