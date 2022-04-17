package org.boip.util.countryservice.transformation;

import org.boip.util.countryservice.model.Country;
import org.boip.util.countryservice.persistence.entity.CountryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface CountryModelEntityMapper {
    @Mappings({
            @Mapping(target="alpha2code", source="model.code"),
            @Mapping(target="dutchName", source="model.nlName"),
            @Mapping(target="englishName", source="model.enName"),
            @Mapping(target="frenchName", source="model.frName"),
            @Mapping(target="creationDate", source = "model.created", dateFormat = "dd-MM-yyyy HH:mm:ss")
    })
    CountryEntity modelToEntity(Country model);

    @Mappings({
            @Mapping(target="code", source="entity.alpha2code"),
            @Mapping(target="nlName", source="entity.dutchName"),
            @Mapping(target="enName", source="entity.englishName"),
            @Mapping(target="frName", source="entity.frenchName"),
            @Mapping(target="created", source = "entity.creationDate", dateFormat = "dd-MM-yyyy HH:mm:ss")
    })
    Country entityToModel(CountryEntity entity);
}
