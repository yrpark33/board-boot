package org.oolong.board.repository;


import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.oolong.board.domain.Board;
import org.oolong.board.dto.BoardListReplyCountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {

    @Autowired
    BoardRepository boardRepository;

//    @Test
    public void testInsert() {

        IntStream.rangeClosed(1, 100).forEach(i -> {
            Board board = Board.builder().title("title..." + i).content("content..." + i).writer("user" + (i % 10)).build();
            Board result = boardRepository.save(board);
            log.info("BNO: " + result.getBno());
        });
    }

//    @Test
    public void testFindById() {
        Long bno = 100L;
        Optional<Board> result = boardRepository.findById(bno);

        Board board = result.orElseThrow();
        log.info(board);
    }

//    @Test
    public void testUpdate() {
        Long bno = 100L;

        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow();
        board.change("update 100", "update 100");
        boardRepository.save(board);

    }

//    @Test
    public void testDelete() {

        Long bno = 1L;

        boardRepository.deleteById(bno);

    }


//    @Test
    public void testFindAll() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        Page<Board> result = boardRepository.findAll(pageable);

        log.info("totalCount: " + result.getTotalElements());
        log.info("totalPages: " + result.getTotalPages());
        log.info("page number: " + result.getNumber());
        log.info("page size: " + result.getSize());

        result.getContent().forEach(board -> log.info(board));

    }

//    @Test
    public void testSearch1() {

        Pageable pageable = PageRequest.of(1, 10, Sort.by("bno").descending());
        boardRepository.search1(pageable);

    }

//    @Test
    public void testSearchAll() {

        String[] types = new String[] {"t", "c", "w"};
        String keyword = "1";

        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        Page<Board> result = boardRepository.searchAll(types, keyword, pageable);

        log.info(result.getTotalPages());
        log.info(result.getSize());
        log.info(result.getNumber());
        log.info(result.hasPrevious() + " : " + result.hasNext());

        result.getContent().forEach(board -> log.info(board));

    }

    @Test
    public void testSearchReplyCount() {

        String[] types = {"t", "c", "w"};
        String keyword = "1";

        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        Page<BoardListReplyCountDTO> result = boardRepository.searchWithReplyCount(types, keyword, pageable);

        log.info(result.getTotalPages());
        log.info(result.getSize());
        log.info(result.getNumber());
        log.info(result.hasPrevious() + " : " + result.hasNext());
        result.getContent().forEach(board -> log.info(board));

    }


}
