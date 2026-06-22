package class6.springbootconversion.domain;

import class6.springbootconversion.domain.policy.LionSubmissionPolicy;

/**
 * 아기사자(Lion) 멤버를 나타내는 클래스입니다.
 * Member를 상속받으며, 학번(studentId) 정보를 추가로 가집니다.
 * 아기사자 전용 제출 정책(LionSubmissionPolicy)을 사용합니다.
 */
public class Lion extends Member {
    private final String studentId; // 아기사자만의 고유 정보: 학번

    public Lion(String name, String major, int generation, String part, String studentId) {
        // 부모 생성자 호출 시, 아기사자 역할(Role.LION)과 아기사자 과제 제출 정책을 주입합니다.
        super(name, major, generation, part, Role.LION, new LionSubmissionPolicy());
        this.studentId = studentId;
    }

    public String getStudentId() {
        return studentId;
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
