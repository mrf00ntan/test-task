package kz.eserzhanov.testtask.testtask.service.impl;

import kz.eserzhanov.testtask.testtask.database.entity.dir.City;
import kz.eserzhanov.testtask.testtask.database.repository.dir.CityRepository;
import kz.eserzhanov.testtask.testtask.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public City getById(Long id) {
        Optional<City> optional = cityRepository.findById(id);
        return optional.orElseGet(City::new);
    }
}
