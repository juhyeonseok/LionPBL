package class6.springbootconversion.dto;

import class6.springbootconversion.domain.Staff;

/**
 * 운영진(Staff)의 정보 요청에 대해 데이터를 응답하기 위한 DTO 클래스입니다.
 */
public class StaffResponse {
    private String name;
    private String major;
    private int generation;
    private String part;
    private String roleName; // 역할 명칭 (예: "운영진")
    private String position; // 직책

    public StaffResponse() {
    }

    public StaffResponse(String name, String major, int generation, String part, String roleName, String position) {
        this.name = name;
        this.major = major;
        this.generation = generation;
        this.part = part;
        this.roleName = roleName;
        this.position = position;
    }

    /**
     * Staff 도메인 객체를 StaffResponse DTO 객체로 변환하는 정적 팩토리 메서드입니다.
     * 
     * @param staff 운영진 도메인 객체
     * @return 맵핑된 StaffResponse DTO 객체
     */
    public static StaffResponse from(Staff staff) {
        return new StaffResponse(
            staff.getName(),
            staff.getMajor(),
            staff.getGeneration(),
            staff.getPart(),
            staff.getRole().getDescription(), // "운영진"
            staff.getRoleTitle() // 직책
        );
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
