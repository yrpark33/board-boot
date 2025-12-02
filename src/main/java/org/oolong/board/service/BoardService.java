package org.oolong.board.service;

import org.oolong.board.dto.BoardDTO;
import org.oolong.board.dto.PageRequestDTO;
import org.oolong.board.dto.PageResponseDTO;

public interface BoardService {

    Long register(BoardDTO boardDTO);
    BoardDTO read(Long bno);
    void modify(BoardDTO boardDTO);
    void remove(Long bno);
    PageResponseDTO<BoardDTO> getList(PageRequestDTO pageRequestDTO);
}
