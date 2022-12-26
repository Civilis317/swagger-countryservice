package org.boip.util.countryservice.rest.io;

import lombok.Data;
import org.boip.util.countryservice.model.Country;

import java.util.List;

@Data
public class PagedCountryListResponse {
    private Integer pageNumber;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;
    private List<Country> countryList;
}
