package class6.springbootconversion.dto;

/**
 * 과제(Assignment) 생성 요청을 위한 DTO 클래스입니다.
 */
public class AssignmentCreateRequest {
    private String title;
    private String content;

    public AssignmentCreateRequest() {
    }

    public AssignmentCreateRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
