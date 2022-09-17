package org.boip.util.countryservice.rest;

import lombok.Data;

@Data
public class GetCountriesRequest {
    private Integer page;
    private int size;
}
