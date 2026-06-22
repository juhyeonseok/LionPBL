package class6.springbootconversion.domain;

import class6.springbootconversion.domain.policy.StaffSubmissionPolicy;

/**
 * 운영진(Staff) 멤버를 나타내는 클래스입니다.
 * Member를 상속받으며, 직책(roleTitle) 및 회의실 대여 권한(hasMeetingRoomAuthority) 정보를 가집니다.
 * 운영진 전용 제출 정책(StaffSubmissionPolicy)을 사용합니다.
 */
public class Staff extends Member {
    private final String roleTitle; // 운영진만의 고유 정보: 직책 (예: 회장, 교육팀장 등)
    private final boolean hasMeetingRoomAuthority; // 회의실 대여 권한 여부

    public Staff(String name, String major, int generation, String part, String roleTitle) {
        // 부모 생성자 호출 시, 운영진 역할(Role.STAFF)과 운영진 과제 제출 정책을 주입합니다.
        super(name, major, generation, part, Role.STAFF, new StaffSubmissionPolicy());
        this.roleTitle = roleTitle;
        this.hasMeetingRoomAuthority = true; // 기본적으로 운영진은 회의실 대여 권한을 가짐
    }

    public String getRoleTitle() {
        return roleTitle;
    }

    public boolean hasMeetingRoomAuthority() {
        return hasMeetingRoomAuthority;
    }

    @Override
    public String getDetailString() {
        return String.format(
            "👤 역할: %s\n" +
            "📌 이름: %s | 🎓 전공: %s | 🔢 기수: %d | 💻 파트: %s\n" +
            "👔 직책: %s\n" +
            "🔑 회의실 대여 권한: %s | 📝 과제 제출 가능: %s",
            getRole().getDescription(),
            getName(), getMajor(), getGeneration(), getPart(),
            roleTitle,
            hasMeetingRoomAuthority ? "✅ 가능" : "❌ 불가능",
            isSubmissionEligible() ? "✅ 가능" : "❌ 불가능"
        );
    }

    @Override
    public String getSummaryString() {
        return String.format(
            "👤 [%s] %s | 🎓 %s | 🔢 %d기 | 💻 %s | 👔 직책: %s | 🔑 회의실 대여: %s | 📝 과제 제출: %s",
            getRole().getDescription(), getName(), getMajor(), getGeneration(), getPart(),
            roleTitle,
            hasMeetingRoomAuthority ? "✅ 가능" : "❌ 불가능",
            isSubmissionEligible() ? "✅ 가능" : "❌ 불가능"
        );
    }
}
