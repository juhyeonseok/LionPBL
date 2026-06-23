package class6.springbootconversion.domain;

import class6.springbootconversion.domain.policy.LionSubmissionPolicy;
import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;

/**
 * 아기사자(Lion) 멤버를 나타내는 JPA 엔티티 클래스입니다.
 * 
 * `@Entity`: 부모인 Member 테이블에 통합 매핑될 하위 엔티티로 등록합니다.
 * `@DiscriminatorValue("LION")`: 단일 테이블 전략 하에서 dtype 컬럼에 저장될 값을 "LION"으로 설정합니다.
 */
@Entity
@DiscriminatorValue("LION")
public class Lion extends Member {
    
    // JPA 리플렉션을 위해 final 키워드를 제거합니다.
    private String studentId;

    /**
     * JPA 스펙 준수를 위한 protected 기본 생성자
     */
    protected Lion() {
        super();
    }

    public Lion(String name, String major, int generation, String part, String studentId) {
        // 부모 생성자 호출 시, 아기사자 역할(Role.LION)과 아기사자 과제 제출 정책을 주입합니다.
        super(name, major, generation, part, Role.LION, new LionSubmissionPolicy());
        this.studentId = studentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Override
    public String getDetailString() {
        return String.format(
            "👤 역할: %s\n" +
            "📌 이름: %s | 🎓 전공: %s | 🔢 기수: %d | 💻 파트: %s\n" +
            "🆔 학번: %s\n" +
            "📝 과제 제출 가능: %s",
            getRole().getDescription(),
            getName(), getMajor(), getGeneration(), getPart(),
            studentId,
            isSubmissionEligible() ? "✅ 가능" : "❌ 불가능"
        );
    }

    @Override
    public String getSummaryString() {
        return String.format(
            "👤 [%s] %s | 🎓 %s | 🔢 %d기 | 💻 %s | 🆔 학번: %s | 📝 과제 제출: %s",
            getRole().getDescription(), getName(), getMajor(), getGeneration(), getPart(),
            studentId,
            isSubmissionEligible() ? "✅ 가능" : "❌ 불가능"
        );
    }
}
