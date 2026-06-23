package class6.springbootconversion.domain;

import class6.springbootconversion.domain.policy.StaffSubmissionPolicy;
import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;

/**
 * 운영진(Staff) 멤버를 나타내는 JPA 엔티티 클래스입니다.
 * 
 * `@Entity`: 부모인 Member 테이블에 통합 매핑될 하위 엔티티로 등록합니다.
 * `@DiscriminatorValue("STAFF")`: 단일 테이블 전략 하에서 dtype 컬럼에 저장될 값을 "STAFF"으로 설정합니다.
 */
@Entity
@DiscriminatorValue("STAFF")
public class Staff extends Member {

    // JPA 리플렉션을 위해 final 키워드를 제거합니다.
    private String roleTitle;
    private boolean hasMeetingRoomAuthority;

    /**
     * JPA 스펙 준수를 위한 protected 기본 생성자
     */
    protected Staff() {
        super();
    }

    public Staff(String name, String major, int generation, String part, String roleTitle) {
        // 부모 생성자 호출 시, 운영진 역할(Role.STAFF)과 운영진 과제 제출 정책을 주입합니다.
        super(name, major, generation, part, Role.STAFF, new StaffSubmissionPolicy());
        this.roleTitle = roleTitle;
        this.hasMeetingRoomAuthority = true; // 기본적으로 운영진은 회의실 대여 권한을 가짐
    }

    public String getRoleTitle() {
        return roleTitle;
    }

    public void setRoleTitle(String roleTitle) {
        this.roleTitle = roleTitle;
    }

    public boolean hasMeetingRoomAuthority() {
        return hasMeetingRoomAuthority;
    }

    public void setHasMeetingRoomAuthority(boolean hasMeetingRoomAuthority) {
        this.hasMeetingRoomAuthority = hasMeetingRoomAuthority;
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
