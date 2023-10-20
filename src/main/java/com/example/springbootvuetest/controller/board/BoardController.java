package com.example.springbootvuetest.controller.board;

import com.example.springbootvuetest.model.request.board.BoardRequest;
import com.example.springbootvuetest.model.response.board.BoardResponse;
import com.example.springbootvuetest.model.response.board.Header;
import com.example.springbootvuetest.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

//    @GetMapping("/list")
//    public List<BoardResponse> selectBoardAllList() {
//        return boardService.selectBoardList();
//    }

    @GetMapping("/list")
    public Header<List<BoardResponse>> selectBoardAllList(@PageableDefault(sort = {"id"})Pageable pageable) {
        return boardService.selectBoardList(pageable);
    }

    @GetMapping("/detail/{id}")
    public BoardResponse selectBoard(@PathVariable Integer id) {
        return boardService.selectBoard(id);
    }

    @PostMapping("/save")
    public BoardResponse createBoard(@RequestBody BoardRequest boardRequest) {
        System.out.println(boardRequest.toString());
        return boardService.saveBoard(boardRequest);
    }

    @DeleteMapping("/{id}")
    public void removeBoard(@PathVariable Integer id) {
        boardService.removeBoard(id);
    }

}
