package org.boip.util.countryservice.service;

import lombok.extern.slf4j.Slf4j;
import org.boip.util.countryservice.exception.ApplicationException;
import org.boip.util.countryservice.exception.NotFoundException;
import org.boip.util.countryservice.exception.ValidationException;
import org.boip.util.countryservice.model.Country;
import org.boip.util.countryservice.persistence.entity.CountryEntity;
import org.boip.util.countryservice.persistence.repository.CountryRepository;
import org.boip.util.countryservice.rest.io.PagedCountryListRequest;
import org.boip.util.countryservice.rest.io.PagedCountryListResponse;
import org.boip.util.countryservice.transformation.CountryModelEntityMapper;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CountryService {

    private final CountryRepository repository;
    private final CountryModelEntityMapper mapper;
    private final SortUtil sortUtil;

    public CountryService(CountryRepository repository, CountryModelEntityMapper mapper, SortUtil sortUtil) {
        this.repository = repository;
        this.mapper = mapper;
        this.sortUtil = sortUtil;
    }

    public Country getByCode(String code) {
        return mapper.entityToModel(
                repository.findById(
                        mapper.uppercase(code))
                        .orElseThrow(() -> new NotFoundException("Country not found for: " + code)
                )
        );
    }

    public List<Country> getAll() {
        List<Country> countryList = new ArrayList<>(256);
        repository.findAll().forEach(ce -> countryList.add(mapper.entityToModel(ce)));
        countryList.sort(Comparator.comparing(Country::getCode));
        return countryList;
    }

    public PagedCountryListResponse getPagedCountryList(PagedCountryListRequest request) {
        Page<CountryEntity> page = repository.getCountriesByPage(
                PageRequest.of(request.getPage(),
                        request.getSize(),
                        sortUtil.buildSort(request))
        );
        PagedCountryListResponse response = new PagedCountryListResponse();
        response.setCountryList(page.get().map(mapper::entityToModel).collect(Collectors.toList()));
        response.setPageNumber(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalPages(page.getTotalPages());
        response.setTotalElements(page.getTotalElements());
        return response;
    }

    public Country save(Country country) {
        try {
            country.setCreated(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()));
            CountryEntity entity = mapper.modelToEntity(country);
            entity.markNew();
            return mapper.entityToModel(repository.save(entity));
        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof ConstraintViolationException) {
                throw new ValidationException("unique constraint violation: code=" + country.getCode());
            }
            throw new ApplicationException(e.getMessage(), e);
        } catch (Exception e) {
            throw new ApplicationException(e.getMessage(), e);
        }
    }
}
