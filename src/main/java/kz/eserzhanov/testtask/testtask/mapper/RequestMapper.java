package kz.eserzhanov.testtask.testtask.mapper;

import kz.eserzhanov.testtask.testtask.database.entity.RequestEntity;
import kz.eserzhanov.testtask.testtask.database.entity.dir.City;
import kz.eserzhanov.testtask.testtask.dto.RequestDto;
import kz.eserzhanov.testtask.testtask.dto.dir.CityDto;
import kz.eserzhanov.testtask.testtask.service.CountryService;
import kz.eserzhanov.testtask.testtask.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(
        componentModel = "spring",
        uses = {
        })
public abstract class RequestMapper {
    @Autowired
    protected UserService userService;

    @Mapping(target = "userId", source = "user.id")
    public abstract RequestDto fromRequest(RequestEntity entity);
    @Mapping(target = "user", expression = "java(userService.getById(dto.getUserId()))")
    public abstract RequestEntity toRequest(RequestDto dto);
}
