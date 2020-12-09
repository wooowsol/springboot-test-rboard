package org.zerock.rboard.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.rboard.dto.BoardDTO;
import org.zerock.rboard.dto.PageRequestDTO;
import org.zerock.rboard.dto.PageResultDTO;
import org.zerock.rboard.entity.Board;
import org.zerock.rboard.entity.Member;
import org.zerock.rboard.repository.BoardRepository;

import java.util.function.Function;
import java.util.Optional;
import javax.transaction.Transactional;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public Long register(BoardDTO boardDTO){

        log.info("DTO------------");
        log.info(boardDTO);

        Board entity = dtoToEntity(boardDTO);

        boardRepository.save(entity);

        return entity.getGno();
    }

    @Override
    public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());

        Page<Object[]> result = boardRepository.getWithMember(pageable);

        Function<Object[], BoardDTO> fn = (arr -> entityToDTO(Board)arr[0], (Member)arr[1]));

        return new PageResultDTO<>(result, fn);
    }

    @Transactional
    @Override
    public BoardDTO read(Long gno) {

        Optional<Board> result = boardRepository.findById(gno);

        if((result.isPresent()){

            Board board = result.get();
            Member member = board.getWriter();

            return entityToDTO(board, member);
        }
        return null;
    }

    @Override
    public void remove(Long gno) {

    }

    @Override
    public void modify(BoardDTO dto) {

    }

    @Override
    public Board dtoToEntity(BoardDTO dto) {
        return null;
    }

    @Override
    public BoardDTO entityToDTO(Board entity, Member member) {
        return null;
    }
}
