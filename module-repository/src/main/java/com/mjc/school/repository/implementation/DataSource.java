package com.mjc.school.repository.implementation;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.*;

public class DataSource {
    private final List<DataSourceModel> datasource;

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
                DataSourceModel dataSourceModel = new DataSourceModel(parts[0],parts[1], LocalDateTime.parse(parts[2]),LocalDateTime.parse(parts[3]), authorStorage.getIdByAuthorName(parts[4]));
                datasource.add(dataSourceModel);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load news.txt from resources",e);
        }
    }

    public List<DataSourceModel> readAllNews(){return datasource;}

    public DataSourceModel createNews(DataSourceModel dataSourceModel){
        datasource.add(dataSourceModel);
        return dataSourceModel;
    }

    public DataSourceModel readById(Long id){
        Optional<DataSourceModel> optionalNews = datasource.stream().filter(dataSourceModel -> dataSourceModel.getId().equals(id)).findFirst();
        return optionalNews.orElse(null);
    }

    public DataSourceModel updateNews(DataSourceModel dataSourceModel){
        for(DataSourceModel dataSourceModelToUpdate : datasource){
            if(dataSourceModelToUpdate.getId().equals(dataSourceModel.getId())) {
                dataSourceModelToUpdate.setTitle(dataSourceModel.getTitle());
                dataSourceModelToUpdate.setContent(dataSourceModel.getContent());
                dataSourceModelToUpdate.setAuthorId(dataSourceModel.getAuthorId());
                dataSourceModelToUpdate.setLastUpdateDate(LocalDateTime.now());
                return dataSourceModelToUpdate;
            }
        }
        return null;
    }

    public Boolean deleteNewsById(Long newsId){
        return datasource.removeIf(dataSourceModel -> dataSourceModel.getId().equals(newsId));
    }

}
