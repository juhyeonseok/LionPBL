package class6.springbootconversion.domain;

import class6.springbootconversion.domain.policy.SubmissionPolicy;

/**
 * 멋쟁이사자처럼 멤버들의 공통 정보를 관리하는 추상 클래스입니다.
 * 5주차의 Member 클래스 설계를 그대로 가져왔으며, 
 * 객체지향의 다형성을 활용하여 Lion과 Staff로 분기됩니다.
 */
public abstract class Member {
    private Long id; // 데이터베이스나 메모리 저장 시 구분할 고유 식별자 ID
    private final String name; // 이름
    private final String major; // 전공
    private final int generation; // 기수 (예: 11, 12 등)
    private final String part; // 파트 (예: 백엔드, 프론트엔드 등)
    private final Role role; // 역할 (LION, STAFF)
    private final SubmissionPolicy submissionPolicy; // 과제 제출 가능 여부를 결정하는 정책

    public Member(String name, String major, int generation, String part, Role role, SubmissionPolicy submissionPolicy) {
        this.name = name;
        this.major = major;
        this.generation = generation;
        this.part = part;
        this.role = role;
        this.submissionPolicy = submissionPolicy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getMajor() {
        return major;
    }

    public int getGeneration() {
        return generation;
    }

    public String getPart() {
        return part;
    }

    public Role getRole() {
        return role;
    }

    public SubmissionPolicy getSubmissionPolicy() {
        return submissionPolicy;
    }

    /**
     * 주입된 제출 정책에 따라 과제 제출이 가능한 멤버인지 판별합니다.
     */
    public boolean isSubmissionEligible() {
        return submissionPolicy.isEligible();
    }

    // 상세 정보를 포맷팅된 문자열로 반환
    public abstract String getDetailString();
    
    // 요약 정보를 포맷팅된 문자열로 반환
    public abstract String getSummaryString();
}
