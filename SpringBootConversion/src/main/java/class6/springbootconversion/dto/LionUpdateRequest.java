package class6.springbootconversion.dto;

/**
 * 아기사자(Lion) 수정 요청 데이터를 처리하기 위한 DTO 클래스입니다.
 * 이름(name)은 URI 경로 변수(@PathVariable)를 통해 전달받으므로 수정 요청 바디에는 포함되지 않습니다.
 */
public class LionUpdateRequest {
    private String major;
    private int generation;
    private String part;
    private String studentId;

    public LionUpdateRequest() {
    }

    public LionUpdateRequest(String major, int generation, String part, String studentId) {
        this.major = major;
        this.generation = generation;
        this.part = part;
        this.studentId = studentId;
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

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
