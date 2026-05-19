package class4.policy;

public class StaffPolicy implements AssignmentPolicy {
    @Override
    public boolean canSubmit() {
        // 운영진은 과제 제출 불가능 정책을 가짐
        return false;
    }
}
