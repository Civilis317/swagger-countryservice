package org.boip.util.countryservice.service;

import lombok.extern.slf4j.Slf4j;
import org.boip.util.countryservice.exception.NotFoundException;
import org.boip.util.countryservice.model.Country;
import org.boip.util.countryservice.persistence.entiy.CountryEntity;
import org.boip.util.countryservice.persistence.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
public class CountryService {

    private final CountryRepository repository;

    public CountryService(CountryRepository repository) {
        this.repository = repository;
    }

    public Country getByCode(String code) {
        CountryEntity entity = repository.findById(code).orElseThrow(() -> new NotFoundException("Country not found for: " + code));
        return fromEntity(entity);
    }

    public List<Country> getAll() {
        List<Country> countryList = new ArrayList<>(256);
        repository.findAll().forEach(ce -> countryList.add(fromEntity(ce)));
        countryList.sort(Comparator.comparing(Country::getCode));
        return countryList;
    }

    public Country save(Country country) {
        CountryEntity entity = fromCountry(country);
        entity.markNew();
        return fromEntity(repository.save(entity));
    }

    private Country fromEntity(CountryEntity entity) {
        Country country = new Country();
        country.setCode(entity.getAlpha2code());
        country.setActive(entity.isActive());
        country.setEnName(entity.getEnglishName());
        country.setFrName(entity.getFrenchName());
        country.setNlName(entity.getDutchName());
        return country;
    }

    private CountryEntity fromCountry(Country country) {
        CountryEntity entity = new CountryEntity();
        entity.setAlpha2code(country.getCode());
        entity.setActive(country.isActive());
        entity.setDutchName(country.getNlName());
        entity.setEnglishName(country.getEnName());
        entity.setFrenchName(country.getFrName());
        return entity;
    }
}
