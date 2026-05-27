package class5.role;

import class5.policy.StaffSubmissionPolicy;

public class Staff extends Member {
    private final String roleTitle;
    private final boolean hasMeetingRoomAuthority;

    public Staff(String name, String major, int generation, String part, String roleTitle) {
        super(name, major, generation, part, Role.STAFF, new StaffSubmissionPolicy());
        this.roleTitle = roleTitle;
        this.hasMeetingRoomAuthority = true;
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
