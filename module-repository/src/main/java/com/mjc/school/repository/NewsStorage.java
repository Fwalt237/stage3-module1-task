package com.mjc.school.repository;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.*;

public class NewsStorage {
    private List<News> newsDB;

    public NewsStorage(){
        this.newsDB = new ArrayList<>();
        AuthorStorage authorStorage = new AuthorStorage();
        try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream("news.txt")){
            if(inputStream == null){
                throw new IOException("Resource file not found: news.txt");
            }
            Scanner scanner = new Scanner(inputStream);
            while(scanner.hasNextLine()){
                String[] parts = Arrays.stream(scanner.nextLine().split(";")).map(String::trim).toArray(String[]::new);
                News news = new News(parts[0],parts[1], LocalDateTime.parse(parts[2]),LocalDateTime.parse(parts[3]), authorStorage.getIdByAuthorName(parts[4]));
                this.newsDB.add(news);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load news.txt from resources",e);
        }
    }

    public List<News> getAllNews(){
        return this.newsDB;
    }
}
