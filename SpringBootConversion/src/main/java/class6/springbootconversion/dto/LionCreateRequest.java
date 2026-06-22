package class6.springbootconversion.dto;

/**
 * 아기사자(Lion) 등록 요청을 처리하기 위한 DTO(Data Transfer Object) 클래스입니다.
 * 클라이언트가 보내는 JSON 요청 데이터가 이 객체로 매핑됩니다.
 */
public class LionCreateRequest {
    private String name;
    private String major;
    private int generation;
    private String part;
    private String studentId;

    // 스프링의 Jackson 역직렬화를 위해 기본 생성자가 반드시 필요합니다.
    public LionCreateRequest() {
    }

    public LionCreateRequest(String name, String major, int generation, String part, String studentId) {
        this.name = name;
        this.major = major;
        this.generation = generation;
        this.part = part;
        this.studentId = studentId;
    }

    // Jackson 바인딩 및 데이터 조회용 Getter 메서드들
    public String getName() {
        return name;
    }

    public String getMajor() {
        return major;
    }

    public int getGeneration() {
        return generation;
    }

    public String getPart() {
        return part;
    }

    public String getStudentId() {
        return studentId;
    }

    // Setter 메서드들도 생성해 줍니다.
    public void setName(String name) {
        this.name = name;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
