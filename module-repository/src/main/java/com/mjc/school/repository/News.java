package com.mjc.school.repository;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class News {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private Long authorId;

    public News(String title, String content, Long authorId) {
        if (Error.isNewsAttributesCorrect(title, content)) return;
        this.id = IdGenerator.getNewsGeneratedId();
        this.title = title;
        this.content = content;
        this.createDate = LocalDateTime.now();
        this.lastUpdateDate = LocalDateTime.now();
        this.authorId = authorId;
    }

    public News(String title, String content,LocalDateTime createDate, LocalDateTime lastUpdateDate, Long authorId) {
        this.id = IdGenerator.getNewsGeneratedId();
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.authorId = authorId;
    }

}

