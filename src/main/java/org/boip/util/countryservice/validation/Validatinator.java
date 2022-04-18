package org.boip.util.countryservice.validation;

import org.boip.util.countryservice.exception.ValidationException;
import org.boip.util.countryservice.model.Country;

import static org.boip.util.countryservice.util.StringUtils.isEmpty;


/**
 * author: Dr. Heinz Doofenshmirtz
 * (c) Evil Incorporated
 */
public class Validatinator {

    public static void validate(Country country) {
        ValidationException ve = new ValidationException();

        if (country != null) {
            if (isEmpty(country.getCode()))
                ve.addMessage("country.code is a mandatory field");
            if (isEmpty(country.getNlName()))
                ve.addMessage("country.nlName is a mandatory field");
            if (isEmpty(country.getEnName()))
                ve.addMessage("country.enName is a mandatory field");
            if (isEmpty(country.getFrName()))
                ve.addMessage("country.frName is a mandatory field");
        } else {
            ve.addMessage("invalid request");
        }

        if (! ve.validationOK())
            throw ve;
    }
}
