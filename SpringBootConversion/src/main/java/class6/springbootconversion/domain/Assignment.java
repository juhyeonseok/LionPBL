package class6.springbootconversion.domain;

import jakarta.persistence.*;

/**
 * 과제(Assignment) 정보를 저장하는 JPA 엔티티 클래스입니다.
 * 
 * 하나의 과제는 반드시 한 명의 멤버에게만 종속되므로 Member와 N:1(다대일) 연관관계를 맺습니다.
 */
@Entity
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title; // 과제 제목
    
    @Column(columnDefinition = "TEXT")
    private String content; // 과제 내용 (긴 글 처리를 위해 TEXT 타입 지정)

    /**
     * `@ManyToOne`: 다대일 연관관계를 선언합니다.
     * fetch = FetchType.LAZY: 지연 로딩을 설정하여, 과제를 조회할 때 멤버 정보까지 불필요하게 
     *                         미리 조회하지 않고 실제 멤버 객체를 사용하는 시점에만 쿼리가 나가도록 최적화합니다.
     * `@JoinColumn`: 데이터베이스 상에서 외래 키(FK) 컬럼명을 member_id로 지정하여 매핑합니다.
     *                이 테이블에 외래 키가 존재하므로 Assignment가 연관관계의 주인이 됩니다.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    protected Assignment() {
    }

    public Assignment(String title, String content, Member member) {
        this.title = title;
        this.content = content;
        this.member = member;
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

    public Member getMember() {
        return member;
    }

    /**
     * 무한 루프를 방지하고 연관관계의 안전한 양방향 설정을 위해 
     * Member를 바인딩해 주는 연관관계 편의 메서드와 유사한 로직을 적용합니다.
     */
    public void setMember(Member member) {
        this.member = member;
    }
}
