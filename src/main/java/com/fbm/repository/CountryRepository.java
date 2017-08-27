package com.fbm.repository;

import com.fbm.domain.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Mocart on 07-Jul-17.
 */
@Transactional
public interface CountryRepository extends CrudRepository<Country, Long> {
    @Override
    List<Country> findAll();

    Country findByCountryName(String countryName);
}
