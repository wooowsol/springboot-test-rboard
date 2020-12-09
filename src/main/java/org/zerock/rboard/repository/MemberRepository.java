package org.zerock.rboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.rboard.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
}
