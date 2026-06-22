package class6.springbootconversion.dto;

/**
 * 운영진(Staff) 등록 요청을 처리하기 위한 DTO 클래스입니다.
 * 클라이언트가 보내는 JSON 요청 데이터가 이 객체로 매핑됩니다.
 */
public class StaffCreateRequest {
    private String name;
    private String major;
    private int generation;
    private String part;
    private String position; // 5주차 도메인의 roleTitle 필드와 매핑됩니다.

    // Jackson 역직렬화를 위한 기본 생성자
    public StaffCreateRequest() {
    }

    public StaffCreateRequest(String name, String major, int generation, String part, String position) {
        this.name = name;
        this.major = major;
        this.generation = generation;
        this.part = part;
        this.position = position;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
