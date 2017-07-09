package com.fbm.repository;

import com.fbm.domain.Country;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Mocart on 07-Jul-17.
 */
public interface CountryRepository extends CrudRepository<Country, Long> {
    @Override
    List<Country> findAll();
}
