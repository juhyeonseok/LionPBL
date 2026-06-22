package class6.springbootconversion.repository;

import class6.springbootconversion.domain.Member;
import java.util.List;

/**
 * 멤버 데이터를 관리하기 위한 저장소(Repository) 인터페이스입니다.
 * 저장 방식의 유연성을 확보하여 구체적인 저장소 구현체(Memory, Mock 등)를 갈아끼울 수 있도록 추상화합니다.
 */
public interface MemberRepository {
    
    /**
     * 회원 객체를 저장소에 저장합니다.
     */
    void save(Member member);

    /**
     * 회원 이름을 기준으로 저장소에서 회원을 찾아 반환합니다.
     */
    Member findByName(String name);

    /**
     * 저장소에 저장된 모든 회원 목록을 반환합니다.
     */
    List<Member> findAll();

    /**
     * 해당 이름을 가진 회원이 저장소에 존재하는지 여부를 확인합니다.
     */
    boolean existsByName(String name);
}
