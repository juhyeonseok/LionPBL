package class4.role;
import class4.policy.AssignmentPolicy;
import class4.policy.StaffPolicy;
public class Staff extends Member {

    private String studentId;

    public Staff(String name, String major, int generation, String part, String studentId) {
        super(name, major, generation, part);
        this.studentId = studentId;
    }

    @Override
    public AssignmentPolicy getAssignmentPolicy() {
        return new StaffPolicy();
    }

    @Override
    public String getDetails() {
        return String.format("[운영진] 이름: %s, 전공: %s, 기수: %d, 파트: %s, 학번: %s",
                getName(), getMajor(), getGeneration(), getPart(), studentId);
    }
}
