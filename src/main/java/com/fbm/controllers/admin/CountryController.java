package com.fbm.controllers.admin;

import com.fbm.domain.Country;
import com.fbm.dto.CountryDto;
import com.fbm.dto.CountryMapper;
import com.fbm.constants.ErrorCode;
import com.fbm.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/") //todo add "admin" to url
//todo add null pointer exception handling
public class CountryController {
    @Autowired
    private CountryService countryService;
    @Autowired
    private ResponseService responseService;

    @RequestMapping(path = "/countries/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity getAllCountries() {
        List<Country> countries = countryService.getAllCountries();
        Collections.sort(countries);
        return responseService.buildSuccessCountryResponse(countries);
    }

    @RequestMapping(path = "/add/country",  method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity addCountry(@RequestBody CountryDto countryDto) {
        Country country = countryService.getByName(countryDto.getCountryName());

        if (country != null) return responseService.buildErrorResponse(HttpStatus.BAD_REQUEST, ErrorCode.ELEMENT_EXISTS);

        CountryMapper countryMapper = new CountryMapper();
        country = countryMapper.convertToNewEntity(countryDto);

        try {
            country = countryService.addCountry(country);
            return responseService.buildSuccessResponse(country);
        }catch (Exception e) {
            return responseService.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.DB_ERROR);
        }
    }

    @RequestMapping(path = "/update/country",  method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity updateCountry(@RequestBody CountryDto countryDto) {
        Country country = countryService.getById(countryDto.getId());

        if (country == null) return responseService.buildErrorResponse(HttpStatus.BAD_REQUEST, ErrorCode.ELEMENT_DOES_NOT_EXIST);
        CountryMapper countryMapper = new CountryMapper();
        country = countryMapper.convertToExistEntity(countryDto, country);

        try {
            country = countryService.updateCountry(country);
            return responseService.buildSuccessResponse(country);
        } catch (Exception e) {
            return responseService.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.DB_ERROR);
        }
    }

    @RequestMapping(path = "/delete/country/byId/{countryId}",  method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity deleteCountry(@PathVariable long countryId) {
        try {
            countryService.deleteCountryById(countryId);
            return responseService.buildSuccessResponse(HttpStatus.OK);
        } catch (Exception e) {
            return responseService.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.DB_ERROR);
        }
    }
}
