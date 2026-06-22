package class6.springbootconversion.domain.policy;

/**
 * 아기사자(Lion)의 과제 제출 정책입니다.
 * 아기사자는 과제 제출 대상이므로 항상 true를 반환합니다.
 */
public class LionSubmissionPolicy implements SubmissionPolicy {
    @Override
    public boolean isEligible() {
        return true; // 아기사자는 항상 제출 대상입니다.
    }
}
