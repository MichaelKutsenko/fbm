package com.fbm.services;

import com.fbm.domain.Country;
import com.fbm.domain.Player;
import com.fbm.domain.Team;
import com.fbm.repository.CountryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.assertj.core.api.Java6Assertions.assertThat;


/**
 * Created by Mocart on 05-Aug-17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TeamServiceTest {
    @Autowired
    private TeamService teamService;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private PlayerService playerService;


    @Test
    @Transactional
    public void addTeam() throws Exception {
        Country country = countryRepository.findOne(2l);

        Team team = new Team();
        team.setCountry(country);
        team.setName("name");
        team.setTeamId(100l);

        Team team2= new Team();
        team2.setCountry(country);
        team2.setName("test name");
        team2.setTeamId(1000l);

        teamService.addTeam(team);
        teamService.addTeam(team2);

        assertThat(teamService.getAllTeams().size()).isEqualTo(5);
        assertThat(teamService.getAllTeams().size()).isNotEqualTo(3);
        assertThat(teamService.getByCountryId(2).size()).isEqualTo(3);
        assertThat(teamService.getByCountryName("England").size()).isEqualTo(3);
        assertThat(teamService.getByName("name")).isEqualTo(team);
        assertThat(teamService.getByName("name")).isNotEqualTo(team2);
        assertThat(teamService.getByName("name1")).isNull();
    }

    @Test
    public void getAllTeams() throws Exception {
    }

    @Test
    public void getByCountryName() throws Exception {
    }

    @Test
    public void getByCountryId() throws Exception {
    }

    @Test
    public void getByName() throws Exception {
    }

}