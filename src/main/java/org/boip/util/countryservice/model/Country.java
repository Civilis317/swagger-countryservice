package org.boip.util.countryservice.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Country {

    private String code;
    private String nlName;
    private String enName;
    private String frName;
    private boolean active;

    @ApiModelProperty(readOnly = true)
    private String created;

}
