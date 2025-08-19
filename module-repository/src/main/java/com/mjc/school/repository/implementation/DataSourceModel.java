package com.mjc.school.repository.implementation;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class DataSourceModel {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private Long authorId;

    public DataSourceModel(String title, String content, Long authorId) {
        if (DataSourceError.isNewsAttributesCorrect(title, content)) return;
        this.id = DataSourceIdGenerator.getNewsGeneratedId();
        this.title = title;
        this.content = content;
        this.createDate = LocalDateTime.now();
        this.lastUpdateDate = LocalDateTime.now();
        this.authorId = authorId;
    }

    public DataSourceModel(String title, String content, LocalDateTime createDate, LocalDateTime lastUpdateDate, Long authorId) {
        this.id = DataSourceIdGenerator.getNewsGeneratedId();
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.authorId = authorId;
    }

}

