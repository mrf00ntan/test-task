package kz.eserzhanov.testtask.testtask.service;

import kz.eserzhanov.testtask.testtask.database.entity.dir.Country;
import kz.eserzhanov.testtask.testtask.exception.SelfException;

public interface CountryService {
    Country getById(Long id);
}
