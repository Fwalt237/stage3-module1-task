package com.mjc.school.service;

import com.mjc.school.repository.impl.Error;
import com.mjc.school.repository.impl.NewsStorage;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class NewsDtoStorage {
    @Getter
    private List<NewsDto> newsDtoStore;
    NewsMapper mapper = NewsMapper.INSTANCE;

    public NewsDtoStorage(){
        NewsStorage newsStorage = new NewsStorage();
        newsDtoStore = mapper.toDtoList(newsStorage.getAllNews());
    }

    public void printAllNewsDto() {
        for(NewsDto newsDto : newsDtoStore){
            System.out.println(newsDto);
        }
    }

    public NewsDto getNewsDtoById(Long id){
        Optional<NewsDto> newsDtoOptional = newsDtoStore.stream().filter(n -> n.getId().equals(id)).findFirst();
        return newsDtoOptional.orElse(null);
    }

    public boolean isAuthorIdExist(Long authorId){
        for(NewsDto newsDto : newsDtoStore){
            if(newsDto.getAuthorId().equals(authorId)){
                return true;
            }
        }
        return false;
    }

    public boolean isNewsIdExist(Long newsId){
        for(NewsDto newsDto : newsDtoStore){
            if(newsDto.getId().equals(newsId)){
                return true;
            }
        }
        return false;
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
            this.newsDtoStore.add(newsDto);
            System.out.println(newsDto);
        }
    }

    public void updateNewsDto(NewsDto newsDto){
        if(newsDto!=null) {
            for(NewsDto newDtoToUpdate : newsDtoStore){
                if(newDtoToUpdate.getId().equals(newsDto.getId())) {
                    newDtoToUpdate.setTitle(newsDto.getTitle());
                    newDtoToUpdate.setContent(newsDto.getContent());
                    newDtoToUpdate.setAuthorId(newsDto.getAuthorId());
                    newDtoToUpdate.setLastUpdateDate(LocalDateTime.now());
                    System.out.println(newDtoToUpdate);
                    return;
                }
            }
        }
    }

    public void deleteNewsDtoById(Long newsId){
        newsDtoStore.removeIf(newsDto -> newsDto.getId().equals(newsId));
    }
}
