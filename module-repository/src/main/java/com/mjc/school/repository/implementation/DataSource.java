package com.mjc.school.repository.implementation;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.*;

public class DataSource {
    private List<News> datasource;

    public DataSource(){
        datasource = new ArrayList<>();
        AuthorStorage authorStorage = new AuthorStorage();
        try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream("news.txt")){
            if(inputStream == null){
                throw new IOException("Resource file not found: news.txt");
            }
            Scanner scanner = new Scanner(inputStream);
            while(scanner.hasNextLine()){
                String[] parts = Arrays.stream(scanner.nextLine().split(";")).map(String::trim).toArray(String[]::new);
                News news = new News(parts[0],parts[1], LocalDateTime.parse(parts[2]),LocalDateTime.parse(parts[3]), authorStorage.getIdByAuthorName(parts[4]));
                datasource.add(news);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load news.txt from resources",e);
        }
    }

    public void createNews(News news){datasource.add(news);}

    public News readById(Long id){
        Optional<News> optionalNews = datasource.stream().filter(news -> news.getId().equals(id)).findFirst();
        return optionalNews.orElse(null);
    }

    public void updateNews(News news){
        for(News newsToUpdate : datasource){
            if(newsToUpdate.getId().equals(news.getId())) {
                newsToUpdate.setTitle(news.getTitle());
                newsToUpdate.setContent(news.getContent());
                newsToUpdate.setAuthorId(news.getAuthorId());
                newsToUpdate.setLastUpdateDate(LocalDateTime.now());
                System.out.println(newsToUpdate);
                return;
            }
        }
    }

    public void deleteNewsById(Long newsId){
        datasource.removeIf(newsDto -> newsDto.getId().equals(newsId));
    }

    public List<News> readAllNews(){return datasource;}
}
