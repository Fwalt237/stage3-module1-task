package com.mjc.school.service;

import com.mjc.school.repository.Error;
import com.mjc.school.repository.IdGenerator;
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
        if (Error.isNewsAttributesCorrect(title, content)) return;
        this.id = IdGenerator.getNewsGeneratedId();
        this.title = title;
        this.content = content;
        this.createDate = LocalDateTime.now();
        this.lastUpdateDate = LocalDateTime.now();
        this.authorId = authorId;
    }
}