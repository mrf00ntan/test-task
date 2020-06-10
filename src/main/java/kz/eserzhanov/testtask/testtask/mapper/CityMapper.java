package kz.eserzhanov.testtask.testtask.mapper;

import kz.eserzhanov.testtask.testtask.database.entity.dir.City;
import kz.eserzhanov.testtask.testtask.dto.dir.CityDto;
import kz.eserzhanov.testtask.testtask.service.CountryService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(
        componentModel = "spring",
        uses = {
        })
public abstract class CityMapper {
    @Autowired
    protected CountryService countryService;

    @Mapping(target = "countryId", source = "country.id")
    public abstract CityDto fromCity(City entity);
    @Mapping(target = "country", expression = "java(countryService.getById(dto.getCountryId()))")
    public abstract City toCity(CityDto dto);
}
