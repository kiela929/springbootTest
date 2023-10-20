package com.example.springbootvuetest.model.response.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pagination {

    private int pageSize;       // 페이지당 보여지는 게시글 최대 개수
    int page;                   // 현재 페이지
    int block;                  // 현재 블럭
    int totalListCnt;           // 총 게시글 수
    int totalPageCnt;           // 총 페이지 수
    int totalBlockCnt;          // 총 구간 수
    int startPage;              // 시작 페이지
    int endPage;                // 마지막 페이지
    int prevBlock;              // 이전 구간 마지막 페이지
    int nextBlock;              // 다음 구간 시작 페이지
    int startIndex;             // 인덱스

    public Pagination(Integer totalListCnt, Integer page, Integer pageSize, Integer blockSize) {
        this.pageSize = pageSize;
        this.page = page;                                                           //현재 페이지
        this.totalListCnt = totalListCnt;                                           //총 게시글 수
        totalPageCnt = (int) Math.ceil(totalListCnt * 1.0 / this.pageSize);         //총 페이지 수
        totalBlockCnt = (int) Math.ceil(totalPageCnt * 1.0 / blockSize);            //총 블럭 수
        block = (int) Math.ceil((this.page * 1.0) / blockSize);                     //현재 블럭
        startPage = ((block - 1) * blockSize + 1);                                  //블럭 시작 페이지
        endPage = startPage + blockSize - 1;                                        //블럭 마지막 페이지
        if (endPage > totalPageCnt) endPage = totalPageCnt;                         //블럭 마지막 페이지 validation
        prevBlock = (block * blockSize) - blockSize;                                // 이전 블럭 (클릭 시, 이전 블럭 마지막 페이지)
        if (prevBlock < 1) prevBlock = 1;                                           // 이전 블럭 validation
        nextBlock = (block * blockSize + 1);                                        //다음 블럭 (클릭 시, 다음 블럭 첫번째 페이지)
        if (nextBlock > totalPageCnt) nextBlock = totalPageCnt;                     // 다음 블럭 validation
        //if(this.page < 1) this.page = 1
        startIndex = (this.page - 1) * this.pageSize;
    }
}
