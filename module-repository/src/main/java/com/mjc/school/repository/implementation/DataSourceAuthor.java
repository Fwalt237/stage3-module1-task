package com.mjc.school.repository.implementation;


import lombok.Data;

@Data
public class DataSourceAuthor {
    private static Long authorNextId = 1L;
    private Long id;
    private String name;

    public DataSourceAuthor(String name) {
        if(DataSourceError.isAuthorLengthCorrect(name)) return;
        this.id = DataSourceIdGenerator.getAuthorGeneratedId();
        this.name = name;
    }
}
