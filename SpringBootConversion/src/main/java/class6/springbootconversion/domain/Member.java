package class6.springbootconversion.domain;

import class6.springbootconversion.domain.policy.SubmissionPolicy;
import class6.springbootconversion.domain.policy.LionSubmissionPolicy;
import class6.springbootconversion.domain.policy.StaffSubmissionPolicy;
import jakarta.persistence.*;

/**
 * 멋쟁이사자처럼 멤버들의 공통 정보를 관리하는 추상 클래스이자 JPA 엔티티입니다.
 * 
 * `@Entity`: 이 클래스를 JPA가 관리하는 엔티티로 선언하여 데이터베이스 테이블과 매핑합니다.
 * `@Inheritance`: 상속 관계 매핑 전략을 지정합니다. 여기서는 한 테이블에 모든 자식 데이터를 모으는 
 *               단일 테이블 전략(InheritanceType.SINGLE_TABLE)을 사용하여 성능을 최적화합니다.
 * `@DiscriminatorColumn`: 단일 테이블 내에서 각 행(Row)이 어떤 자식 클래스(Lion, Staff)인지 구분하기 위한 
 *                        구분 컬럼(dtype)을 지정합니다.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class Member {

    /**
     * `@Id`: 데이터베이스의 기본 키(Primary Key)를 매핑합니다.
     * `@GeneratedValue`: 기본 키 생성을 데이터베이스에 위임합니다. GenerationType.IDENTITY는 
     *                     MySQL의 Auto Increment 기능을 사용하여 자동으로 ID값을 1씩 증가시키며 채워줍니다.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // JPA 엔티티는 프록시 생성 및 리플렉션 세팅을 해야 하므로 필드에 'final' 키워드를 지정할 수 없습니다.
    private String name;
    private String major;
    private int generation;
    private String part;

    /**
     * `@Enumerated(EnumType.STRING)`: Enum 상수의 이름(문자열 자체)을 DB에 저장합니다.
     * 기본값(ORDINAL)은 순서 숫자를 저장하므로 Enum이 변경될 시 정합성이 깨질 위험이 있어 반드시 STRING을 권장합니다.
     */
    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * `@OneToMany`: 일대다 연관관계를 선언합니다.
     * mappedBy = "member": 연관관계의 주인이 자식인 Assignment 엔티티의 member 필드임을 나타냅니다.
     * cascade = CascadeType.ALL: 부모 객체의 영속성 상태 변화(생성, 수정, 삭제)를 자식 객체에 전이시킵니다.
     * orphanRemoval = true: 부모 객체인 Member의 assignments 리스트에서 자식 객체를 제거하면, 
     *                       DB 상에서도 해당 자식 데이터가 자동으로 DELETE 되도록 고아 객체 제거를 활성화합니다.
     */
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<Assignment> assignments = new java.util.ArrayList<>();

    /**
     * `@Transient`: 이 필드는 데이터베이스 테이블의 컬럼과 매핑하지 않고 제외시킵니다.
     * 자바 프로그램 실행 중에 정책 비즈니스 룰을 판별하기 위한 비영속 상태 필드입니다.
     */
    @Transient
    private SubmissionPolicy submissionPolicy;

    /**
     * JPA 스펙상 엔티티는 인자(Parameter)가 없는 기본 생성자가 필수적으로 존재해야 합니다.
     * 외부에서 직접 무분별하게 생성하지 못하도록 접근 제어자를 `protected`로 두어 캡슐화합니다.
     */
    protected Member() {
    }

    public Member(String name, String major, int generation, String part, Role role, SubmissionPolicy submissionPolicy) {
        this.name = name;
        this.major = major;
        this.generation = generation;
        this.part = part;
        this.role = role;
        this.submissionPolicy = submissionPolicy;
    }

    /**
     * `@PostLoad`: 데이터베이스에서 데이터를 조회하여 영속성 컨텍스트에 엔티티 객체로 로드된 직후 실행되는 콜백 메서드입니다.
     * 데이터베이스에는 저장되지 않는 `@Transient`인 제출 정책(SubmissionPolicy) 필드를 
     * 저장된 역할(Role)에 맞춰 런타임에 올바르게 동적 복구해 줍니다.
     */
    @PostLoad
    protected void initPolicy() {
        if (this.role == Role.LION) {
            this.submissionPolicy = new LionSubmissionPolicy();
        } else if (this.role == Role.STAFF) {
            this.submissionPolicy = new StaffSubmissionPolicy();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public SubmissionPolicy getSubmissionPolicy() {
        return submissionPolicy;
    }

    public boolean isSubmissionEligible() {
        // 정책 객체가 null인 경우(예: 로드 직후가 아닐 때 등)를 대비해 안전하게 복구 확인 후 호출합니다.
        if (submissionPolicy == null) {
            initPolicy();
        }
        return submissionPolicy != null && submissionPolicy.isEligible();
    }

    public java.util.List<Assignment> getAssignments() {
        return assignments;
    }

    /**
     * 양방향 연관관계를 맺기 위한 연관관계 편의 메서드입니다.
     * 한 번에 부모의 컬렉션에 추가하고 자식의 외래 키 객체 참조를 함께 맞춰줍니다.
     */
    public void addAssignment(Assignment assignment) {
        this.assignments.add(assignment);
        if (assignment.getMember() != this) {
            assignment.setMember(this);
        }
    }

    public abstract String getDetailString();
    
    public abstract String getSummaryString();
}
