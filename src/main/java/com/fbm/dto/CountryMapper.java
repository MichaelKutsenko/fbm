package com.fbm.dto;

import com.fbm.domain.Country;
import com.fbm.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;

public class CountryMapper implements Mapper<Country, CountryDto> {
    @Autowired
    private CountryService countryService;

    @Override
    public CountryDto convertToDto(Country convertFrom) {
        CountryDto countryDto = new CountryDto();
        countryDto.setId(convertFrom.getCountryId());
        countryDto.setCountryName(convertFrom.getCountryName());

        return countryDto;
    }

    @Override
    public Country convertToNewEntity(CountryDto countryDto) {
        Country country = new Country();
        return convertToExistEntity(countryDto, country);
    }

    @Override
    public Country convertToExistEntity(CountryDto countryDto, Country country) {
        country.setCountryName(countryDto.getCountryName());
        return country;
    }
}
