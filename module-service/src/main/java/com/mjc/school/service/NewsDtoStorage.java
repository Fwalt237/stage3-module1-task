package com.mjc.school.service;

import com.mjc.school.repository.implementation.DataSourceModel;
import com.mjc.school.repository.implementation.Error;
import com.mjc.school.repository.implementation.DataSource;
import lombok.Getter;
import java.util.List;


public class NewsDtoStorage {
    @Getter
    private final DataSource dataSource;
    private final NewsMapper mapper ;

    public NewsDtoStorage(){
        dataSource = new DataSource();
        mapper = NewsMapper.INSTANCE;
    }

    public List<NewsDto> getAllNewsDto() {
        return mapper.toDtoList(dataSource.readAllNews());
    }

    public void printAllNewsDto() {
        for(NewsDto newsDto : this.getAllNewsDto()) {
            System.out.println(newsDto);
        }
    }

    public NewsDto getNewsDtoById(Long id){
        return mapper.toDto(dataSource.readById(id));
    }

    public boolean isAuthorIdExist(Long authorId){
        return this.getAllNewsDto().stream().anyMatch(dto -> dto.getAuthorId().equals(authorId));
    }

    public boolean isNewsIdExist(Long newsId){
        return this.getAllNewsDto().stream().anyMatch(dto -> dto.getId().equals(newsId));
    }

    public void newsIdNotNumber() {
        Error.newsIdNotNumber();
    }
    public void newsIdNotFound(Long id) {
        Error.newsIdNotFound(id);
    }
    public void authorIdNotNumber(){
        Error.authorIdNotNumber();
    }
    public void authorIdNotFound(Long authorId) {
        Error.authorIdNotFound(authorId);
    }

    public void createNewsDto(NewsDto newsDto){
        if(newsDto!=null) {
            DataSourceModel dataSourceModel = dataSource.createNews(mapper.toDataSourceModel(newsDto));
            System.out.println(mapper.toDto(dataSourceModel));
        }
    }

    public void updateNewsDto(NewsDto newsDto){
        if(newsDto!=null) {
            DataSourceModel dataSourceModel = dataSource.updateNews(mapper.toDataSourceModel(newsDto));
            System.out.println(mapper.toDto(dataSourceModel));
        }
    }

    public Boolean deleteNewsDtoById(Long newsId){
        return dataSource.deleteNewsById(newsId);
    }
}
