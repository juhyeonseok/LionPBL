package class5.role;

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
