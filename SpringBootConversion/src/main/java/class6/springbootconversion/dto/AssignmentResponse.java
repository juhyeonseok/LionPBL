package class6.springbootconversion.dto;

import class6.springbootconversion.domain.Assignment;

/**
 * 과제(Assignment) 정보 응답을 위한 DTO 클래스입니다.
 * 양방향 연관관계로 인한 JSON 무한 참조 에러를 완전히 예방하며, 필요한 데이터만 정제하여 반환합니다.
 */
public class AssignmentResponse {
    private Long id;
    private String title;
    private String content;
    private Long memberId; // 과제가 할당된 회원 ID

    public AssignmentResponse() {
    }

    public AssignmentResponse(Long id, String title, String content, Long memberId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.memberId = memberId;
    }

    /**
     * Assignment 엔티티 객체를 AssignmentResponse DTO로 매핑하여 반환하는 팩토리 메서드입니다.
     * 
     * @param assignment 과제 엔티티 객체
     * @return 맵핑 완료된 AssignmentResponse DTO
     */
    public static AssignmentResponse from(Assignment assignment) {
        return new AssignmentResponse(
            assignment.getId(),
            assignment.getTitle(),
            assignment.getContent(),
            assignment.getMember() != null ? assignment.getMember().getId() : null
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
}
