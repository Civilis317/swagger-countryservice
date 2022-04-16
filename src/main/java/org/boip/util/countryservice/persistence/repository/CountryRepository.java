package org.boip.util.countryservice.persistence.repository;

import org.boip.util.countryservice.persistence.entiy.CountryEntity;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<CountryEntity, String> {
}
