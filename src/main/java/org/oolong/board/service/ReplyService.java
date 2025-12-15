package org.oolong.board.service;

import org.oolong.board.dto.PageRequestDTO;
import org.oolong.board.dto.PageResponseDTO;
import org.oolong.board.dto.ReplyDTO;

public interface ReplyService {

    Long register(ReplyDTO replyDTO);

    ReplyDTO read(Long rno);

    void modify(ReplyDTO replyDTO);

    void remove(Long rno);

    PageResponseDTO<ReplyDTO> getListOfBoard(Long bno, PageRequestDTO pageRequestDTO);


}
