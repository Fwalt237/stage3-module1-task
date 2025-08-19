package com.mjc.school.repository.implementation;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataSourceAuthorStorage {
    private List<DataSourceAuthor> dataSourceAuthors;

    public DataSourceAuthorStorage() {
        this.dataSourceAuthors = new ArrayList<>();

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("author.txt")) {
            if (inputStream == null) {
                throw new IOException("Resource file not found: author.txt");
            }
            Scanner scanner = new Scanner(inputStream);
            while (scanner.hasNextLine()) {
                this.dataSourceAuthors.add(new DataSourceAuthor(scanner.nextLine().trim()));
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load author.txt from resources", e);
        }
    }

    public Long getIdByAuthorName(String authorName) {
        if (dataSourceAuthors == null) return null;
        for (DataSourceAuthor dataSourceAuthor : dataSourceAuthors) {
            if (dataSourceAuthor.getName().equals(authorName)) return dataSourceAuthor.getId();
        }
        return null;
    }

}
