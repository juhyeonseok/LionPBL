package class6.springbootconversion.domain.policy;

/**
 * 운영진(Staff)의 과제 제출 정책입니다.
 * 운영진은 과제를 제출하지 않으므로 false를 반환합니다.
 */
public class StaffSubmissionPolicy implements SubmissionPolicy {
    @Override
    public boolean isEligible() {
        return false; // 운영진은 과제 제출 대상이 아닙니다.
    }
}
