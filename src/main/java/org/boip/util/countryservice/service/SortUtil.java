package org.boip.util.countryservice.service;

import org.boip.util.countryservice.persistence.entity.CountryEntity;
import org.boip.util.countryservice.rest.io.PagedCountryListRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class SortUtil {
    private final static String MODEL_CODE = "code";
    private final static String MODEL_NL_NAME = "nlName";
    private final static String MODEL_EN_NAME = "enName";
    private final static String MODEL_FR_NAME = "frName";
    private final static String MODEL_CREATED = "created";

    private final static String ENTITY_CODE = "alpha2code";
    private final static String ENTITY_NL_NAME = "dutchName";
    private final static String ENTITY_EN_NAME = "englishName";
    private final static String ENTITY_FR_NAME = "frenchName";
    private final static String ENTITY_CREATION_DATE = "creationDate";

    private Map<String, String> modelEntityColumnNames;

    @PostConstruct
    private void init() {
        modelEntityColumnNames = new HashMap<>();
        modelEntityColumnNames.put(MODEL_CODE, ENTITY_CODE);
        modelEntityColumnNames.put(MODEL_NL_NAME, ENTITY_NL_NAME);
        modelEntityColumnNames.put(MODEL_EN_NAME, ENTITY_EN_NAME);
        modelEntityColumnNames.put(MODEL_FR_NAME, ENTITY_FR_NAME);
        modelEntityColumnNames.put(MODEL_CREATED, ENTITY_CREATION_DATE);
    }

    private String getEntityFieldName(String modelFieldName) {
        if (modelFieldName == null)
            return ENTITY_CODE;
        String result = modelEntityColumnNames.get(modelFieldName);
        if (result != null)
            return result;
        return ENTITY_CODE;
    }

    public Sort buildSort(PagedCountryListRequest request) {
        return Sort.by(
                request.isSortDescending() ? Sort.Direction.DESC : Sort.Direction.ASC,
                getEntityFieldName(request.getSortField())
        );
    }

}
