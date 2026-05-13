package class3.role;

import class3.policy.AssignmentPolicy;
import class3.policy.StaffPolicy;

public class Staff extends Member {
    private String position;

    public Staff(String name, String major, int generation, String part, String position) {
        super(name, major, generation, part);
        this.position = position;
    }

    @Override
    public AssignmentPolicy getAssignmentPolicy() {
        return new StaffPolicy();
    }

    @Override
    public String getDetails() {
        return String.format("[운영진] 이름: %s, 전공: %s, 기수: %d, 파트: %s, 직책: %s",
                getName(), getMajor(), getGeneration(), getPart(), position);
    }
}