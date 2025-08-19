package com.mjc.school.repository.implementation;

public class DataSourceIdGenerator {
    private static Long newsNextId = 1L;
    private static Long authorNextId = 1L;

    public static Long getNewsGeneratedId() {
        return newsNextId++;
    }

    public static Long getAuthorGeneratedId() {
        return authorNextId++;
    }
}
