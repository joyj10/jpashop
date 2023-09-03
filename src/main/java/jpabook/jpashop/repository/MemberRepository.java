package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * MemberRepository
 * <pre>
 * Describe here
 * </pre>
 *
 * @version 1.0,
 */

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Long countByName(String name);
}
