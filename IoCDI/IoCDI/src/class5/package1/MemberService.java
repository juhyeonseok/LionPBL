package class5.package1;

import class5.role.Member;
import java.util.List;

public class MemberService {
    private final MemberRepository repository = new MemberRepository();

    public void join(Member member) {
        if (repository.existsByName(member.getName())) {
            throw new IllegalArgumentException(
                String.format("중복된 이름('%s')의 회원이 이미 존재합니다.", member.getName())
            );
        }
        repository.save(member);
    }

    public Member findMember(String name) {
        return repository.findByName(name);
    }

    public List<Member> findAllMembers() {
        return repository.findAll();
    }
}
