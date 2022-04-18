package org.boip.util.countryservice.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.boip.util.countryservice.model.Country;
import org.boip.util.countryservice.service.CountryService;
import org.boip.util.countryservice.validation.Validatinator;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.boip.util.countryservice.validation.Validatinator.*;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@Api(value = "Countryservice")
public class CountryServiceController extends AbstractController {

    private final CountryService countryService;

    public CountryServiceController(CountryService countryService) {
        this.countryService = countryService;
    }

    @ApiOperation(value = "Retrieve a list of countries")
    @GetMapping("/list")
    public @ResponseBody List<Country> list() {
        return countryService.getAll();
    }

    @ApiOperation(value = "Retrieve a single country")
    @GetMapping("/get/{code}")
    public @ResponseBody Country get(@PathVariable String code) {
        return countryService.getByCode(code);
    }

    @ApiOperation(value = "add or update a country")
    @PostMapping("save")
    public @ResponseBody Country save(@RequestBody Country country) {
        validate(country);
        return countryService.save(country);
    }

}
