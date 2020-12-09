package org.zerock.rboard.service;

import org.zerock.rboard.dto.BoardDTO;
import org.zerock.rboard.dto.PageRequestDTO;
import org.zerock.rboard.dto.PageResultDTO;
import org.zerock.rboard.entity.Board;
import org.zerock.rboard.entity.Member;

public interface BoardService {

    Long register(BoardDTO boardDTO);

    PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO requestDTO);

    BoardDTO read(Long gno);

    void remove(Long gno);

    void modify(BoardDTO dto);

    default Board dtoToEntity(BoardDTO dto)  { // DTO 처리

        Member member = Member.builder().mid(dto.getWriter()).build();

        Board entity = Board.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();
        return entity;
    }

    default BoardDTO entityToDTO(Board entity, Member member)  { // Entity 처리

        BoardDTO dto = BoardDTO.builder()
                .gno(entity.getGno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(member.getMid())
                .mname(member.getMname())
                .regDate(entity.getRegdate())
                .modDate(entity.getModdate())
                .build();
        return dto;
    }
}
