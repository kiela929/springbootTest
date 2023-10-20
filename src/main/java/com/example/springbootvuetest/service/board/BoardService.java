package com.example.springbootvuetest.service.board;

import com.example.springbootvuetest.entity.www.board.BoardEntity;
import com.example.springbootvuetest.model.request.board.BoardRequest;
import com.example.springbootvuetest.model.response.board.BoardResponse;
import com.example.springbootvuetest.model.response.board.Header;
import com.example.springbootvuetest.model.response.board.Pagination;
import com.example.springbootvuetest.repository.mysql.www.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private ModelMapper modelMapper = null;

    public List<BoardResponse> selectBoardList() {
        Pageable paging = PageRequest.of(0, 10, Sort.by("id").descending());
        var boardList = boardRepository.findAll(paging);
        modelMapper = new ModelMapper();
        var result = new ArrayList<BoardResponse>();
        boardList.stream().forEach(board -> result.add(modelMapper.map(board, BoardResponse.class)));

        return result;
    }

    public BoardResponse selectBoard(Integer id) {
        var board = boardRepository.findById(id).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        modelMapper = new ModelMapper();
        return modelMapper.map(board, BoardResponse.class);
    }

    @Transactional("wwwTransactionManager")
    public BoardResponse saveBoard(BoardRequest boardRequest) {
        modelMapper = new ModelMapper();
        return modelMapper.map(boardRepository.save(modelMapper.map(boardRequest, BoardEntity.class)), BoardResponse.class);
    }


    @Transactional("wwwTransactionManager")
    public void removeBoard(Integer id) {
        modelMapper = new ModelMapper();
        var board = boardRepository.findById((id)).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        boardRepository.delete(modelMapper.map(board, BoardEntity.class));
    }

    public Header<List<BoardResponse>> selectBoardList(Pageable pageable) {
        modelMapper = new ModelMapper();
        var list = new ArrayList<BoardResponse>();
        var boardEntities = boardRepository.findAllByOrderByIdDesc(pageable);

        boardEntities.stream().forEach(board -> list.add(modelMapper.map(board, BoardResponse.class)));

        Pagination pagination = new Pagination(
                (int) boardEntities.getTotalElements(),
                pageable.getPageNumber()+1,
                pageable.getPageSize(),
                10
        );

        return Header.OK(list, pagination);
    }
}
