package org.boip.util.countryservice.service;

import lombok.extern.slf4j.Slf4j;
import org.boip.util.countryservice.exception.NotFoundException;
import org.boip.util.countryservice.model.Country;
import org.boip.util.countryservice.persistence.entity.CountryEntity;
import org.boip.util.countryservice.persistence.repository.CountryRepository;
import org.boip.util.countryservice.transformation.CountryModelEntityMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
public class CountryService {

    private final CountryRepository repository;
    private final CountryModelEntityMapper mapper;

    public CountryService(CountryRepository repository, CountryModelEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Country getByCode(String code) {
        CountryEntity entity = repository.findById(code).orElseThrow(() -> new NotFoundException("Country not found for: " + code));
        return mapper.entityToModel(entity);
    }

    public List<Country> getAll() {
        List<Country> countryList = new ArrayList<>(256);
        repository.findAll().forEach(ce -> countryList.add(mapper.entityToModel(ce)));
        countryList.sort(Comparator.comparing(Country::getCode));
        return countryList;
    }

    public Country save(Country country) {
        CountryEntity entity = mapper.modelToEntity(country);
        entity.markNew();
        return mapper.entityToModel(repository.save(entity));
    }
}
