package org.boip.util.countryservice.rest.io;

import lombok.Data;

@Data
public class PagedCountryListRequest {
    private Integer page;
    private Integer size;
    private String sortField;
    private boolean sortDescending;
}
