package com.fbm.services;

import com.fbm.domain.Country;
import com.fbm.repository.CountryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by Mocart on 05-Aug-17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CountryServiceTest {
    @Autowired
    private CountryService countryService;

    @Test
    @Transactional
    public void getAllCountries() throws Exception {
        Country country = new Country();
        country.setCountryName("na");
        countryService.addCountry(country);

        assertThat(countryService.getAllCountries().size()).isEqualTo(4);
    }
}