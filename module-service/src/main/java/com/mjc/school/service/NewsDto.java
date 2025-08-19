package com.mjc.school.service;

import com.mjc.school.repository.implementation.DataSourceError;
import com.mjc.school.repository.implementation.DataSourceIdGenerator;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NewsDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
    private Long authorId;

    public NewsDto(String title, String content, Long authorId) {
        if (DataSourceError.isNewsAttributesCorrect(title, content)) return;
        this.id = DataSourceIdGenerator.getNewsGeneratedId();
        this.title = title;
        this.content = content;
        this.createDate = LocalDateTime.now();
        this.lastUpdateDate = LocalDateTime.now();
        this.authorId = authorId;
    }
}