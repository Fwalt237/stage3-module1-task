package com.mjc.school.repository.implementation;


import lombok.Data;

@Data
public class Author {
    private static Long authorNextId = 1L;
    private Long id;
    private String name;

    public Author(String name) {
        if(Error.isAuthorLengthCorrect(name)) return;
        this.id = IdGenerator.getAuthorGeneratedId();
        this.name = name;
    }
}
