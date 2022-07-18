package com.gitub.evgen.web;

import com.gitub.evgen.model.Country;
import com.gitub.evgen.service.ICountryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class MyController {

    private final ICountryService countryService;

    public MyController(ICountryService countryService) {
        this.countryService = countryService;
    }

    @RequestMapping("/countries")
    public List<Country> listCountries() {
        return countryService.findAll();
    }
}

