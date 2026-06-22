package class6.springbootconversion.dto;

import class6.springbootconversion.domain.Lion;

/**
 * 아기사자(Lion)의 정보 요청에 대해 데이터를 응답하기 위한 DTO 클래스입니다.
 */
public class LionResponse {
    private String name;
    private String major;
    private int generation;
    private String part;
    private String roleName; // 역할 명칭 (예: "아기사자")
    private String studentId;

    public LionResponse() {
    }

    public LionResponse(String name, String major, int generation, String part, String roleName, String studentId) {
        this.name = name;
        this.major = major;
        this.generation = generation;
        this.part = part;
        this.roleName = roleName;
        this.studentId = studentId;
    }

    /**
     * Lion 도메인 객체를 LionResponse DTO 객체로 변환하는 정적 팩토리 메서드입니다.
     * 
     * @param lion 아기사자 도메인 객체
     * @return 맵핑된 LionResponse DTO 객체
     */
    public static LionResponse from(Lion lion) {
        return new LionResponse(
            lion.getName(),
            lion.getMajor(),
            lion.getGeneration(),
            lion.getPart(),
            lion.getRole().getDescription(), // "아기사자"
            lion.getStudentId()
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

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
