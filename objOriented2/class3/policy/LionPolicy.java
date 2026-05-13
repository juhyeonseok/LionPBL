package class3.policy;

public class LionPolicy implements AssignmentPolicy {
    @Override
    public boolean canSubmit() {
        // 아기사자는 과제 제출 가능 정책을 가짐
        return true;
    }
}