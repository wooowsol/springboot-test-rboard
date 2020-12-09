package org.zerock.rboard.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.rboard.entity.Member;

import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void insertDummies(){

        IntStream.rangeClosed(100,200).forEach(i -> {

            Member member = Member.builder().mid("M"+i).mpw("M"+i).mname("사용자"+i).build();

            System.out.println(memberRepository.save(member));
        });
    }
}
