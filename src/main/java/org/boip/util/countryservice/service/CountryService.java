package org.boip.util.countryservice.service;

import lombok.extern.slf4j.Slf4j;
import org.boip.util.countryservice.model.Country;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Slf4j
@Service
public class CountryService {

    private static Map<String, Country> countryMap;

    @PostConstruct
    private void init() {
        log.info("init...");
        countryMap = new HashMap<>(256);
        countryMap.put("NL", new Country("NL", "Nederland", "Holland", "Pays Bas", true));
        countryMap.put("DE", new Country("DE", "Duitsland", "Germany", "Allemagne", true));
        countryMap.put("FR", new Country("FR", "Frankrijk", "France", "France", true));
        countryMap.put("BE", new Country("BE", "Belgie", "Belgium", "Belgique", true));
        countryMap.put("UK", new Country("UK", "Verenigd Koninkrijk", "United Kingdom", "Royaumee-Uni", true));
    }

    public Country getByCode(String code) {
        return countryMap.get(code.toUpperCase());
    }

    public List<Country> getAll() {
        List<Country> resultList = new ArrayList<>(countryMap.values());
        resultList.sort(Comparator.comparing(Country::getCode));

        return resultList;
    }

    public void save(Country country) {
        countryMap.put(country.getCode(), country);
    }
}
