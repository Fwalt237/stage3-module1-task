package com.mjc.school.repository.implementation;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AuthorStorage {
    private final List<Author> authors;

    public AuthorStorage() {
        this.authors = new ArrayList<>();

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("author.txt")) {
            if (inputStream == null) {
                throw new IOException("Resource file not found: author.txt");
            }
            Scanner scanner = new Scanner(inputStream);
            while (scanner.hasNextLine()) {
                this.authors.add(new Author(scanner.nextLine().trim()));
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load author.txt from resources", e);
        }
    }

    public Long getIdByAuthorName(String authorName) {
        if (authors == null) return null;
        for (Author author : authors) {
            if (author.getName().equals(authorName)) return author.getId();
        }
        return null;
    }

}
