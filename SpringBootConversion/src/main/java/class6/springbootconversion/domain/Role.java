package class6.springbootconversion.domain;

/**
 * 멋사 멤버의 역할을 정의하는 Enum 클래스입니다.
 * 5주차의 Role 정의를 그대로 가져왔으며, '아기사자'와 '운영진' 역할을 구분합니다.
 */
public enum Role {
    LION("아기사자"),
    STAFF("운영진");

    private final String description;

    Role(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
