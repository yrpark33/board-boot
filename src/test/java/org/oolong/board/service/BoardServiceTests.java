package org.oolong.board.service;


import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.oolong.board.dto.BoardDTO;
import org.oolong.board.dto.PageRequestDTO;
import org.oolong.board.dto.PageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class BoardServiceTests {

    @Autowired
    BoardService boardService;

//    @Test
    public void testRegister() {

        BoardDTO dto = BoardDTO.builder().title("Sample Title").content("Sample Content").writer("user00").build();

        Long bno = boardService.register(dto);
        log.info("bno: " + bno);

    }


//    @Test
    public void testModify() {

        BoardDTO boardDTO = BoardDTO.builder().bno(101L).title("Updated Title 101").content("Updated Content 101").build();
        boardService.modify(boardDTO);

    }


    @Test
    public void testGetList() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().type("tcw").keyword("1").page(1).size(10).build();

        PageResponseDTO<BoardDTO> responseDTO = boardService.getList(pageRequestDTO);

        log.info(responseDTO);
    }

}
