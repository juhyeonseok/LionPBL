package class6.springbootconversion.dto;

/**
 * 과제(Assignment) 정보 수정을 위한 DTO 클래스입니다.
 */
public class AssignmentUpdateRequest {
    private String title;
    private String content;

    public AssignmentUpdateRequest() {
    }

    public AssignmentUpdateRequest(String title, String content) {
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
