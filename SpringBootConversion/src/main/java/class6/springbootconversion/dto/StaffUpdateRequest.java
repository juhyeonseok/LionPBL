package class6.springbootconversion.dto;

/**
 * 운영진(Staff) 수정 요청 데이터를 처리하기 위한 DTO 클래스입니다.
 * 이름(name)은 URI 경로 변수(@PathVariable)를 통해 전달받으므로 수정 요청 바디에는 포함되지 않습니다.
 */
public class StaffUpdateRequest {
    private String major;
    private int generation;
    private String part;
    private String position; // 5주차 도메인의 roleTitle 필드와 매핑됩니다.

    public StaffUpdateRequest() {
    }

    public StaffUpdateRequest(String major, int generation, String part, String position) {
        this.major = major;
        this.generation = generation;
        this.part = part;
        this.position = position;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
