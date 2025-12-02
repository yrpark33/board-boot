package org.oolong.board.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.oolong.board.domain.Board;
import org.oolong.board.dto.BoardDTO;
import org.oolong.board.dto.PageRequestDTO;
import org.oolong.board.dto.PageResponseDTO;
import org.oolong.board.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {

    private final ModelMapper modelMapper;
    private final BoardRepository boardRepository;

    @Override
    public Long register(BoardDTO boardDTO) {

        Board board = modelMapper.map(boardDTO, Board.class);
        Long bno = boardRepository.save(board).getBno();

        return bno;
    }

    @Override
    public BoardDTO read(Long bno) {

        Board board = boardRepository.findById(bno).orElseThrow();

        return modelMapper.map(board, BoardDTO.class);

    }

    @Override
    public void modify(BoardDTO boardDTO) {

        Board board = boardRepository.findById(boardDTO.getBno()).orElseThrow();
        board.change(boardDTO.getTitle(), boardDTO.getContent());
        boardRepository.save(board);

    }

    @Override
    public void remove(Long bno) {

        boardRepository.deleteById(bno);

    }

    @Override
    public PageResponseDTO<BoardDTO> getList(PageRequestDTO pageRequestDTO) {

        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("bno");


        Page<Board> result = boardRepository.searchAll(types, keyword, pageable);

        List<BoardDTO> list = result.getContent().stream().map(board -> modelMapper.map(board, BoardDTO.class)).collect(Collectors.toList());

        return PageResponseDTO.<BoardDTO>withAll().pageRequestDTO(pageRequestDTO).dtoList(list).total((int)result.getTotalElements()).build();
    }


}
