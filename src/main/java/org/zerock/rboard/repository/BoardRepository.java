package org.zerock.rboard.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.rboard.entity.Board;
import org.zerock.rboard.entity.Member;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query(value = "SELECT b, w FROM Board b left outer join b.writer w ",
            countQuery = "SELECT COUNT(b) FROM Board b")
    Page<Object[]> getWithMember(Pageable pageable);

    void deleteBoardByWriter(Member member);

}
