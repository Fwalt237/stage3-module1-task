package com.mjc.school.service;

import com.mjc.school.repository.implementation.News;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface NewsMapper {
    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);
    List<NewsDto> toDtoList(List<News> newsList);
}
