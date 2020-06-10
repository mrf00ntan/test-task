package kz.eserzhanov.testtask.testtask.service.impl;

import kz.eserzhanov.testtask.testtask.database.entity.dir.Country;
import kz.eserzhanov.testtask.testtask.database.entity.dir.Role;
import kz.eserzhanov.testtask.testtask.database.repository.dir.CountryRepository;
import kz.eserzhanov.testtask.testtask.exception.MapException;
import kz.eserzhanov.testtask.testtask.exception.SelfException;
import kz.eserzhanov.testtask.testtask.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    private final MapException mapException;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository, MapException mapException) {
        this.countryRepository = countryRepository;
        this.mapException = mapException;
    }

    @Override
    public Country getById(Long id){
        Optional<Country> optional = countryRepository.findById(id);
        return optional.orElseGet(Country::new);
    }
}
