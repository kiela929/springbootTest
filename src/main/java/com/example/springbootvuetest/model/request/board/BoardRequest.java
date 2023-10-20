package com.example.springbootvuetest.model.request.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardRequest {

    private Long id;
    private String category;
    private Integer ref;
    private String mid;
    private String name;
    private String bPasswd;
    private String ip;
    private String title;
    private String contents;
    private String writeDay;
    private String email;
    private String readCount;
    private String fileName1;
    private String orgFileName1;
    private String keyword;
    private String page;
    private String size;
}
