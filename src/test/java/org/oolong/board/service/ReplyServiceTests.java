package org.oolong.board.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.oolong.board.dto.ReplyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class ReplyServiceTests {

    @Autowired
    ReplyService replyService;


    @Test
    public void testRegister() {
        ReplyDTO replyDTO = ReplyDTO.builder().replyText("ReplyDTO text").replier("replier").bno(100L).build();
        log.info(replyService.register(replyDTO));

    }
}
