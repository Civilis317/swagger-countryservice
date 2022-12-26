package org.boip.util.countryservice.persistence.repository;

import org.boip.util.countryservice.persistence.entity.CountryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<CountryEntity, String> {

    @Query("select c from CountryEntity c")
    Page<CountryEntity> getCountriesByPage(Pageable pageable);
}
