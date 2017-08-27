package com.fbm.services;

import com.fbm.domain.Country;
import com.fbm.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mocart on 05-Aug-17.
 */
@Service
public class CountryService {
    @Autowired
    private CountryRepository countryRepository;

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    public Country getByName(String countryName) {
        return countryRepository.findByCountryName(countryName);
    }

    public Country getById(long countryId) {
        return countryRepository.findOne(countryId);
    }

    public Country addCountry(Country country) {
        return countryRepository.save(country);
    }

    public Country updateCountry(Country country) {
        return countryRepository.save(country);
    }

    public void deleteCountryById(long countryId) {
        countryRepository.delete(countryId);
    }

}
