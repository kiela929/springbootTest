package com.example.springbootvuetest.entity.www.board;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="NOTICE")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_no")
    private Long id;
    @Column(name = "category")
    private String category;
    @Column(name = "ref")
    private Integer ref;
    @Column(name = "mid")
    private String mid;
    @Column(name = "bname")
    private String name;
    @Column(name = "bpasswd")
    private String bPasswd;
    @Column(name = "ip")
    private String ip;
    @Column(name = "title")
    private String title;
    @Column(name = "contents")
    private String contents;
    @CreatedDate
    @Column(name = "write_day")
    private LocalDateTime writeDay;
    @Column(name = "email")
    private String email;
    @Column(name = "readcount")
    private String readCount;
    @Column(name = "filename1")
    private String fileName1;
    @Column(name = "orgfilename1")
    private String orgFileName1;
}
