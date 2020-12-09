package org.zerock.rboard.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.rboard.entity.Board;
import org.zerock.rboard.entity.Member;

import java.awt.print.Pageable;
import java.util.stream.IntStream;
import java.util.Optional;

@SpringBootTest
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void testInsert(){

        IntStream.rangeClosed(100,200).forEach(i -> {

            Member writer = Member.builder().mid("M"+ (i+100)).build();

            Board board = Board.builder()
                    .title("Title..." + i)
                    .content("Content" + i)
                    .writer(writer)
                    .build();

            boardRepository.save(board);
        });
    }

    @Test
    @Transactional
    public void testRead(){

        Long gno = 1L;

        Optional<Board> result = boardRepository.findById(gno);

        Board board = result.get();

        System.out.println("------------------------");
        System.out.println(board.getGno());
        System.out.println(board.getTitle());
        System.out.println(board.getWriter());

    }

    @Test
    public void testPaging(){

        PageRequest pageable = PageRequest.of(0, 10, Sort.by("gno").descending());

        Page<Board> result = boardRepository.findAll(pageable);

        System.out.println("--------------------");

        result.get().forEach(board ->{
//            System.out.println(board.getGno() +":" +board.getTitle() +": "+board.getWriter().getMid());
            System.out.println(board.getGno() +":" +board.getTitle() +": "+board.getWriter().getMname());

        });

    }

    @Commit
    @Transactional
    @Test
    public void testDeleteMember(){

        Member member = Memeber.builder().mid("M200").build();

        boardRepository.deleteBoardByWriter(member);

        memberRepository.delete(member);
    }


}
