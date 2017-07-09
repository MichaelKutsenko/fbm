package com.fbm.repository;

import com.fbm.domain.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Mocart on 07-Jul-17.
 */
public interface TeamRepository extends CrudRepository<Team, Long> {
    @Override
    List<Team> findAll();

    List<Team> findByCountry_CountryId(long countryId);

    Team findByName(String name);
}
