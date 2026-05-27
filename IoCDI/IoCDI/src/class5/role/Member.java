package class5.role;

import class5.policy.SubmissionPolicy;

public abstract class Member {
    private Long id;
    private final String name;
    private final String major;
    private final int generation;
    private final String part;
    private final Role role;
    private final SubmissionPolicy submissionPolicy;

    public Member(String name, String major, int generation, String part, Role role, SubmissionPolicy submissionPolicy) {
        this.name = name;
        this.major = major;
        this.generation = generation;
        this.part = part;
        this.role = role;
        this.submissionPolicy = submissionPolicy;
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

    public String getMajor() {
        return major;
    }

    public int getGeneration() {
        return generation;
    }

    public String getPart() {
        return part;
    }

    public Role getRole() {
        return role;
    }

    public SubmissionPolicy getSubmissionPolicy() {
        return submissionPolicy;
    }

    public boolean isSubmissionEligible() {
        return submissionPolicy.isEligible();
    }

    public abstract String getDetailString();
    
    public abstract String getSummaryString();
}
