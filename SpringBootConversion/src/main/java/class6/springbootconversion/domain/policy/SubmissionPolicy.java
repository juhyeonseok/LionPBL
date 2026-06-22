package class6.springbootconversion.domain.policy;

/**
 * 과제 제출 권한 여부를 판별하기 위한 정책 인터페이스입니다.
 * 객체지향적인 설계를 위해 아기사자(Lion)와 운영진(Staff)의 제출 가능 조건을 분리하는 역할입니다.
 */
public interface SubmissionPolicy {
    /**
     * 과제를 제출할 수 있는 조건이 충족되었는지 여부를 반환합니다.
     * @return 제출 가능 여부 (true/false)
     */
    boolean isEligible();
}
