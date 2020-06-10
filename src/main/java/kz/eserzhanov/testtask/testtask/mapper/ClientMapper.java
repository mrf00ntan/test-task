package kz.eserzhanov.testtask.testtask.mapper;

import kz.eserzhanov.testtask.testtask.database.entity.Client;
import kz.eserzhanov.testtask.testtask.dto.ClientDto;
import kz.eserzhanov.testtask.testtask.service.CityService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(
        componentModel = "spring",
        uses = {
            CityMapper.class
        })
public abstract class ClientMapper {
    @Autowired
    protected CityService cityService;

    @Mapping(target = "cityId", source = "city.id")
    public abstract ClientDto fromClient(Client entity);
    @Mapping(target = "city", expression = "java(cityService.getById(dto.getCityId()))")
    public abstract Client toClient(ClientDto dto);
}
