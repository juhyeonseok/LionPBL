package class3.role;

import class3.policy.AssignmentPolicy;

public abstract class Member {
    private String name;
    private String major;
    private int generation;
    private String part;

    public Member(String name, String major, int generation, String part) {
        this.name = name;
        this.major = major;
        this.generation = generation;
        this.part = part;
    }

    // 부모 클래스의 필드가 private이므로 자식 클래스에서 사용할 수 있도록 Getter 제공
    protected String getName() { return name; }
    protected String getMajor() { return major; }
    protected int getGeneration() { return generation; }
    protected String getPart() { return part; }

    // 정책 객체를 반환하는 추상 메서드 (다형성 포인트)
    public abstract AssignmentPolicy getAssignmentPolicy();

    // 역할별 상세 정보를 반환하는 추상 메서드
    public abstract String getDetails();

    // 과제 제출 가능 여부 판단을 정책 객체에 위임
    public boolean canSubmitAssignment() {
        return getAssignmentPolicy().canSubmit();
    }

    // 정보 출력 공통 메서드
    public void printInfo() {
        System.out.println(getDetails());
        String status = canSubmitAssignment() ? "가능" : "불가능";
        System.out.println("과제 제출 가능 여부: " + status);
    }
}