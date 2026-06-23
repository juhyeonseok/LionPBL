package class6.springbootconversion.repository;

import class6.springbootconversion.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA가 제공하는 JpaRepository 인터페이스입니다.
 * 
 * JpaRepository<T, ID>를 상속받으면, Spring Data JPA가 프록시 기술을 사용하여 
 * 런타임에 이 인터페이스의 구현체를 자동으로 만들고 스프링 빈으로 등록해 줍니다.
 * 
 * 기본적인 CRUD 메서드(save, findById, findAll, deleteById 등)가 이미 내장되어 있습니다.
 */
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long> {
    
    /**
     * 회원 이름을 기준으로 회원을 조회하는 쿼리 메서드입니다.
     * Spring Data JPA가 메서드 이름을 분석하여 "SELECT m FROM Member m WHERE m.name = :name" 쿼리를 자동 생성합니다.
     */
    Member findByName(String name);

    /**
     * 해당 이름을 가진 회원이 존재하는지 여부를 확인하는 쿼리 메서드입니다.
     * 마찬가지로 "SELECT COUNT(m) > 0 FROM Member m WHERE m.name = :name" 형태의 쿼리가 실행됩니다.
     */
    boolean existsByName(String name);
}
